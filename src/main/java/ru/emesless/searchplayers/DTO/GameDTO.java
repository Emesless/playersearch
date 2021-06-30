package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.emesless.searchplayers.model.Game;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDTO {
    private Long id;
    private String title;
    private String about;

    public Game toGame() {
        Game game = new Game();
        game.setId(id);
        game.setTitle(title);
        game.setAbout(about);
        return game;
    }

    public static GameDTO fromGame(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setAbout(game.getAbout());
        return gameDTO;
    }
}
