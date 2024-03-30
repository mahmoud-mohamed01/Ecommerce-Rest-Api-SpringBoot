package com.example.ecommerceRestAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "product_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;


    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String longDescription;


    @Column(nullable = false)
    private Double price;

    @OneToOne(mappedBy = "product",cascade = CascadeType.REMOVE,optional = false,orphanRemoval = true)
    private Inventory inventory;


}
