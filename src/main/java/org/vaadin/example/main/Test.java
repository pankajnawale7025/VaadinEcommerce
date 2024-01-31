package org.vaadin.example.main;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "abcd")
public class Test extends VerticalLayout {

    public Test() {
        add("hello");
    }

}
