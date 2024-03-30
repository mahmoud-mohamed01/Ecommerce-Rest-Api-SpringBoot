package com.example.ecommerceRestAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long Id;

    @Column(nullable = false,length = 512)
    private String addressLine1;

    @Column(length = 512)
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false,length = 75)
    private String country;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private WebUser user;

}
