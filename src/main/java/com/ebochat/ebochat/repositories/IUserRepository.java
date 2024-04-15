package com.ebochat.ebochat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebochat.ebochat.models.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
