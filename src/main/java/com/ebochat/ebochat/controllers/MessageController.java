package com.ebochat.ebochat.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebochat.ebochat.models.MessageModel;
import com.ebochat.ebochat.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping
    public ArrayList<MessageModel> findAll(){
        return this.messageService.getMessages();
    }

    @GetMapping(path = "/chat/{chatId}")
    public List<MessageModel> findMessagesByChatId(@PathVariable("chatId") Long chatId){
        return this.messageService.findMessagesByChatId(chatId);
    }
}
