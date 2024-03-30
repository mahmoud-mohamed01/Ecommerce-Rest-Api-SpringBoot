package com.example.ecommerceRestAPI.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    @Value("${encryption.salt.rounds}")
    private int saltRounds;

    private String salt;

    @PostConstruct
    public void postConstruct(){
        salt= BCrypt.gensalt(saltRounds);
    }

    public String encryptPassword(String pasword){
        return BCrypt.hashpw(pasword,salt);
    }

    public boolean verifyPassowrd(String password,String hash){
        return BCrypt.checkpw(password,hash);
    }
}
