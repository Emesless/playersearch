package ru.emesless.searchplayers.service.Game;

import ru.emesless.searchplayers.model.Game;
import ru.emesless.searchplayers.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDetailsService implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameDetailsService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Game findByName(String name) {
        return gameRepository.findGameByTitle(name);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
