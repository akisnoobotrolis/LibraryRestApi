package com.library.libraryrestapi.service;

import com.library.libraryrestapi.dto.UserDto;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.exceptions.userExceptions.UserAlreadyExistsException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIdNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserNotFoundException;
import com.library.libraryrestapi.token.VerificationToken;

import java.util.List;



public interface UserService {
    User findUserByEmail(String username);

    User findUserById(Long userId) throws UserIdNotFoundException;


    User newUserAccount(UserDto userDto) throws UserAlreadyExistsException;

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    List<User> findAllUsers();

    VerificationToken generateNewVerificationToken(String oldToken);

    User updateUser(UserDto userDto) throws UserNotFoundException, UserAlreadyExistsException;


    String deleteUser();






}
