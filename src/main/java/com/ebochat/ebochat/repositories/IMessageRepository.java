package com.ebochat.ebochat.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ebochat.ebochat.Responses.MessageResponse;
import com.ebochat.ebochat.models.MessageModel;

public interface IMessageRepository extends JpaRepository<MessageModel, Long>{

    @Query("SELECT new com.ebochat.ebochat.Responses.MessageResponse(m.content, u.username, u.id) FROM MessageModel m JOIN m.user u WHERE m.chat.id = :chatId")
    List<MessageResponse> findByChatId(@Param("chatId") Long chatId);
}
