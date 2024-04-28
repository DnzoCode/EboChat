package com.ebochat.ebochat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebochat.ebochat.Request.ChatMessage;
import com.ebochat.ebochat.models.MessageModel;
import com.ebochat.ebochat.services.MessageService;

@Controller
@RequestMapping("/wsMessage")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
        public MessageModel sendMessage(@Payload MessageModel messagePayload) {
        return messagePayload;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageModel addUser(@Payload MessageModel chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getDirection());
        return chatMessage;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage handleChatMessage(@DestinationVariable String roomId, ChatMessage message) {
        // Guardar el mensaje en la base de datos
        // message.setCreationDate(LocalDateTime.now());
        return new ChatMessage(message.getMessage(), message.getUser());
    }
}
