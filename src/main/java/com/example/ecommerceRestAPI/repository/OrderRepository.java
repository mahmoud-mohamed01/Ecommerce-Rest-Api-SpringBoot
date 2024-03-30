package com.example.ecommerceRestAPI.repository;

import com.example.ecommerceRestAPI.model.UserOrder;
import com.example.ecommerceRestAPI.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<UserOrder,Long> {

    List<UserOrder> findByUser(WebUser user);



}
