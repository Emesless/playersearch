package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.PlayerSearchList;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.util.SqlTimeDeserializer;
import ru.emesless.searchplayers.util.Views;

import java.sql.Time;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerSearchListDTO {
    @JsonView(Views.Request.class)
    private Long id;
    @JsonView(Views.Request.class)
    private String title;
    @JsonView(Views.Request.class)
    private String info;

    @JsonView(Views.Request.class)
    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time timeStart;
    @JsonView(Views.Request.class)

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time timeEnd;
    @JsonView(Views.Request.class)
    private Game game;
    @JsonView(Views.Request.class)
    @JsonIgnoreProperties("password")
    private User user;

    public PlayerSearchList fromDTO() {
        PlayerSearchList playerSearchList = new PlayerSearchList();
        playerSearchList.setId(id);
        playerSearchList.setTitle(title);
        playerSearchList.setInfo(info);
        playerSearchList.setTimeStart(timeStart);
        playerSearchList.setTimeEnd(timeEnd);
        playerSearchList.setGame(game);
        playerSearchList.setUser(user);
        return playerSearchList;
    }

    public PlayerSearchListDTO toDTO(PlayerSearchList playerSearchList) {
        PlayerSearchListDTO playerSearchListDTO = new PlayerSearchListDTO();
        playerSearchListDTO.setId(playerSearchList.getId());
        playerSearchListDTO.setTitle(playerSearchList.getTitle());
        playerSearchListDTO.setInfo(playerSearchList.getInfo());
        playerSearchListDTO.setTimeStart(playerSearchList.getTimeStart());
        playerSearchListDTO.setTimeEnd(playerSearchList.getTimeEnd());
        playerSearchListDTO.setGame(playerSearchList.getGame());
        playerSearchListDTO.setUser(playerSearchList.getUser());
        return playerSearchListDTO;
    }
}
