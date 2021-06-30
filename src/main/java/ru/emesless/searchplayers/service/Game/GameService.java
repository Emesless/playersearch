package ru.emesless.searchplayers.service.Game;

import ru.emesless.searchplayers.model.Game;

import java.util.List;

public interface GameService {
    Game getById(Long id);

    Game findByName(String name);

    void save(Game game);

    void delete(Long id);

    List<Game> getAll();
}
