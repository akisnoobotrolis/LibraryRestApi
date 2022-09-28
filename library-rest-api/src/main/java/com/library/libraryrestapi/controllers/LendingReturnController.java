package com.library.libraryrestapi.controllers;



import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyLentException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.lendingExceptions.LendingNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;
import com.library.libraryrestapi.service.LendingReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LendingReturnController {

    @Autowired
    LendingReturnService lendingReturnService;

    @PostMapping("books/lend/{id}")
    public String lendBook(@PathVariable Long id) throws UserIsNotEnabledException, BookNotFoundException, BookAlreadyLentException {
        return lendingReturnService.lendBook(id);
    }


    @DeleteMapping ("books/return/{id}")
    public String returnBook(@PathVariable Long id) throws UserIsNotEnabledException, BookNotFoundException, LendingNotFoundException {
        return lendingReturnService.returnBook(id);
    }





}
