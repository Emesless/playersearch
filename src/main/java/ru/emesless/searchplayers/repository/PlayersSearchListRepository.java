package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.PlayerSearchList;
import ru.emesless.searchplayers.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersSearchListRepository extends JpaRepository<PlayerSearchList, Long> {
    List<PlayerSearchList> findAllByGame(Game game);

    Page<PlayerSearchList> findAllByGame(Game game, Pageable pageable);

    PlayerSearchList getById(Long id);

    List<PlayerSearchList> findAllByUser(User user);

    void deleteByUserAndId(User user, Long id);

    void deleteById(long id);
}
