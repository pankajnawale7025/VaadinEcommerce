package org.vaadin.example.event;

public class MyCustomEvent {
    private String eventData;

    public MyCustomEvent(String eventData) {
        this.eventData = eventData;
    }

    public String getEventData() {
        return eventData;
    }
}
