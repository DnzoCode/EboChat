package com.ebochat.ebochat.Request;

import com.ebochat.ebochat.models.ChatModel.TypeChat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    TypeChat typeChat;
    Long userId;
    Long userLoggedId;

}
