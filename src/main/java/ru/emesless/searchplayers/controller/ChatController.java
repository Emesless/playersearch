package ru.emesless.searchplayers.controller;

import ru.emesless.searchplayers.DTO.ChatDTO;
import ru.emesless.searchplayers.model.ChatMessages;
import ru.emesless.searchplayers.repository.ChatMessagesRepo;
import ru.emesless.searchplayers.service.Chat.ChatMessageService;
import ru.emesless.searchplayers.service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * todo Document type ChatController
 */

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final UserService currentUser;
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatController(UserService currentUser, ChatMessageService chatMessageService) {
        this.currentUser = currentUser;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public ChatDTO change(ChatDTO chatMessages) {
        this.chatMessageService.save(chatMessages);
        return chatMessages;
    }

    @PostMapping
    public ResponseEntity<ChatDTO> sendMessage(@RequestBody ChatDTO chatMes, @AuthenticationPrincipal User user) {
        chatMes.setUser(currentUser.loadUserByEmail(user.getUsername()));
        this.chatMessageService.save(chatMes);
        return new ResponseEntity<ChatDTO>(chatMes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ChatMessages>> getChatMes() {
        System.out.println();

        return new ResponseEntity<List<ChatMessages>>(this.chatMessageService.getLast(), HttpStatus.OK);
    }
}
