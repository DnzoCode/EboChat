package com.ebochat.ebochat.websocket;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ebochat.ebochat.Request.MessageRequest;
import com.ebochat.ebochat.models.ChatModel;
import com.ebochat.ebochat.models.MessageModel;
import com.ebochat.ebochat.models.UserModel;
import com.ebochat.ebochat.services.ChatService;
import com.ebochat.ebochat.services.MessageService;
import com.ebochat.ebochat.services.UserService;
import com.google.gson.Gson;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;



    @Autowired
    public WebSocketHandler(MessageService messageService, UserService userService, ChatService chatService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
    }

    private final LocalDateTime timeStamp = LocalDateTime.now();


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        MessageModel messageModel = new MessageModel();
        // Manejar el mensaje recibido
        String payload = (String) message.getPayload();
        System.out.println("Received message: " + payload);

        Gson gson = new Gson();
        MessageRequest messageRequest = gson.fromJson(payload, MessageRequest.class);
        Long userId = messageRequest.getUserId();
        Long chatId = messageRequest.getChatId();

        Optional<UserModel> optionalUser = this.userService.getById(userId);
        Optional<ChatModel> chat = this.chatService.findById(chatId);


        messageModel.setContent(messageRequest.getContent());
        messageModel.setUser(optionalUser.get());
        messageModel.setChat(chat.get());
        messageModel.setCreationDate(timeStamp);
        this.messageService.saveMessage(messageModel);
        // Envía un mensaje de vuelta al cliente
        session.sendMessage(new TextMessage(payload));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Conexión establecida
        // ...
        System.out.println("WebSocket connection established.");
        session.sendMessage(new TextMessage("{'stateChat': 'ONLINE'}"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Conexión cerrada
        // ...
        System.out.println("WebSocket connection closed.");
        session.sendMessage(new TextMessage("{'stateChat': 'DISCONNECT'}"));
    }
}
