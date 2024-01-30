package org.vaadin.example.event;

import com.google.common.eventbus.EventBus;

public class EventManager {
    private static final EventBus eventBus = new EventBus();

    public static EventBus getEventBus() {
        return eventBus;
    }
}
