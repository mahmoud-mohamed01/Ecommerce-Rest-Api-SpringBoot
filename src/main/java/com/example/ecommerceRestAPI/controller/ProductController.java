package com.example.ecommerceRestAPI.controller;

import com.example.ecommerceRestAPI.dto.ErrorMessage;
import com.example.ecommerceRestAPI.dto.LoginBody;
import com.example.ecommerceRestAPI.dto.Message;
import com.example.ecommerceRestAPI.dto.ProductDto;
import com.example.ecommerceRestAPI.exception.ProductNotFoundException;
import com.example.ecommerceRestAPI.model.Product;
import com.example.ecommerceRestAPI.service.impl.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PostMapping
    public ResponseEntity<Object> addproduct(@Valid @RequestBody ProductDto productDto, Errors errors) {

        System.out.println(productDto);
        if (errors.hasErrors()) {
            //get validation error messages
            String message = errors.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining("; "));

            return new ResponseEntity<>(new ErrorMessage(message), HttpStatus.BAD_REQUEST);
        }

        ProductDto newProduct = productService.addProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct( @PathVariable Long id) {

        System.out.println(id);
        try {
            productService.deleteProductById(id);
            return new ResponseEntity(new Message("deleted "), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("product with this id not found"), HttpStatus.NOT_FOUND);
        }

    }
}