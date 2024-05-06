package com.ebochat.ebochat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ebochat.ebochat.models.MessageModel;

public interface IMessageRepository extends JpaRepository<MessageModel, Long>{

    @Query("SELECT m FROM Message m WHERE m.chat_id = :chatId")
    List<MessageModel> findByChatId(@Param("chatId") Long chatId);
}
