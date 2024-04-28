package com.example.ecommerceRestAPI.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String shortDescription;
    @NotNull
    @NotBlank
    private String longDescription;
    @PositiveOrZero
    private Double price;
}
