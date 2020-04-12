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

    public User findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }

    public User findUserById(Long id){
        return repository.findUserById(id);
    }

    public Boolean deleteUser(Long id){
        User user = findUserById(id);
        if(user != null){
            repository.delete(user);
            return true;
        }
        return false;
    }


}


