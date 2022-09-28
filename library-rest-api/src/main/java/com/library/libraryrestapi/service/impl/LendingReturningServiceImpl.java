package com.library.libraryrestapi.service.impl;

import com.library.libraryrestapi.entity.Book;
import com.library.libraryrestapi.entity.Lending;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.exceptions.bookExceptions.BookAlreadyLentException;
import com.library.libraryrestapi.exceptions.bookExceptions.BookNotFoundException;
import com.library.libraryrestapi.exceptions.lendingExceptions.LendingNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIsNotEnabledException;
import com.library.libraryrestapi.repository.BookRepository;
import com.library.libraryrestapi.repository.LendingRepository;
import com.library.libraryrestapi.repository.UserRepository;
import com.library.libraryrestapi.service.LendingReturnService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
public class LendingReturningServiceImpl implements LendingReturnService {





    @Autowired
    BookRepository bookRepository;

    @Autowired
    LendingRepository lendingRepository;

    @Autowired
    UserRepository userRepository;





    @Override
    public String  lendBook(Long bookId) throws BookNotFoundException, BookAlreadyLentException, UserIsNotEnabledException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();


            if (!book.isLent()) {
                book.setLent(true);
                bookRepository.save(book);
                Lending lending=new Lending();
                lending.setBook(book);

                User user = userRepository.findByUsername(username);
                if (user.isEnabled()) {
                    lending.setUser(user);
                    lendingRepository.save(lending);

                    return "Success";
                }
                else {
                    throw new UserIsNotEnabledException(username);
                }

            }
            else {
                throw new BookAlreadyLentException(bookId);
            }
        }
        else {throw new BookNotFoundException(bookId);}

    }




    @Transactional
    @Override
    public String returnBook(Long bookId) throws BookNotFoundException, LendingNotFoundException, UserIsNotEnabledException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Lending lending = lendingRepository.findByBook(book);
            if (lending!=null) {
                if (userRepository.findByUsername(username).isEnabled()) {
                    book.setLent(false);
                    bookRepository.save(book);
                    lendingRepository.delete(lending);
                    return "Success";
                }
                else throw new UserIsNotEnabledException(username);
            }
            else {
                throw new LendingNotFoundException(bookId);
            }
        }
        else {
            throw new BookNotFoundException(bookId);
        }

    }
}