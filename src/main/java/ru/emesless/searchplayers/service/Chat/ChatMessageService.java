package ru.emesless.searchplayers.service.Chat;

import ru.emesless.searchplayers.DTO.ChatDTO;
import ru.emesless.searchplayers.model.ChatMessages;

import java.util.List;

/**
 * todo Document type ChatMessageService
 */
public interface ChatMessageService {
    void save(ChatDTO chatMessages);

    List<ChatMessages> getAll();

    List<ChatMessages> getLast();
}
