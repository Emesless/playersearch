package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.ServerSearchList;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.util.Views;

/**
 * todo Document type ServerDTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerSearchListDTO {
    @JsonView(Views.Request.class)
    private Long id;
    @JsonView(Views.Request.class)
    private String title;
    @JsonView(Views.Request.class)
    private String info;

    @JsonView(Views.Request.class)
    private Game game;
    @JsonView(Views.Request.class)
    @JsonIgnoreProperties("password")
    private User user;

    public ServerSearchList fromDTO() {
        ServerSearchList serverSearchList = new ServerSearchList();
        serverSearchList.setId(id);
        serverSearchList.setTitle(title);
        serverSearchList.setInfo(info);

        serverSearchList.setGame(game);
        serverSearchList.setUser(user);
        return serverSearchList;
    }

    public ServerSearchListDTO toDTO(ServerSearchList serverSearchList) {
        ServerSearchListDTO serverSearchListDTO = new ServerSearchListDTO();
        serverSearchListDTO.setId(serverSearchList.getId());
        serverSearchListDTO.setTitle(serverSearchList.getTitle());
        serverSearchListDTO.setInfo(serverSearchList.getInfo());

        serverSearchListDTO.setGame(serverSearchList.getGame());
        serverSearchListDTO.setUser(serverSearchList.getUser());
        return serverSearchListDTO;
    }
}
