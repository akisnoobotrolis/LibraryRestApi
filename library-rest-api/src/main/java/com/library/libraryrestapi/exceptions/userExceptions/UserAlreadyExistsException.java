package com.library.libraryrestapi.exceptions.userExceptions;


import com.library.libraryrestapi.entity.User;

public class UserAlreadyExistsException extends java.lang.Exception {


    public UserAlreadyExistsException(String username) {
        super("The user with username" + username + "was not found");

    }




}