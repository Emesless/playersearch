package ru.emesless.searchplayers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.emesless.searchplayers.DTO.GameDTO;
import ru.emesless.searchplayers.DTO.PageFeedback;
import ru.emesless.searchplayers.model.*;
import ru.emesless.searchplayers.service.Game.GameService;
import ru.emesless.searchplayers.service.GamersListRequest.PlayersSearchListService;
import ru.emesless.searchplayers.service.Server.ServersSearchListService;
import ru.emesless.searchplayers.service.Users.UserService;
import ru.emesless.searchplayers.service.admins.AdminService;
import ru.emesless.searchplayers.util.Views;

import java.util.List;

/**
 * todo Document type adminController
 */
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('developers:write')")
public class adminController {
    private final UserService userService;
    private final AdminService adminService;
    private final GameService gameService;

    @Autowired
    public adminController(UserService userService, AdminService adminService,
        GameService gameService) {
        this.userService = userService;
        this.adminService = adminService;
        this.gameService = gameService;
    }

    @PostMapping("requests/{req_id}/del")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchList> delUsersRequests(@PathVariable("req_id") Long req,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        User admin = userService.loadUserByEmail(user.getUsername());
        if (admin.getRole() == Role.ADMIN) {
            adminService.deleteReq(req);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("profile/{profile_id}/comments/{req_id}")
    @JsonView(Views.Request.class)
    public ResponseEntity delComments(@PathVariable("req_id") Long req, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        User admin = userService.loadUserByEmail(user.getUsername());
        if (admin.getRole() == Role.ADMIN) {
            adminService.deleteComments(req);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/servers/{req_id}/del")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchList> delUsersServers(@PathVariable("req_id") Long req,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        User admin = userService.loadUserByEmail(user.getUsername());
        if (admin.getRole() == Role.ADMIN) {
            adminService.deleteServers(req);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/feedback")
    @JsonView(Views.Request.class)
    public ResponseEntity<PageFeedback> getFeedback(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
        @PageableDefault(size = 4, sort = {"id"}, direction = Sort.Direction.DESC)
            Pageable pageable) {
        User admin = userService.loadUserByEmail(user.getUsername());
        if (admin.getRole() == Role.ADMIN) {
            return new ResponseEntity<PageFeedback>(this.adminService.getAllFed(pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("game/add")
    public ResponseEntity<GameDTO> addGame(@RequestBody GameDTO gameDTO) {
        Game game = gameDTO.toGame();
        Game gameFromDb = gameService.findByName(game.getTitle());
        HttpHeaders headers = new HttpHeaders();
        if (gameFromDb == null) {
            this.gameService.save(game);
            return new ResponseEntity<GameDTO>(gameDTO, headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<GameDTO>(HttpStatus.BAD_REQUEST);
        }
    }
}
