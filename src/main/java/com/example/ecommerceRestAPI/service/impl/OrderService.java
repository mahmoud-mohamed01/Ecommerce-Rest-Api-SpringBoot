package com.example.ecommerceRestAPI.service.impl;

import com.example.ecommerceRestAPI.model.UserOrder;
import com.example.ecommerceRestAPI.model.WebUser;
import com.example.ecommerceRestAPI.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

   public List<UserOrder> getOrders(WebUser user){
       return orderRepository.findByUser(user);
   }
}
