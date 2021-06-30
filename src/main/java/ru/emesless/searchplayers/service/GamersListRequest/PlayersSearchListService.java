package ru.emesless.searchplayers.service.GamersListRequest;

import ru.emesless.searchplayers.DTO.PagePlayerRequestDTO;
import ru.emesless.searchplayers.DTO.PlayerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.PlayerSearchList;
import org.springframework.data.domain.Pageable;
import ru.emesless.searchplayers.model.User;

import java.util.List;

public interface PlayersSearchListService {
    void save(PlayerSearchListDTO playerSearchListDTO);

    void delete(Long id);

    void delete(User user, Long id);

    List<PlayerSearchList> getAll(Game game);

    PagePlayerRequestDTO getAllPage(Game game, Pageable pageable);

    PlayerSearchListDTO getOnePage(Long id);

    List<PlayerSearchList> getUsersRequest(Long id);
}
