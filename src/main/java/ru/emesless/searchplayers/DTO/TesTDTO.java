package ru.emesless.searchplayers.DTO;

import lombok.Data;
import ru.emesless.searchplayers.model.User;

/**
 * todo Document type TesTDTO
 */
@Data
public class TesTDTO {
    private Long id;

    private String comments;

    private User addressesUser;

    private User senderUser;
}
