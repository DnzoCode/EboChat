package com.ebochat.ebochat.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.models.ChatModel;
import com.ebochat.ebochat.repositories.IChatRepository;

@Service
public class ChatService {
    @Autowired
    IChatRepository chatRepository;

    public ArrayList<ChatModel> getUsers(){
        return (ArrayList<ChatModel>) chatRepository.findAll();
    }

    public Optional<ChatModel> getById(Long id){
        return chatRepository.findById(id);
    }
    
    public ChatModel saveChat(ChatModel user){
        return chatRepository.save(user);
    }
}
