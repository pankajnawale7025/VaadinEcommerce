package org.vaadin.example.main;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Product;
import org.vaadin.example.presenter.ProductDetailPresenter;

import java.util.List;
import java.util.Map;

@Route(value = "productdetails", layout = MainLayout.class)
@PageTitle("product-details")


public class ProductDetails extends VerticalLayout implements HasUrlParameter<String> {


    String parameter = "xyz";

    @Autowired
    ProductDetailPresenter productDetailPresenter;

    @PostConstruct
    public void buildProductDetail() {
        if (!this.parameter.equals("xyz")) {
            Product product = productDetailPresenter.getProduct(this.parameter);

            getProductdiv(product);

            add(new Paragraph("Parameter is " + parameter));
        }

    }

    private void getProductdiv(Product product) {
        Div div = new Div();

        add();
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        System.out.println(s);
        this.parameter = s;
        buildProductDetail();

    }
}



