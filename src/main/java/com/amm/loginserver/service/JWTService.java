package com.amm.loginserver.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JWTService {
    private KeyStore keyStore;

    @PostConstruct
    public void init() throws Exception {
      try{
        keyStore = KeyStore.getInstance("Jks");
        InputStream resourceAsStream = getClass().getResourceAsStream("/palindromeBrio.jks");
        keyStore.load(resourceAsStream, "password".toCharArray());
        }catch(KeyStoreException |CertificateException |NoSuchAlgorithmException | IOException e) {
          throw new Exception("Exception occurred while loading");
      }
    }

    public Key getPrivateKey() throws Exception{
        try{
            return (PrivateKey) keyStore.getKey("PalindromeBrio", "password".toCharArray());
        }catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new Exception("Exception occurred while retrieving private key");
        }
    }

    public PublicKey getPublicKey() throws Exception{
        try {
            return keyStore.getCertificate("PalindromeBrio").getPublicKey();
        }catch (KeyStoreException e) {
            throw new Exception("Exception occurred while retrieving public key");
        }
    }

    public String generateToken(Authentication authentication) throws Exception{
        User user = (User) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername()).signWith(getPrivateKey()).compact();
    }

    public boolean validateToken(String jwt) throws Exception {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJWT(String token) throws Exception{
        Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
