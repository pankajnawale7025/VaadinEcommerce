package org.vaadin.example.main;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Product;
import org.vaadin.example.service.ProductService;


@Slf4j
@Route(value = "assad", layout = MainLayout.class)
public class Home extends VerticalLayout {

    OrderedList productList = new OrderedList();

    Button button = new Button();

    @PostConstruct
    public void postConstruct() {

        button.addClickListener(e -> getProductList());
        add(new H1("Hello"), productList,button);
    }

    ProductService productService;

    @Autowired
    public Home(ProductService productService) {
        this.productService = productService;

    }


    public void getProductList() {
        productList.removeAll();

        for (Product product : productService.listOfProductByCategory()) {
            Div div = new Div();
            div.add(product.getName());

            log.info("product Name is {}",product.getName());
            productList.add(div);

        }
    }


}
