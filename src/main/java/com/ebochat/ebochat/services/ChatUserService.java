package com.ebochat.ebochat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.models.ChatUserModel;
import com.ebochat.ebochat.repositories.IChatUserRepository;

@Service
public class ChatUserService {
    @Autowired
    IChatUserRepository chatUserRepository;

    public ChatUserModel saveChatUser(ChatUserModel chatUserModel){
        return this.chatUserRepository.save(chatUserModel);
    }
}
