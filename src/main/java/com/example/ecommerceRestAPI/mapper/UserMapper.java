package com.example.ecommerceRestAPI.mapper;

import com.example.ecommerceRestAPI.dto.UserDto;
import com.example.ecommerceRestAPI.model.WebUser;

public class UserMapper {

    public static WebUser mapToUser(UserDto userDto){
      WebUser user=new WebUser();
      user.setUserName(userDto.getUsername());
      user.setEmail(userDto.getEmail());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());

      //ToDo: encrypt password
        user.setPassword(userDto.getPassword());

        return user;

    }


    public static UserDto mapToUserDto(WebUser user){

        return new UserDto(
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
        );

    }









}
