package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByTitle(String title);
}
