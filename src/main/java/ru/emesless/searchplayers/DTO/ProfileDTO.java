package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.model.Role;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.util.Views;

import java.time.LocalDate;

/**
 * todo Document type ProfileDTO
 */
@Data
@JsonView(Views.UserInfo.class)
public class ProfileDTO {
    private Long id;
    @JsonView(Views.UserRole.class)
    private String email;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthdate;
    @JsonView(Views.UserRole.class)
    private Role role;

    public static ProfileDTO fromUser(User user) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setLastName(user.getLastName());
        profileDTO.setNickname(user.getNickname());
        profileDTO.setBirthdate(user.getBirthdate());
        profileDTO.setRole(user.getRole());
        return profileDTO;
    }
}

