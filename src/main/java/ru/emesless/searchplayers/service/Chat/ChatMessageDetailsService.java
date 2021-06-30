package ru.emesless.searchplayers.service.Chat;

import ru.emesless.searchplayers.DTO.ChatDTO;
import ru.emesless.searchplayers.model.ChatMessages;
import ru.emesless.searchplayers.repository.ChatMessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo Document type ChatMessageDetailsService
 */
@Service
public class ChatMessageDetailsService implements ChatMessageService {

    private final ChatMessagesRepo chatMessagesRepo;

    @Autowired
    public ChatMessageDetailsService(ChatMessagesRepo chatMessagesRepo) {
        this.chatMessagesRepo = chatMessagesRepo;
    }

    @Override
    public void save(ChatDTO chatMessages) {
        chatMessagesRepo.save(chatMessages.fromDTO());
    }

    @Override
    public List<ChatMessages> getAll() {
        //     chatMessagesRepo.findFirst10();
        return null;
    }

    @Override
    public List<ChatMessages> getLast() {
        return chatMessagesRepo.getLast();
    }
}
