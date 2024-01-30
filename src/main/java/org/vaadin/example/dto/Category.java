package org.vaadin.example.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class Category {
    int category_id;
    String category;
    String googleMaterialIcon;
    List<Product> productList;

}
