package com.example.ecommerceRestAPI.service.impl;

import com.example.ecommerceRestAPI.dto.LoginBody;
import com.example.ecommerceRestAPI.dto.UserDto;
import com.example.ecommerceRestAPI.exception.UserAlreadyExistException;
import com.example.ecommerceRestAPI.mapper.UserMapper;
import com.example.ecommerceRestAPI.model.WebUser;
import com.example.ecommerceRestAPI.repository.UserRepository;
import com.example.ecommerceRestAPI.service.WebUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WebUserServiceImpl implements WebUserService  {
    private UserRepository userRepository;
    private EncryptionService encryptionService;

    private JWTService jwtService;

    @Override
    public UserDto registerUser(UserDto userDto) throws UserAlreadyExistException {
        WebUser webUser= UserMapper.mapToUser(userDto);
        if (userRepository.findByUserNameIgnoreCase(webUser.getUserName()).isPresent() ||
                userRepository.findByEmailIgnoreCase(webUser.getEmail()).isPresent()){
            throw new UserAlreadyExistException();


        }
        String hashedPassword=encryptionService.encryptPassword(webUser.getPassword());
        webUser.setPassword(hashedPassword);
        return UserMapper.mapToUserDto( userRepository.save(webUser));

    }

    @Override
    public String loginUser(LoginBody loginBody) {
        Optional<WebUser> existUser=userRepository.findByUserNameIgnoreCase(loginBody.getUsername());
        if (existUser.isPresent()){
            WebUser user=existUser.get();
            if (encryptionService.verifyPassowrd(loginBody.getPassword(),user.getPassword()))
            {
               return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
