package org.vaadin.example.main;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Category;
import org.vaadin.example.dto.Customer;
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
    String customerName = null;

    @PostConstruct
    public void postConstruct() {

        getProductList();

        customerName = (String) VaadinSession.getCurrent().getAttribute("name");
        log.info("Customer is {}", customerName);
        Button reloadThePageButton = new Button("Reload the page ");
        reloadThePageButton.addClickListener(e -> {
            UI.getCurrent().getPage().reload();
        });

        Button nvbtn = new Button("Navigate button ");
        nvbtn.addClickListener(e -> {
            String url = "productdetails/" + "123";
            UI.getCurrent().navigate(url);
        });


        productList.getStyle().setDisplay(Style.Display.FLEX).setFlexWrap(Style.FlexWrap.WRAP);


        add(reloadThePageButton, nvbtn, getCategoryDiv(), productList);
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
        Paragraph priceParagraph = new Paragraph("₹" + String.valueOf(product.getPrice()));
        priceParagraph.getStyle().set("font-size", "large").set("font-weight", "700");

        priceDiv.add(priceParagraph);

        Paragraph originalPriceParagraph = new Paragraph("₹" + String.valueOf(product.getPrice() + (product.getPrice() * product.getDiscount())));
        originalPriceParagraph.getStyle().set("font-size", "large").set("font-weight", "600");
        originalPriceParagraph.addClassName("strikethrough-text");


        priceDiv.add(originalPriceParagraph);

        Paragraph discountparagraph = new Paragraph(String.valueOf(product.getDiscount()) + "%");
        discountparagraph.getStyle().set("color", "green");

        priceDiv.add(discountparagraph);
        priceDiv.getStyle().set("display", "flex").set("justify-content", "space-around");
        productChildDiv.add(imagediv);
        productChildDiv.add(productName);
        productChildDiv.add(priceDiv);
        productChildDiv.addClickListener(e -> {
            String url = "productdetails/" +String.valueOf( product.getId());
            UI.getCurrent().navigate(url);
        });
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
        if (customerName != null) {
            Notification.show("Welcome " + customerName + "...");
        }
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
