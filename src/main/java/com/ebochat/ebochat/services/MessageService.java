package com.ebochat.ebochat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.Responses.MessageResponse;
import com.ebochat.ebochat.models.MessageModel;
import com.ebochat.ebochat.repositories.IMessageRepository;

@Service
public class MessageService {
    @Autowired
    IMessageRepository messageRepository;

    public ArrayList<MessageModel> getMessages(){
        return (ArrayList<MessageModel>) messageRepository.findAll();
    }

    public Optional<MessageModel> getMessageById(Long id){
        return messageRepository.findById(id);
    }
    
    public MessageModel saveMessage(MessageModel message){
        return messageRepository.save(message);
    }

    public List<MessageResponse> findMessagesByChatId(Long chatId){
        return messageRepository.findByChatId(chatId);
    }
}
