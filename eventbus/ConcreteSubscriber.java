package eventbus;


public class ConcreteSubscriber implements ISubscriber {

    @Override 
    public void handleEvenet(EventType eventType, String payload) {
        System.out.println("----------");
        System.out.println("handling event: " + eventType.toString()); 
        System.out.println("payload: " + payload); 
        System.out.println("----------");
    }
}