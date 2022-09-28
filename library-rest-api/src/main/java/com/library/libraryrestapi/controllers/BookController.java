package com.library.libraryrestapi.controllers;

import com.library.libraryrestapi.dto.BookDto;

import com.library.libraryrestapi.entity.Book;

import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyExistsException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;
import com.library.libraryrestapi.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books/findAllBooks")
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }


    @GetMapping(value = "/books/info/id/{id}")
    public Book findBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping(value = "/books/info/title/{title}")
    public Book findBook(@PathVariable String title){
        return bookService.findByTitle(title);
    }



    @PutMapping("books/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) throws UserIsNotEnabledException, BookNotFoundException {
        return bookService.updateBook(id,bookDto);
    }

    @DeleteMapping("books/delete/{id}")
    public String deleteBook(@PathVariable Long id) throws UserIsNotEnabledException {
        return bookService.deleteById(id);
    }

    @PostMapping("books/add")
    public Book newBook(@RequestBody BookDto bookDto) throws UserIsNotEnabledException, BookAlreadyExistsException {
        return bookService.addBook(bookDto);
    }
}