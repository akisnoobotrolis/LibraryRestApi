package com.library.libraryrestapi.service;


import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyLentException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.lendingExceptions.LendingNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;

public interface LendingReturnService {





    String   lendBook(Long bookId) throws BookNotFoundException, BookAlreadyLentException, UserIsNotEnabledException;

    String returnBook(Long bookId) throws BookNotFoundException, LendingNotFoundException, UserIsNotEnabledException;
}