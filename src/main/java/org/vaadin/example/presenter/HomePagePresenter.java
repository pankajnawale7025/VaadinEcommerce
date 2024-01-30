package org.vaadin.example.presenter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.dto.Category;

import org.vaadin.example.dto.Product;
import org.vaadin.example.service.CategoryService;
import org.vaadin.example.service.ProductService;


import java.util.List;

@Service

public class HomePagePresenter {


    CategoryService categoryService;

    ProductService productService;

    @Autowired
    public HomePagePresenter(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    public List<Category> listOfProductByCategory() {
        return categoryService.listOfProductByCategory();
    }


    public List<Product> listOfProducts() {
        return productService.listOfProductByCategory();


    }
}







