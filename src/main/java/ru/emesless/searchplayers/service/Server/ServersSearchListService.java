package ru.emesless.searchplayers.service.Server;

import ru.emesless.searchplayers.DTO.PageServersRequestDTO;
import ru.emesless.searchplayers.DTO.ServerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.ServerSearchList;
import org.springframework.data.domain.Pageable;
import ru.emesless.searchplayers.model.User;

import java.util.List;

/**
 * todo Document type ServersSearchListService
 */
public interface ServersSearchListService {
    void save(ServerSearchListDTO serverSearchListDTO);

    void delete(Long id);

    void delete(User user, Long id);

    List<ServerSearchList> getAll(Game game);

    PageServersRequestDTO getAllPage(Game game, Pageable pageable);

    ServerSearchListDTO getOnePage(Long id);

    List<ServerSearchList> getUsersRequestServers(Long id);
}
