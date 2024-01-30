package org.vaadin.example.utility;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dialog.Dialog;

import java.util.Collection;

public class Utility {

    public Component getDilogueBox()
    {

        Dialog dialog = new Dialog();
        dialog.add((Collection<Component>) new Label("This is a timed dialog"));

        // Set the dialog's size
        dialog.setWidth("300px");
        dialog.setHeight("200px");

        // Set the dialog to close after 5 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // Close the dialog
                        dialog.close();
                    }
                },
                5000  // 5 seconds
        );
        return  dialog;
    }


}
