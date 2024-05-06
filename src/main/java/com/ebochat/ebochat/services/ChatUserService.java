package com.ebochat.ebochat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.Responses.ContactResponse;
import com.ebochat.ebochat.models.ChatUserModel;
import com.ebochat.ebochat.repositories.IChatUserRepository;

@Service
public class ChatUserService {
    @Autowired
    IChatUserRepository chatUserRepository;

    public ChatUserModel saveChatUser(ChatUserModel chatUserModel){
        return this.chatUserRepository.save(chatUserModel);
    }

    public List<ContactResponse> findChatsByUserId(Long userId){
        return this.chatUserRepository.findChatsByUserId(userId);
    }
}
