package org.vaadin.example.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Cartitem {

    private int id;
    private int quantity;
    private double totalPrice;
    private Cart cart;

    private Product product;
}
