package com.library.libraryrestapi.controllers;

import com.library.libraryrestapi.dto.UserDto;
import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.event.RegistrationCompleteEvent;
import com.library.libraryrestapi.exceptions.userExceptions.UserAlreadyExistsException;
import com.library.libraryrestapi.repository.UserRepository;
import com.library.libraryrestapi.repository.VerificationTokenRepository;
import com.library.libraryrestapi.service.UserService;
import com.library.libraryrestapi.token.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
public class RegistrationController {


    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;




    @Autowired
    private ApplicationEventPublisher publisher;


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto, final HttpServletRequest request) throws UserAlreadyExistsException {
        User user= userService.newUserAccount(userDto);

        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));

        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }








    @GetMapping("/resendVerifyToken")
    public  String resendVerificationToken(@RequestParam("token" )String oldToken, HttpServletRequest request){
        VerificationToken verificationToken=userService.generateNewVerificationToken(oldToken);
        User user= verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return  "verification link resent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken)
    {
        String url= applicationUrl+ "/verifyRegistration?token="+verificationToken.getToken();




        log.info("Click the link to verify your account :{}",url);
    }


    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result=userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User verified";
        }
        else {

            return "User did not verify";
        }
    }
}