package com.example.ecommerceRestAPI.service.impl;


import com.example.ecommerceRestAPI.model.Product;
import com.example.ecommerceRestAPI.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

   private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
