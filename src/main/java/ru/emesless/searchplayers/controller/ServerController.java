package ru.emesless.searchplayers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import ru.emesless.searchplayers.DTO.PageServersRequestDTO;
import ru.emesless.searchplayers.DTO.ServerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.service.Game.GameService;
import ru.emesless.searchplayers.service.Server.ServersSearchListService;
import ru.emesless.searchplayers.service.Users.UserService;
import ru.emesless.searchplayers.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * todo Document type ServerController
 */
@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final GameService gameService;
    private final ServersSearchListService serversSearchListService;
    private final UserService currentUser;

    @Autowired
    public ServerController(GameService gameService, ServersSearchListService serversSearchListService, UserService currentUser) {
        this.gameService = gameService;
        this.serversSearchListService = serversSearchListService;
        this.currentUser = currentUser;
    }

    @PostMapping("{id}/ServersList")
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<ServerSearchListDTO> addPlayerOrder(@RequestBody ServerSearchListDTO serverOrd,
        @PathVariable("id") Long gameId,
        @AuthenticationPrincipal User user
    ) {
        if (gameId == null) {
            return new ResponseEntity<ServerSearchListDTO>(HttpStatus.BAD_REQUEST);
        }
        Game game = this.gameService.getById(gameId);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serverOrd.setGame(game);
        serverOrd.setUser(currentUser.loadUserByEmail(user.getUsername()));
        this.serversSearchListService.save(serverOrd);
        return new ResponseEntity<ServerSearchListDTO>(serverOrd, HttpStatus.CREATED);
    }

    @GetMapping("{id}/ServersList")
    public ResponseEntity<PageServersRequestDTO> getAllServers(@PathVariable("id") Long gameId,
        @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Game game = this.gameService.getById(gameId);
        return new ResponseEntity<PageServersRequestDTO>(this.serversSearchListService.getAllPage(game, pageable), HttpStatus.OK);
    }

    @GetMapping("{id}/ServersList/{req_id}")
    @JsonView(Views.Request.class)
    public ResponseEntity<ServerSearchListDTO> getCurrentReq(
        @PathVariable("req_id") Long currentReqId) {
        return new ResponseEntity<>(serversSearchListService.getOnePage(currentReqId), HttpStatus.OK);
    }

    @PostMapping("{req_id}/fav")
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity addToFavorite(@PathVariable("req_id") Long currentReqId, @AuthenticationPrincipal User user
    ) {
        currentUser.addToFavSer(currentReqId, currentUser.loadUserByEmail(user.getUsername()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
