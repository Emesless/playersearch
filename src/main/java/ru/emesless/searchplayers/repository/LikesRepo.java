package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.Likes;
import ru.emesless.searchplayers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * todo Document type Likes
 */
public interface LikesRepo extends JpaRepository<Likes, Long> {
    long countByAddressesUser(User user);

    Likes getByAddressesUserAndSenderUser(User adr, User send);

    void deleteByAddressesUserAndSenderUser(User adr, User send);
}
