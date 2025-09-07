package com.tukaram.rest;

import com.tukaram.entity.MessageEntity;
import com.tukaram.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{roomId}")
    public List<MessageEntity> getMessages(@PathVariable String roomId) {
        return messageService.getMessagesForRoom(roomId);
    }
}
