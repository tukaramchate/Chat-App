package com.tukaram.controller;

import com.tukaram.model.ChatMessage;
import com.tukaram.service.MessageService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    // client sends to /app/chat.send/{roomId}
    @MessageMapping("/chat.send/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, ChatMessage message) {
        // persist message
        messageService.save(message.getSender(), message.getContent(), roomId);

        // broadcast to subscribers of /topic/{roomId}
        messagingTemplate.convertAndSend("/topic/" + roomId, message);
    }

    // client sends to /app/chat.addUser/{roomId}
    @MessageMapping("/chat.addUser/{roomId}")
    public void addUser(@DestinationVariable String roomId, ChatMessage message) {
        message.setType(ChatMessage.MessageType.JOIN);
        messagingTemplate.convertAndSend("/topic/" + roomId, message);
    }
}
