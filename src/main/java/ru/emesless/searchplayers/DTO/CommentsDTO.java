package ru.emesless.searchplayers.DTO;

import lombok.Data;
import ru.emesless.searchplayers.model.ProfileComents;
import ru.emesless.searchplayers.service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * todo Document type CommentsDTO
 */
@Data
public class CommentsDTO {

    @Autowired
    private UserService userService;

    private Long id;

    private String comments;

    private Long addressesUser;

    private Long senderUser;

    public ProfileComents fromDTO() {
        ProfileComents profileComents = new ProfileComents();
        profileComents.setId(id);
        profileComents.setComments(comments);
        profileComents.setAddressesUser(userService.getById(addressesUser));
        profileComents.setSenderUser(userService.getById(senderUser));
        return profileComents;
    }

    public CommentsDTO toDTO(ProfileComents profileComents) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(profileComents.getId());
        commentsDTO.setComments(profileComents.getComments());
        commentsDTO.setAddressesUser(profileComents.getAddressesUser().getId());
        commentsDTO.setSenderUser(profileComents.getSenderUser().getId());
        return commentsDTO;
    }
}




