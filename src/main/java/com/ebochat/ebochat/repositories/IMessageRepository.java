package com.ebochat.ebochat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebochat.ebochat.models.MessageModel;

public interface IMessageRepository extends JpaRepository<MessageModel, Long>{

}
