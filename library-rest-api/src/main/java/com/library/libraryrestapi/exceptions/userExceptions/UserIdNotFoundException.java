package com.library.libraryrestapi.exceptions.userExceptions;

public class UserIdNotFoundException extends java.lang.Exception {

    public UserIdNotFoundException(Long id) {
        super("The user with id" + id + "was not found");

    }




}