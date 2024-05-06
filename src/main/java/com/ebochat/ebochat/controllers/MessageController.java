package com.ebochat.ebochat.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebochat.ebochat.Request.CreateMessageRequest;
import com.ebochat.ebochat.Responses.MessageResponse;
import com.ebochat.ebochat.models.ChatModel;
import com.ebochat.ebochat.models.MessageModel;
import com.ebochat.ebochat.models.UserModel;
import com.ebochat.ebochat.services.ChatService;
import com.ebochat.ebochat.services.MessageService;
import com.ebochat.ebochat.services.UserService;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    @GetMapping
    public ArrayList<MessageModel> findAll(){
        return this.messageService.getMessages();
    }

    @GetMapping(path = "/chat/{chatId}")
    public List<MessageResponse> findMessagesByChatId(@PathVariable("chatId") Long chatId){
        return this.messageService.findMessagesByChatId(chatId);
    }

    @PostMapping
    public MessageModel save(@RequestBody CreateMessageRequest message){
        MessageModel messageModel = new MessageModel();
        Optional<ChatModel> chat = this.chatService.findById(message.getChatId());
        Optional<UserModel> user = this.userService.getById(message.getUserId());
        messageModel.setChat(chat.get());
        messageModel.setUser(user.get());
        messageModel.setContent(message.getContent());
        return this.messageService.saveMessage(messageModel);
    }
}
