package eventbus;

import java.util.*;

public class ConcreteSubscriber implements ISubscriber {

    @Override 
    public void handleEvenet(EventType eventType, String payload) {
        Objects.requireNonNull(eventType, "EventType must not be null!");
        Objects.requireNonNull(payload, "Payload must not be null!");

        System.out.println("----------");
        System.out.println("handling event: " + eventType.toString()); 
        System.out.println("payload: " + payload); 
        System.out.println("----------");
    }
}