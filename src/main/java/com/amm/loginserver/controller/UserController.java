package com.amm.loginserver.controller;

import com.amm.loginserver.model.User;
import com.amm.loginserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UserController {
    @Autowired
    private UserService service;
    //Testing to make sure deployed properly
    @GetMapping("/hello")
    private ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/login/newuser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws Exception {
        String username = user.getUsername();
        return new ResponseEntity<>(service.addUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/login")
    public ResponseEntity<Boolean> deleteUser(@RequestParam Long id){
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }
}
