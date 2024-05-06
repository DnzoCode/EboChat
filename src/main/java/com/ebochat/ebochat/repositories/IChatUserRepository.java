package com.ebochat.ebochat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ebochat.ebochat.Responses.ContactResponse;
import com.ebochat.ebochat.models.ChatUserModel;

public interface IChatUserRepository extends JpaRepository<ChatUserModel, Long>{
    @Query("SELECT new com.ebochat.ebochat.Responses.ContactResponse(cu.chat.id, u.username)  " +
           "FROM ChatUserModel cu " +
           "JOIN cu.chat c " +
           "JOIN cu.user u " +
           "WHERE u.id != :userId " +  // Excluir el usuario actual
            "AND c.id IN (" +
            "   SELECT cu2.chat.id FROM ChatUserModel cu2 WHERE cu2.user.id = :userId" +
            ")"
    )
    List<ContactResponse> findChatsByUserId(@Param("userId") Long userId);
}
