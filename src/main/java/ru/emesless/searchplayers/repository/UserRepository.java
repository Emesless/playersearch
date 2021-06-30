package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findTopByOrderByIdDesc();
}
