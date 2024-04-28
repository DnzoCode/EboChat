package com.ebochat.ebochat.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.models.RoleModel;
import com.ebochat.ebochat.repositories.IRoleRepository;
@Service
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;

    public ArrayList<RoleModel> getUsers(){
        return (ArrayList<RoleModel>) roleRepository.findAll();
    }

    public Optional<RoleModel> getById(Long id){
        return roleRepository.findById(id);
    }
    
    public RoleModel saveUser(RoleModel user){
        return roleRepository.save(user);
    }
}
