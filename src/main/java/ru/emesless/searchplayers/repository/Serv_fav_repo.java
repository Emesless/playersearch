package ru.emesless.searchplayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.emesless.searchplayers.model.FavoriteServers;
import ru.emesless.searchplayers.model.User;

import java.util.List;

/**
 * todo Document type Serv_fav_repo
 */
public interface Serv_fav_repo extends JpaRepository<FavoriteServers, Long> {
    void deleteByAddressesUserAndServerSearchList_Id(User user, Long id);

    FavoriteServers getByAddressesUserAndServerSearchList_Id(User user, Long id);

    List<FavoriteServers> getAllByAddressesUser(User user);
}
