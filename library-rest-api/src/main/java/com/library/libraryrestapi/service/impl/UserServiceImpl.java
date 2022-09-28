package com.library.libraryrestapi.service.impl;

import com.library.libraryrestapi.dto.UserDto;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.exceptions.userExceptions.UserAlreadyExistsException;
import com.library.libraryrestapi.exceptions.userExceptions.UserIdNotFoundException;
import com.library.libraryrestapi.exceptions.userExceptions.UserNotFoundException;
import com.library.libraryrestapi.repository.UserRepository;
import com.library.libraryrestapi.repository.VerificationTokenRepository;
import com.library.libraryrestapi.service.UserService;
import com.library.libraryrestapi.token.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
public class UserServiceImpl implements UserService {








    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;




    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);

    }

    @Override
    public void saveVerificationTokenForUser(String token, User user){
        VerificationToken verificationToken=new VerificationToken(user,token);
        verificationTokenRepository.save(verificationToken);



    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if (verificationToken==null){
            return "invalid";
        }
        User user=verificationToken.getUser();
        Calendar cal=Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            verificationTokenRepository.delete(verificationToken);
            userRepository.delete(user);
            return "expired";

        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }












    @Override
    public User newUserAccount(UserDto userDto) throws UserAlreadyExistsException {

        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
                throw new UserAlreadyExistsException(userRepository.findUserByEmail(userDto.getEmail()).getUsername());
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return  user;
    }


    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) throws UserIdNotFoundException {

            if (userRepository.findById(userId).isPresent()){
                return userRepository.findById(userId).get();
            }
            throw new UserIdNotFoundException(userId);
    }

    @Override
    public User updateUser(UserDto userDto) throws UserNotFoundException, UserAlreadyExistsException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user=userRepository.findByUsername(username);
        if (user!=null){
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            if (userRepository.findByUsername(username)!=null || userRepository.findUserByEmail(userDto.getEmail())!=null ){
                throw new UserAlreadyExistsException(username);
            }
            else {
                user.setUsername(userDto.getUsername());
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setEmail(userDto.getEmail());
                return userRepository.save(user);
            }
        }
        else {

            throw new UserNotFoundException(username);
        }

    }


    @Transactional
    @Override
    public String deleteUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user=userRepository.findByUsername(username);
        verificationTokenRepository.deleteByUser(user);
        userRepository.delete(user);


        return "Success";
    }
}