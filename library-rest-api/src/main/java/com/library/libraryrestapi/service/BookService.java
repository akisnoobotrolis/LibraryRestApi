package com.library.libraryrestapi.service;


import com.library.libraryrestapi.dto.BookDto;
import com.library.libraryrestapi.entity.Book;
import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyExistsException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;

import java.util.List;


public interface BookService {


   Book addBook(BookDto bookDto) throws BookAlreadyExistsException, UserIsNotEnabledException;

    Book updateBook(Long bookId, BookDto bookDto) throws BookNotFoundException, UserIsNotEnabledException;

    Book findById(Long id) throws BookNotFoundException;

    List<Book> findAllBooks();



    String deleteById(Long id) throws UserIsNotEnabledException;


    Book findByTitle(String title);


}