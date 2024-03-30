package com.example.ecommerceRestAPI.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.ecommerceRestAPI.model.WebUser;
import com.example.ecommerceRestAPI.repository.UserRepository;
import com.example.ecommerceRestAPI.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class isAuth extends OncePerRequestFilter {
   private JWTService jwtService;
   private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader=request.getHeader("Authorization");
        if (tokenHeader!=null &&tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            try {
                String userName= jwtService.getUserName(token);
                Optional<WebUser> existUser=userRepository.findByUserNameIgnoreCase(userName);
                if (existUser.isPresent()) {
                    WebUser user = existUser.get();
                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>()) ;
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }


            }
            catch (JWTDecodeException exception){
                System.out.println("invalid token");
            }

        }

        filterChain.doFilter(request,response);



    }
}
