package com.example.ecommerceRestAPI.repository;

import com.example.ecommerceRestAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {

}
