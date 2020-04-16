package com.amm.loginserver.model;

public class AuthenticationResponse {
    private String authenticationToken;
    private String username;

    public AuthenticationResponse(String authenticationToken, String username){
        this.authenticationToken = authenticationToken;
        this.username = username;
    }


}
