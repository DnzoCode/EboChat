package com.ebochat.ebochat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebochat.ebochat.models.ChatUserModel;

public interface IChatUserRepository extends JpaRepository<ChatUserModel, Long>{

}
