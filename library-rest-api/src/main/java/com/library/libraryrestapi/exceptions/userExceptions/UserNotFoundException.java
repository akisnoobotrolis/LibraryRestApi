package com.library.libraryrestapi.exceptions.userExceptions;

public class UserNotFoundException extends java.lang.Exception {

    public UserNotFoundException(String username) {
        super("The user with username" + username + "was not found");

    }




}