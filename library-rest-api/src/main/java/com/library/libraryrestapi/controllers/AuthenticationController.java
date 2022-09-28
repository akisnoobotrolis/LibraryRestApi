package com.library.libraryrestapi.controllers;

import com.library.libraryrestapi.repository.UserRepository;
import com.library.libraryrestapi.requestAndResponse.AuthenticationRequest;
import com.library.libraryrestapi.requestAndResponse.AuthenticationResponse;
import com.library.libraryrestapi.service.MyUserDetailsService;
import com.library.libraryrestapi.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtility jwtUtility;




    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("Incorrect username or password", badCredentialsException);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userRepository.findByUsername(userDetails.getUsername()).isEnabled()) {
            final String jwt = jwtUtility.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }else {
            throw new Exception("User is not Verified");
        }
    }
}
