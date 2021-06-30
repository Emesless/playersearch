package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.model.ServerSearchList;
import ru.emesless.searchplayers.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * todo Document type ServerSearchListRepo
 */
public interface ServerSearchListRepo extends JpaRepository<ServerSearchList, Long> {
    List<ServerSearchList> findAllByGame(Game game);

    Page<ServerSearchList> findAllByGame(Game game, Pageable pageable);

    ServerSearchList getById(Long id);

    List<ServerSearchList> findAllByUser(User user);

    void deleteByUserAndId(User user, Long id);
}
