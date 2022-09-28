package com.library.libraryrestapi.exceptions.bookExceptions;

public class BookAlreadyLentException extends java.lang.Exception {

    public BookAlreadyLentException(Long id) {
        super("The book with the id" + id + "is already lent");

    }




}