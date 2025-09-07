package com.tukaram.service;

import com.tukaram.entity.MessageEntity;
import com.tukaram.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public MessageEntity save(String sender, String content, String roomId) {
        MessageEntity e = new MessageEntity(sender, content, roomId, LocalDateTime.now());
        return repo.save(e);
    }

    public List<MessageEntity> getMessagesForRoom(String roomId) {
        return repo.findByRoomIdOrderByTimestampAsc(roomId);
    }
}
