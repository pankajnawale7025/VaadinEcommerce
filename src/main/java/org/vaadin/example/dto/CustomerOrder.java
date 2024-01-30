package org.vaadin.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class CustomerOrder {

    private int id;
    private double finalTotalPrice;
    private LocalDate localDate;
    @JsonIgnore

    Customer customer;


    List<Orderitem> orderitemList;


}
