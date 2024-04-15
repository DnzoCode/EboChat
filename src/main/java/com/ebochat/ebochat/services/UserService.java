package com.ebochat.ebochat.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebochat.ebochat.models.UserModel;
import com.ebochat.ebochat.repositories.IUserRepository;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }
    
    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }


}
