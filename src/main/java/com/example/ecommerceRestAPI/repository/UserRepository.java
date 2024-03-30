package com.example.ecommerceRestAPI.repository;

import com.example.ecommerceRestAPI.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<WebUser,Long> {

    Optional<WebUser> findByUserNameIgnoreCase(String username);
    Optional<WebUser> findByEmailIgnoreCase(String email);

}
