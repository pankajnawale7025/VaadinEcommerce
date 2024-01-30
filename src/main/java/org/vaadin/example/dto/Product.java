package org.vaadin.example.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Product {


    private int id;
    @Size(min = 4, max = 100, message = "size  of name be greater than 4 alphabets  and less tha 20 alphabets")

    private String name;
    private double price;

    private String imageurl;


    Category categoryInProduct;


    @Min(0)
    @Max(99)
    private double discount;
    @Min(0)
    private int stockQuantity;


}
