package com.library.libraryrestapi.exceptions.lendingExceptions;



public class LendingNotFoundException extends java.lang.Exception {

    public LendingNotFoundException(Long id) {
        super("The Lending for book with the id" + id + "was not found");

    }




}