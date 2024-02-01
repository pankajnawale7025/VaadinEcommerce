package org.vaadin.example.main;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Category;
import org.vaadin.example.dto.Product;
import org.vaadin.example.presenter.HomePagePresenter;
import org.vaadin.example.service.ProductService;

import java.util.HashMap;
import java.util.List;


@Slf4j
@Route(value = "home", layout = MainLayout.class)
public class Home extends VerticalLayout {

    OrderedList productList = new OrderedList();
    HomePagePresenter homePagePresenter;

    @PostConstruct
    public void postConstruct() {
        getProductList();
        add(new H1("Hello"), getCategoryDiv(), productList);
    }

    ProductService productService;

    @Autowired
    public Home(ProductService productService, HomePagePresenter homePagePresenter) {
        this.productService = productService;
        this.homePagePresenter = homePagePresenter;

    }


    public Component getProduct(Product product) {

        Div productChildDiv = new Div();
        productChildDiv.addClassName("product-child-div");
        // image
        Div imagediv = new Div();
        imagediv.getStyle().set("display", "flex").set("justify-content", "center");


        String imageurl = product.getImageurl();
        Image image = new Image(imageurl, "Product-image");
        image.getStyle().set("height", "115px");
        imagediv.add(image);
        productChildDiv.add(imagediv);

        //name

        Paragraph productName = new Paragraph(product.getName());
        productName.getStyle().set("font-size", "larger").set("font-weight", "700");

        //pricing div
        Div priceDiv = new Div();
        priceDiv.add(new Paragraph("₹" + String.format("%,f", product.getPrice())));
        priceDiv.add(new Paragraph("Actual Price" + product.getPrice() + (product.getPrice() * product.getDiscount())));
        priceDiv.add(new Paragraph(String.valueOf(product.getPrice())));
        priceDiv.getStyle().set("display", "flex").set("justify-content", "space-around");
        productChildDiv.add(imagediv);
        productChildDiv.add(productName);
        productChildDiv.add(priceDiv);
        return productChildDiv;
    }

    public void removeProductFromList() {
        productList.removeAll();
    }

    // on clickcategory div
    public void getProductList(Integer categoryId) {
        productList.removeAll();

        for (Product product : productService.listOfProductByCategory(categoryId)) {
            Div div = new Div();
            div.add(getProduct(product));
            log.info("product Name is {}", product.getName());
            productList.add(div);

        }
    }

    // load least when ui is created

    public void getProductList() {
        productList.removeAll();
        for (Product product : productService.listOfProductByCategory()) {
            Div div = new Div();
            div.add(getProduct(product));
            log.info("product Name is {}", product.getName());
            productList.add(div);

        }
    }


    public Component getCategoryDiv() {

        Div categoryParentDiv = new Div();
        categoryParentDiv.setWidthFull();
        categoryParentDiv.addClassName("category-parent-div");
        List<Category> categoryList = homePagePresenter.listOfProductByCategory();
        if (categoryList != null)
            for (Category value : categoryList) {
                Div categoryChildDiv = new Div();
                String category = value.getCategory();
                int categoryId = value.getCategory_id();
                categoryChildDiv.add(new H2(category));
                categoryChildDiv.add(new Button(category));
                categoryChildDiv.addClassName("category-child-div");
                categoryParentDiv.add(categoryChildDiv);
                categoryChildDiv.addClickListener(e -> getProductList(categoryId));
            }
        return categoryParentDiv;
    }

}
