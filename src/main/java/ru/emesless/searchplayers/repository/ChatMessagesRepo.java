package ru.emesless.searchplayers.repository;

import ru.emesless.searchplayers.model.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * todo Document type ChatMessagesRepo
 */
public interface ChatMessagesRepo extends JpaRepository<ChatMessages, Long> {
    @Query(value = "SELECT * FROM public.chat_messages ORDER BY id DESC LIMIT 2", nativeQuery = true)
    List<ChatMessages> getLast();
}
