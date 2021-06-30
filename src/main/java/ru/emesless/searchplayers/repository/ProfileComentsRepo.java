package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.ProfileComents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * todo Document type ProfileComentsRepo
 */
public interface ProfileComentsRepo extends JpaRepository<ProfileComents, Long> {
    List<ProfileComents> getAllByAddressesUser_Id(Long id);
}
