package com.library.libraryrestapi.controllers;

import com.library.libraryrestapi.dto.UserDto;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.exceptions.userExceptions.UserAlreadyExistsException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIdNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserNotFoundException;
import com.library.libraryrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users/findAllUsers")
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }


    @GetMapping(value = "/users/info/{id}")
    public User findUser(@PathVariable Long id) throws UserIdNotFoundException {
        return userService.findUserById(id);
    }

    @PutMapping("users/update/myAccount")
    public User updateUser(@RequestBody UserDto userDto) throws UserNotFoundException, UserAlreadyExistsException {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("users/delete/myAccount")
    public String deleteUser(){
        return userService.deleteUser();
    }
}