package org.vaadin.example.main;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Category;
import org.vaadin.example.dto.Product;
import org.vaadin.example.presenter.HomePagePresenter;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "home", layout = MainLayout.class)
public class HomePage extends VerticalLayout {


    HomePagePresenter homePagePresenter;

    @Autowired
    public HomePage(HomePagePresenter homePagePresenter) {

        this.homePagePresenter = homePagePresenter;

        HorizontalLayout categoryLayout = new HorizontalLayout();
        categoryLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        categoryLayout.setSizeFull();
        categoryLayout.add(getCategoryDiv());

        HorizontalLayout productsLayout = new HorizontalLayout();
        productsLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        productsLayout.setSizeFull();
        productsLayout.add(getProductListDiv());

        add(categoryLayout, productsLayout);
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
                categoryChildDiv.add(new H2(category));
                categoryChildDiv.add(new Button(category));
                categoryChildDiv.addClassName("category-child-div");
                categoryParentDiv.add(categoryChildDiv);
            }

        return categoryParentDiv;
    }

    public Component getProductListDiv() {
        Div productParentDiv = new Div();
        productParentDiv.addClassName("product-parent-div");
        List<Product> productsList = homePagePresenter.listOfProducts();
        List<Div> collect = productsList.stream().map(x ->
                {

                    Div productChildDiv = new Div();
                    productChildDiv.addClassName("product-child-div");
                    // image
                    Div imagediv = new Div();
                    imagediv.getStyle().set("display", "flex").set("justify-content", "center");


                    String imageurl = x.getImageurl();
                    Image image = new Image(imageurl, "Product-image");
                    image.getStyle().set("height", "115px");
                    imagediv.add(image);
                    productChildDiv.add(imagediv);

                    //name

                    Paragraph productName = new Paragraph(x.getName());
                    productName.getStyle().set("font-size", "larger").set("font-weight", "600");

                    //pricing div
                    Div priceDiv = new Div();
                    priceDiv.add(new Paragraph("Rs" + x.getPrice()));
                    priceDiv.add(new Paragraph("Actual Price" + x.getPrice() + (x.getPrice() * x.getDiscount())));
                    priceDiv.add(new Paragraph(String.valueOf(x.getPrice())));
                    priceDiv.getStyle().set("display", "flex").set("justify-content", "space-around");
                    productChildDiv.add(imagediv);
                    productChildDiv.add(productName);
                    productChildDiv.add(priceDiv);
                    return productChildDiv;
                }

        ).toList();


        for (Div div : collect) {
            productParentDiv.add(div);
        }

        return productParentDiv;
    }


}

