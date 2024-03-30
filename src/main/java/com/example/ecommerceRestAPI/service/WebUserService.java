package com.example.ecommerceRestAPI.service;


import com.example.ecommerceRestAPI.dto.LoginBody;
import com.example.ecommerceRestAPI.dto.UserDto;
import com.example.ecommerceRestAPI.exception.UserAlreadyExistException;

public interface WebUserService {

    UserDto registerUser(UserDto userDto) throws UserAlreadyExistException;
    String loginUser(LoginBody loginBody);

}
