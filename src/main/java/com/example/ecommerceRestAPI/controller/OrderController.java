package com.example.ecommerceRestAPI.controller;

import com.example.ecommerceRestAPI.model.UserOrder;
import com.example.ecommerceRestAPI.model.WebUser;
import com.example.ecommerceRestAPI.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Object> getUserOrders(@AuthenticationPrincipal WebUser user){
        List<UserOrder> userOrders=orderService.getOrders(user);
        return ResponseEntity.ok(userOrders);

    }
}
