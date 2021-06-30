package ru.emesless.searchplayers.service.GamersListRequest;

import ru.emesless.searchplayers.DTO.PagePlayerRequestDTO;
import ru.emesless.searchplayers.DTO.PlayerSearchListDTO;
import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.PlayerSearchList;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.repository.PlayersSearchListRepository;
import ru.emesless.searchplayers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayersSearchListDetailsService implements PlayersSearchListService {

    private final PlayersSearchListRepository playersSearchListRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlayersSearchListDetailsService(PlayersSearchListRepository playersSearchListRepository,
        UserRepository userRepository) {
        this.playersSearchListRepository = playersSearchListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(PlayerSearchListDTO playerSearchListDTO) {
        playersSearchListRepository.save(playerSearchListDTO.fromDTO());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        playersSearchListRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User user, Long id) {
        playersSearchListRepository.deleteByUserAndId(user, id);
    }

    @Override
    public List<PlayerSearchList> getAll(Game game) {
        return playersSearchListRepository.findAllByGame(game);
    }

    @Override
    public PagePlayerRequestDTO getAllPage(Game game, Pageable pageable) {
        Page<PlayerSearchList> page = playersSearchListRepository.findAllByGame(game, pageable);
        return new PagePlayerRequestDTO(page.getContent(),
            pageable.getPageNumber(),
            page.getTotalPages());
    }

    @Override
    public PlayerSearchListDTO getOnePage(Long id) {
        PlayerSearchList playerSearchList = playersSearchListRepository.findById(id).orElse(null);
        PlayerSearchListDTO playerSearchListDTO = null;
        if (playerSearchList != null) {
            playerSearchListDTO = new PlayerSearchListDTO().toDTO(playerSearchList);
        }
        return playerSearchListDTO;
    }

    @Override
    public List<PlayerSearchList> getUsersRequest(Long id) {
        return playersSearchListRepository.findAllByUser(userRepository.findById(id).orElse(null));
    }
}
