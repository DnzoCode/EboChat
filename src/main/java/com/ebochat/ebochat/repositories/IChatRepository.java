package com.ebochat.ebochat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebochat.ebochat.models.ChatModel;

public interface IChatRepository extends JpaRepository<ChatModel, Long>{

}
