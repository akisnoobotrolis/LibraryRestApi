package com.library.libraryrestapi.exceptions.bookExceptions;

public class BookNotFoundException extends java.lang.Exception {

    public BookNotFoundException(Long id) {
        super("The book with the id" + id + "was not found");

    }




}