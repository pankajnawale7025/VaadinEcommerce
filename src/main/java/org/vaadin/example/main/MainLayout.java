package org.vaadin.example.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class MainLayout extends AppLayout {
    public MainLayout() {



        createHeader();
        // createDrawer();
    }

    private void createHeader() {
        H1 title = new H1("Cloudmart");
        title.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.Margin.MEDIUM);
        title.addClickListener(e -> navigateToHomepage());

        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(new Icon("lumo", "search"));
        textField.setTooltipText("Wrap in “quotes” for exact phrase");
        textField.addClassName("search-text-field");
        Button searchButton = new Button("Search");
        searchButton.getStyle().set("color", "#fff").set("background-color", "#11142a");

        Button logInButton = new Button("Log In");
        Button signUpButton = new Button("Sign up");
        Button profileButton = new Button("Profile");
        Button CartButton = new Button("Cart");
        Button logoutButton = new Button("Logout");
        logoutButton.setVisible(false);
        CartButton.setVisible(false);
        profileButton.setVisible(false);


        logoutButton.addClickListener(e -> {
            VaadinSession.getCurrent().getSession().invalidate();


        });


        Object namevalue = VaadinSession.getCurrent().getAttribute("name");
        String name = (namevalue != null) ? namevalue.toString() : null;

        if (name != null) {
            logInButton.setVisible(false);
            signUpButton.setVisible(false);


            logoutButton.setVisible(true);
            CartButton.setVisible(true);
            profileButton.setVisible(true);


        }


        logInButton.addClickListener(e -> navigateToLoginPage());


        logInButton.getStyle().set("margin-left", "300px");
        signUpButton.addClickListener(e -> navigateToregistrationPage());

        var header = new HorizontalLayout(title, textField, searchButton, logInButton, signUpButton, CartButton, profileButton, logoutButton);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);
        addToNavbar(header);


    }


    private void navigateToLoginPage() {
        UI.getCurrent().navigate("");
    }


    private void navigateToHomepage() {
        UI.getCurrent().navigate("home");
    }

    private void navigateToregistrationPage() {
        UI.getCurrent().navigate("registration-page");
    }

}


