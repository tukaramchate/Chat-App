package com.tukaram.repository;

import com.tukaram.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByRoomIdOrderByTimestampAsc(String roomId);
}
