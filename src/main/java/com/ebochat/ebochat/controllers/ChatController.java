package com.ebochat.ebochat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebochat.ebochat.Request.ChatRequest;
import com.ebochat.ebochat.Responses.ContactResponse;
import com.ebochat.ebochat.Responses.CreateChatResponse;
import com.ebochat.ebochat.models.ChatModel;
import com.ebochat.ebochat.services.ChatService;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping
    public CreateChatResponse addChat(@RequestBody ChatRequest request) {
        ChatModel chatModel = new ChatModel();
        chatModel.setTypeChat(request.getTypeChat());
        CreateChatResponse createChatResponse = new CreateChatResponse();
        createChatResponse = this.chatService.saveChat(chatModel, request.getUserId(), request.getUserLoggedId());
        return createChatResponse;
    }

    @GetMapping(path = "/user/{userId}")
    public List<ContactResponse> findChatsByUserId(@PathVariable("userId") Long userId){
        return this.chatService.findChatsByUserId(userId);
    }
}
