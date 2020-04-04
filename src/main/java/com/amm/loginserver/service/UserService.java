package com.amm.loginserver.service;

import com.amm.loginserver.model.User;
import com.amm.loginserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User addUser(User user) throws Exception{
        if(repository.findUserByUsername(user.getUsername()) ==null){
            return repository.save(user);
        }
        throw new Exception("User already exists");
    }

    private User findUserByUserName(String username){
        return repository.findUserByUsername(username);
    }


}


