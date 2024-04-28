package com.ebochat.ebochat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebochat.ebochat.models.RoleModel;

public interface IRoleRepository extends JpaRepository<RoleModel, Long>{

}
