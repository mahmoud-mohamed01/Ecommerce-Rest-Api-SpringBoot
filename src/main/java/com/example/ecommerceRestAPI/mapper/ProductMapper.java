package com.example.ecommerceRestAPI.mapper;

import com.example.ecommerceRestAPI.dto.ProductDto;
import com.example.ecommerceRestAPI.dto.UserDto;
import com.example.ecommerceRestAPI.model.Product;
import com.example.ecommerceRestAPI.model.WebUser;

public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setShortDescription(productDto.getShortDescription());
        product.setLongDescription(productDto.getLongDescription());
        product.setPrice(productDto.getPrice());


        return product;

    }


    public static ProductDto mapToProductDto(Product product) {

        return new ProductDto(
                product.getName(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice()
        );

    }
}