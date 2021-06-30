package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.Contacts;
import ru.emesless.searchplayers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * todo Document type ContactsRepo
 */
public interface ContactsRepo extends JpaRepository<Contacts, Long> {
    List<Contacts> findAllByUser(User user);

    void deleteAllByUser(User user);
}
