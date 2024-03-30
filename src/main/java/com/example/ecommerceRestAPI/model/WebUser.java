package com.example.ecommerceRestAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "WebUsers")
public class WebUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true)
    private String email;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @JsonIgnore
    @Column(nullable = false,length = 1000)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Adress> adresses=new ArrayList<>();



    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<UserOrder> Orders=new ArrayList<>();



}
