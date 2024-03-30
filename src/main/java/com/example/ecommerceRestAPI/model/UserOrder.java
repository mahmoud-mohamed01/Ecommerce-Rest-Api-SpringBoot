package com.example.ecommerceRestAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "UserOrders")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private WebUser user;



    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id",nullable = false)
    private Adress adress;


    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<OrderQuantity> quantities=new ArrayList<>();
}
