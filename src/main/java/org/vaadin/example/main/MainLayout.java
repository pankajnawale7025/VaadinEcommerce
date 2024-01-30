package org.vaadin.example.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        // createDrawer();
    }

    private void createHeader() {
        H1 title = new H1("Cloudmart");
        title.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.Margin.MEDIUM);


        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(new Icon("lumo", "search"));
        textField.setTooltipText("Wrap in “quotes” for exact phrase");
        textField.addClassName("search-text-field");
        Button searchButton = new Button("Search");
        searchButton.getStyle().set("color", "#fff").set("background-color", "#11142a");
        Button logInButton = new Button("Log In");
        ConfirmDialog ConfirmDialog;
        ConfirmDialog confirmDialog;
        logInButton.addClickListener(e -> navigateToLoginPage());


        logInButton.getStyle().set("margin-left", "300px");
        Button signUpButton = new Button("Sign up");
        signUpButton.addClickListener(e -> navigateToregistrationPage());

        var header = new HorizontalLayout(title, textField, searchButton, logInButton, signUpButton);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);
        addToNavbar(header);


    }


    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                //   new RouterLink("List", ListView.class)
        ));
    }

    private void navigateToLoginPage() {
        UI.getCurrent().navigate("loginpage");
    }

    private void navigateToregistrationPage() {
        UI.getCurrent().navigate("registration-page");
    }

    private void navigateToHomePage() {
        UI.getCurrent().navigate("homepage");
    }
}


