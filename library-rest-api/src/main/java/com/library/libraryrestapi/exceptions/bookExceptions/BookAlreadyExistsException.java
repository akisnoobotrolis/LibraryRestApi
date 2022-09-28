package com.library.libraryrestapi.exceptions.bookExceptions;

import com.library.libraryrestapi.entity.Book;


public class BookAlreadyExistsException extends java.lang.Exception {


    public BookAlreadyExistsException(String title) {
        super("The book with the title" + title +" already exists" );

    }




}