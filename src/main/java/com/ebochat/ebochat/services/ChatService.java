package com.ebochat.ebochat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.Responses.CreateChatResponse;
import com.ebochat.ebochat.models.ChatModel;
import com.ebochat.ebochat.models.ChatUserModel;
import com.ebochat.ebochat.models.UserModel;
import com.ebochat.ebochat.repositories.IChatRepository;
import com.ebochat.ebochat.repositories.IChatUserRepository;

@Service
public class ChatService {
    @Autowired
    IChatRepository chatRepository;

    @Autowired
    UserService userService;

    @Autowired
    ChatUserService chatUserService;

    public Optional<ChatModel> findById(Long id){
        return this.chatRepository.findById(id);
    }

    public CreateChatResponse saveChat(ChatModel chat, Long userId, Long userLoggedId){
        CreateChatResponse createChatResponse = new CreateChatResponse();
        try {
            ChatModel newChat = this.chatRepository.save(chat);
            createChatResponse = this.addChatAndUser(newChat.getId(), userId);
            createChatResponse = this.addChatAndUser(newChat.getId(), userLoggedId);

        } catch (Exception e) {
            createChatResponse.setError(true);
            createChatResponse.setResponse(e.getMessage());
        }
        return createChatResponse;

    }

    public CreateChatResponse addChatAndUser(Long chatId, Long userId){
        CreateChatResponse createChatResponse = new CreateChatResponse();
        try {
            Optional<ChatModel> chat = this.chatRepository.findById(chatId);
            Optional<UserModel> user = this.userService.getById(userId);

            ChatUserModel chatUserModel = new ChatUserModel();
            chatUserModel.setChat(chat.get());
            chatUserModel.setUser(user.get());
            this.chatUserService.saveChatUser(chatUserModel);

            createChatResponse.setError(false);
            createChatResponse.setResponse("Chat Creado satisfactoriamente");

        } catch (Exception e) {
            createChatResponse.setError(true);
            createChatResponse.setResponse(e.getMessage());
        }

        return createChatResponse;
    }
}
