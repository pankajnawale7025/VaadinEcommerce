package org.vaadin.example.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor

public class Cart {

    private int id;


    private Customer customer;
    List<Cartitem> cartitemList;


}
