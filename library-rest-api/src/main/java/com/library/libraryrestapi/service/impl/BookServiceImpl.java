package com.library.libraryrestapi.service.impl;

import com.library.libraryrestapi.dto.BookDto;
import com.library.libraryrestapi.entity.Book;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyExistsException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;
import com.library.libraryrestapi.repository.BookRepository;
import com.library.libraryrestapi.repository.UserRepository;
import com.library.libraryrestapi.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {










    @Autowired
    private BookRepository bookRepository;









    @Autowired
    private UserRepository userRepository;





    @Override
    public Book addBook(BookDto bookDto) throws BookAlreadyExistsException, UserIsNotEnabledException {

       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

      String username = userDetails.getUsername();

    if(bookRepository.findByTitle(bookDto.getTitle())!=null){
        throw new BookAlreadyExistsException(bookDto.getTitle());
    }
    else {
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublisher(bookDto.getPublisher());
        User user = userRepository.findByUsername(username);
        if (user.isEnabled()) {
            bookRepository.save(book);
            return book;
        }
        else {
            throw new UserIsNotEnabledException(user.getUsername());
        }
    }
    }

    @Override
    public Book updateBook(Long bookId, BookDto bookDto) throws BookNotFoundException, UserIsNotEnabledException {
       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

       String username = userDetails.getUsername();
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isPresent()){
            Book book;
            book = optionalBook.get();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setNumberOfPages(bookDto.getNumberOfPages());
            book.setPublisher(bookDto.getPublisher());
            User user = userRepository.findByUsername(username);
            if (user.isEnabled()) {
                return bookRepository.save(book);
            }
            else {
                throw new UserIsNotEnabledException(username);
            }

        }
        else {
            throw new BookNotFoundException(bookId);
        }


    }

    @Override
    public Book findById(Long id) throws BookNotFoundException {




        Optional<Book> optionalBook= bookRepository.findById(id);
        if (optionalBook.isPresent()){
            return optionalBook.get();
        }
        else {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    public List<Book> findAllBooks() {
         return bookRepository.findAll();
    }



    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }


    @Override
    public String deleteById(Long id) throws UserIsNotEnabledException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        if (userRepository.findByUsername(username).isEnabled()) {
            bookRepository.deleteById(id);
        }
        else {
            throw new UserIsNotEnabledException(username);
        }
    return "Success";
    }


}