package ru.emesless.searchplayers.service.Server;

import ru.emesless.searchplayers.DTO.PageServersRequestDTO;
import ru.emesless.searchplayers.DTO.ServerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.ServerSearchList;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.repository.ServerSearchListRepo;
import ru.emesless.searchplayers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * todo Document type ServersSearchDetails
 */
@Service
public class ServersSearchDetails implements ServersSearchListService {

    private final ServerSearchListRepo serverSearchListRepo;
    private final UserRepository userRepository;

    @Autowired
    public ServersSearchDetails(ServerSearchListRepo serverSearchListRepo, UserRepository userRepository) {
        this.serverSearchListRepo = serverSearchListRepo;
        this.userRepository = userRepository;
    }

    @Override
    public void save(ServerSearchListDTO serverSearchListDTO) {
        serverSearchListRepo.save(serverSearchListDTO.fromDTO());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        serverSearchListRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User user, Long id) {
        serverSearchListRepo.deleteByUserAndId(user, id);
    }

    @Override
    public List<ServerSearchList> getAll(Game game) {
        return null;
    }

    @Override
    public PageServersRequestDTO getAllPage(Game game, Pageable pageable) {
        Page<ServerSearchList> page = serverSearchListRepo.findAllByGame(game, pageable);
        return new PageServersRequestDTO(page.getContent(),
            pageable.getPageNumber(),
            page.getTotalPages());
    }

    @Override
    public ServerSearchListDTO getOnePage(Long id) {
        ServerSearchList serverSearchList = serverSearchListRepo.findById(id).orElse(null);
        ServerSearchListDTO serverSearchListDTO = null;
        if (serverSearchList != null) {
            serverSearchListDTO = new ServerSearchListDTO().toDTO(serverSearchList);
        }
        return serverSearchListDTO;
    }

    @Override
    public List<ServerSearchList> getUsersRequestServers(Long id) {
        return serverSearchListRepo.findAllByUser(userRepository.findById(id).orElse(null));
    }
}
