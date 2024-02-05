package org.vaadin.example.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Product;
import org.vaadin.example.service.ProductService;

public class ProductDetailPresenter {

    ProductService productService;


    @Autowired
    public ProductDetailPresenter(ProductService productService) {
        this.productService = productService;

    }


    public Product getProduct(String productId) {
        return productService.getProduct(productId);
    }
}
