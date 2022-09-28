package com.library.libraryrestapi.requestAndResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class AuthenticationResponse {


    private final String jwt;
    public AuthenticationResponse(String jwt){
        this.jwt=jwt;
    }
    public String getJwt(){return jwt;}
}