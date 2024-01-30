package org.vaadin.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Setter
@Getter
@ToString
public class Customer {
    private int id=32;
    @NotBlank
    private String name;
    @NotBlank
    private String surName;
    @NotBlank
    private String contactNumber;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String address;
    @NotBlank
    private String password;

    private Cart cart;
    private List<CustomerOrder> customerOrderList;
    public Customer() {

    }

}
