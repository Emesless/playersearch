package ru.emesless.searchplayers.DTO;

import lombok.Data;
import ru.emesless.searchplayers.model.ChatMessages;
import ru.emesless.searchplayers.model.User;

/**
 * todo Document type ChatDTO
 */
@Data
public class ChatDTO {
    private long id;
    private String message;
    private User user;

    public ChatMessages fromDTO() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setId(id);
        chatMessages.setMessage(message);
        chatMessages.setUser(user);
        return chatMessages;
    }

    public ChatDTO toDTO(ChatMessages chatMessages) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chatMessages.getId());
        chatDTO.setMessage(chatMessages.getMessage());
        chatDTO.setUser(chatMessages.getUser());
        return chatDTO;
    }
}
