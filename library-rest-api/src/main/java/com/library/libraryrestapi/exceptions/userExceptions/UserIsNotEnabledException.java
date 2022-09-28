package com.library.libraryrestapi.exceptions.userExceptions;

public class UserIsNotEnabledException extends java.lang.Exception {

    public UserIsNotEnabledException (String username) {
        super("The user with username" + username + "was not found");

    }




}