package ru.emesless.searchplayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.emesless.searchplayers.model.FavoriteReqPlayers;
import ru.emesless.searchplayers.model.User;

import java.util.List;

/**
 * todo Document type Req_fav_repo
 */
public interface Req_fav_repo extends JpaRepository<FavoriteReqPlayers, Long> {
    List<FavoriteReqPlayers> getAllByAddressesUser_Id(Long id);

    void deleteByAddressesUserAndPlayerSearchList_Id(User user, Long id);

    FavoriteReqPlayers getByAddressesUserAndPlayerSearchList_Id(User user, Long id);

    List<FavoriteReqPlayers> getAllByAddressesUser(User addressesUser);
}
