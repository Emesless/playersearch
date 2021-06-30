package ru.emesless.searchplayers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import ru.emesless.searchplayers.DTO.GameDTO;
import ru.emesless.searchplayers.DTO.PagePlayerRequestDTO;
import ru.emesless.searchplayers.DTO.PlayerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.service.Game.GameService;
import ru.emesless.searchplayers.service.GamersListRequest.PlayersSearchListService;
import ru.emesless.searchplayers.service.Users.UserService;
import ru.emesless.searchplayers.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGame() {
        return new ResponseEntity<List<Game>>(this.gameService.getAll(), HttpStatus.OK);
    }



/*    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        //Game gameFromDb = gameRepository.findGameByTitle(game.getTitle());
        Game gameFromDb = gameService.findByName(game.getTitle());
        HttpHeaders headers = new HttpHeaders();
        if (gameFromDb == null){
            System.out.println("zashel");
            System.out.println(game.getTitle());
            this.gameService.save(game);
            return new ResponseEntity<Game>(game, headers, HttpStatus.CREATED);
        }else return new ResponseEntity<Game>(HttpStatus.BAD_REQUEST);
    }*/


/*    @GetMapping("{title}")
    public  ResponseEntity<Game> getGame(@PathVariable("title") String gameTitle){
        if (gameTitle == null){
            return new ResponseEntity<Game>(HttpStatus.BAD_REQUEST);
        }
        Game game = this.gameService.findByName(gameTitle);
        if (game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(game, HttpStatus.OK);
    }*/

    @GetMapping("{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable("id") Long gameId) {
        if (gameId == null) {
            return new ResponseEntity<GameDTO>(HttpStatus.BAD_REQUEST);
        }
        Game game = this.gameService.getById(gameId);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GameDTO result = GameDTO.fromGame(game);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Autowired
    private PlayersSearchListService playersSearchListService;
    @Autowired
    private UserService currentUser;

    //запихать гаме в пул +
    @PostMapping("{id}/PlayersList")
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<PlayerSearchListDTO> addPlayerOrder(@RequestBody PlayerSearchListDTO playerOrder,
        @PathVariable("id") Long gameId,
        @AuthenticationPrincipal User user
    ) {
        HttpHeaders headers = new HttpHeaders();
        if (gameId == null) {
            return new ResponseEntity<PlayerSearchListDTO>(HttpStatus.BAD_REQUEST);
        }
        Game game = this.gameService.getById(gameId);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        playerOrder.setGame(game);
        playerOrder.setUser(currentUser.loadUserByEmail(user.getUsername()));
        this.playersSearchListService.save(playerOrder);
        return new ResponseEntity<PlayerSearchListDTO>(playerOrder, headers, HttpStatus.CREATED);
    }

    // подумать над представлением todto
/*    @GetMapping("{id}/PlayersList")
    public  ResponseEntity<List<PlayerSearchList>> getAllPlayerOrder(@PathVariable("id") Long gameId){
        HttpHeaders headers = new HttpHeaders();
        if (gameId == null){
            return new ResponseEntity<List<PlayerSearchList>>(HttpStatus.BAD_REQUEST);
        }
        Game game = this.gameService.getById(gameId);
        if (game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PlayerSearchList> listOrder= this.playersSearchListService.getAll(game);
        return new ResponseEntity<List<PlayerSearchList>>(listOrder, HttpStatus.OK);
    }*/

    @GetMapping("{id}/PlayersListt")
    public ResponseEntity<PagePlayerRequestDTO> getAllPlayerOrder2(@PathVariable("id") Long gameId,
        @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Game game = this.gameService.getById(gameId);
        return new ResponseEntity<PagePlayerRequestDTO>(this.playersSearchListService.getAllPage(game, pageable), HttpStatus.OK);
    }

    @GetMapping("{id}/PlayersListt/{req_id}")
    @JsonView(Views.Request.class)
    public ResponseEntity<PlayerSearchListDTO> getCurrentReq(
        @PathVariable("req_id") Long currentReqId) {
        return new ResponseEntity<>(playersSearchListService.getOnePage(currentReqId), HttpStatus.OK);
    }

    @PostMapping("{req_id}/fav")
    public ResponseEntity addToFavorite(@PathVariable("req_id") Long currentReqId, @AuthenticationPrincipal User user
    ) {
        currentUser.addToFavReq(currentReqId, currentUser.loadUserByEmail(user.getUsername()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
