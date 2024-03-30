package com.example.ecommerceRestAPI.controller;

import com.example.ecommerceRestAPI.dto.LoginBody;
import com.example.ecommerceRestAPI.model.Product;
import com.example.ecommerceRestAPI.service.impl.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PostMapping("/prod")
    public ResponseEntity<Object> testparm(@RequestBody Object id){
        System.out.println("prodID: "+id.toString());
        return ResponseEntity.ok("gggg");
    }
}
