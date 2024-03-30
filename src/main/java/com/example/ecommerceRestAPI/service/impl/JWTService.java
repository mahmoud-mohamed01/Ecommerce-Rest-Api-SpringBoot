package com.example.ecommerceRestAPI.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ecommerceRestAPI.model.WebUser;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class JWTService {

    @Value("${jwt.alogorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;


    @PostConstruct
    public void postConstuct(){
        algorithm=Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(WebUser user){
       return JWT.create().
                withClaim("USER_NAME",user.getUserName()).
                withIssuer(issuer).
                withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSeconds))).
                sign(algorithm);
    }

    public String getUserName(String token){
        return JWT.decode(token).getClaim("USER_NAME").asString();
    }


}
