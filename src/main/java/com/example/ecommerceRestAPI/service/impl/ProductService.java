package com.example.ecommerceRestAPI.service.impl;


import com.example.ecommerceRestAPI.dto.ProductDto;
import com.example.ecommerceRestAPI.exception.ProductNotFoundException;
import com.example.ecommerceRestAPI.mapper.ProductMapper;
import com.example.ecommerceRestAPI.model.Product;
import com.example.ecommerceRestAPI.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

   private ProductRepository productRepository;

   //get all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //add new product
    public ProductDto addProduct(ProductDto productDto){
        Product product=productRepository.save(ProductMapper.mapToProduct(productDto));
        return ProductMapper.mapToProductDto(product);
    }

    public void deleteProductById(Long id) throws ProductNotFoundException {
        if (!productRepository.findById(id).isPresent()){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);

    }

}
