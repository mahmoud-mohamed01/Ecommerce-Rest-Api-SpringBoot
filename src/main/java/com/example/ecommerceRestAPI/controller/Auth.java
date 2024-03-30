package com.example.ecommerceRestAPI.controller;


import com.example.ecommerceRestAPI.dto.ErrorMessage;
import com.example.ecommerceRestAPI.dto.JwtResponse;
import com.example.ecommerceRestAPI.dto.LoginBody;
import com.example.ecommerceRestAPI.dto.UserDto;
import com.example.ecommerceRestAPI.exception.UserAlreadyExistException;
import com.example.ecommerceRestAPI.model.WebUser;
import com.example.ecommerceRestAPI.service.WebUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class Auth {
    private WebUserService webUserService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDto userDto, Errors errors){
        UserDto newUser= null;
        if (errors.hasErrors()){

            //get validation error messages
          String message=errors.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining("; "));

            return new ResponseEntity<>(new ErrorMessage(message), HttpStatus.BAD_REQUEST);
        }


        try {
            newUser = webUserService.registerUser(userDto);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>("user already exist",HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginBody loginBody,Errors errors){
        if (errors.hasErrors()){

            //get validation error messages
            String message=errors.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining("; "));

            return new ResponseEntity<>(new ErrorMessage(message), HttpStatus.BAD_REQUEST);
        }
        String jwt=webUserService.loginUser(loginBody);
        if (jwt == null){
            return new ResponseEntity<>(new ErrorMessage("not correct userName or Password"), HttpStatus.BAD_REQUEST);
        }
        else {

            return ResponseEntity.ok(new JwtResponse(jwt));
        }

    }


    @GetMapping("/me")
    public ResponseEntity getLoggedinUser(@AuthenticationPrincipal WebUser user){
        if (user!=null)
            return ResponseEntity.ok(user);
        else {
            return new  ResponseEntity(new ErrorMessage("not Autherized user"),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

    }



}
