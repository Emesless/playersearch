package ru.emesless.searchplayers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import ru.emesless.searchplayers.DTO.PlayerSearchListDTO;
import ru.emesless.searchplayers.DTO.ServerSearchListDTO;
import ru.emesless.searchplayers.DTO.mappers.CommentsConvertor;
import ru.emesless.searchplayers.DTO.CommentsDTO;
import ru.emesless.searchplayers.DTO.ProfileDTO;
import ru.emesless.searchplayers.model.*;
import ru.emesless.searchplayers.service.GamersListRequest.PlayersSearchListService;
import ru.emesless.searchplayers.service.Server.ServersSearchListService;
import ru.emesless.searchplayers.service.Users.UserService;
import ru.emesless.searchplayers.util.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * todo Document type profile
 */

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;
    private final PlayersSearchListService playersSearchListService;
    private final ServersSearchListService serversSearchListService;

    @Autowired
    public ProfileController(UserService userService, PlayersSearchListService playersSearchListService,
        ServersSearchListService serversSearchListService) {
        this.userService = userService;
        this.playersSearchListService = playersSearchListService;
        this.serversSearchListService = serversSearchListService;
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserInfo.class)
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable("id") Long profileId) {
        User user = userService.getById(profileId);
        if (user != null) {
            ProfileDTO profileDTO = ProfileDTO.fromUser(user);
            return new ResponseEntity<>(profileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/upd")
    public ResponseEntity<ProfileDTO> updProfile(@PathVariable("id") Long profileId, @RequestBody User user) {
        this.userService.update(user, profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/upd/contacts")
    public ResponseEntity<ProfileDTO> updProfileContacts(@PathVariable("id") Long profileId, @RequestBody List<Contacts> contacts) {
        this.userService.deleteContacts(profileId);
        for (Contacts contacts1 : contacts) {
            contacts1.setUser(this.userService.getById(profileId));
            this.userService.saveContacts(contacts1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> addComments(@PathVariable("id") Long profileId,
        @RequestBody CommentsDTO commentsDTO,
        @AuthenticationPrincipal User user) {
        userService.addComments(commentsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<ProfileComents>> getComments(@PathVariable("id") Long profileId) {
        /*List<ProfileComents> profileComents = userService.getComments(profileId);
        List<TesTDTO> commentsDTOS = profileComents.stream().map(commentsConvertor::convertToDto).collect(Collectors.toList());*/
        return new ResponseEntity<>(userService.getComments(profileId), HttpStatus.OK);
    }

    @GetMapping("/{id}/contacts")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<Contacts>> getContacts(@PathVariable("id") Long profileId) {
        return new ResponseEntity<>(userService.getContacts(profileId), HttpStatus.OK);
    }

    @PostMapping("/{id}/likes")
    public ResponseEntity<Likes> addLike(@PathVariable("id") long profileId, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        userService.addLike(profileId, userService.loadUserByEmail(user.getUsername()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<Long> getLike(@PathVariable("id") long profileId) {
        return new ResponseEntity<>(userService.getLikes(profileId), HttpStatus.OK);
    }

    @PostMapping("{id}/update")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User newUser, @AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        if (userService.loadUserByEmail(user.getUsername()).getId().equals(id)) {
            BeanUtils.copyProperties(newUser, userService.loadUserByEmail(user.getUsername()), "id", "role", "status");
            System.out.println(newUser);
            userService.save(newUser);
        }
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @GetMapping("/{id}/requests")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<PlayerSearchList>> getUsersRequests(@PathVariable("id") Long userid) {
        return new ResponseEntity<>(playersSearchListService.getUsersRequest(userid), HttpStatus.OK);
    }

    @GetMapping("/{id}/servers")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<ServerSearchList>> getUsersServers(@PathVariable("id") Long userid) {
        return new ResponseEntity<>(serversSearchListService.getUsersRequestServers(userid), HttpStatus.OK);
    }

    @PostMapping("/{id}/requests/{req_id}/del")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchList> delUsersRequests(@PathVariable("id") Long userid, @PathVariable("req_id") Long req,
        @AuthenticationPrincipal
            org.springframework.security.core.userdetails.User user) {
        User currentUser = userService.loadUserByEmail(user.getUsername());
        if (currentUser.getId() == userid) {
            playersSearchListService.delete(currentUser, req);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/servers/{req_id}/del")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchList> delUsersServers(@PathVariable("id") Long userid, @PathVariable("req_id") Long req, @AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        User currentUser = userService.loadUserByEmail(user.getUsername());
        if (currentUser.getId() == userid) {
            serversSearchListService.delete(currentUser, req);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/requests/{req_id}/upd")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchList> updUsersRequests(@PathVariable("id") Long userid, @PathVariable("req_id") Long req,
        @RequestBody PlayerSearchListDTO playerOrder) {
        playerOrder.setId(req);
        System.out.println(playerOrder);
        playerOrder.setGame(playersSearchListService.getOnePage(req).getGame());
        playerOrder.setUser(playersSearchListService.getOnePage(req).getUser());
        this.playersSearchListService.save(playerOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/servers/{req_id}/upd")
    @JsonView(Views.Request.class)
    public ResponseEntity<ServerSearchList> updServersInf(@PathVariable("id") Long userid, @PathVariable("req_id") Long req,
        @RequestBody ServerSearchListDTO serverInf) {
        serverInf.setId(req);
        serverInf.setGame(serversSearchListService.getOnePage(req).getGame());
        serverInf.setUser(serversSearchListService.getOnePage(req).getUser());
        this.serversSearchListService.save(serverInf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}/fav/players")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<FavoriteReqPlayers>> getFavPlay(@AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        return new ResponseEntity<>(userService.getFavoriteReq(userService.loadUserByEmail(user.getUsername())), HttpStatus.OK);
    }

    @GetMapping("{id}/fav/servers")
    @JsonView(Views.Request.class)
    public ResponseEntity<List<FavoriteServers>> getFavServ(@AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        return new ResponseEntity<>(userService.getFavoriteSer(userService.loadUserByEmail(user.getUsername())), HttpStatus.OK);
    }

    @PostMapping("{id}/feedback")
    public ResponseEntity addFeedback(@RequestBody FeedBack feedBack, @AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        feedBack.setUser(userService.loadUserByEmail(user.getUsername()));
        userService.addFeedback(feedBack);
        return new ResponseEntity(HttpStatus.OK);
    }
}
