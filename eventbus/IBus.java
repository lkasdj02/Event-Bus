package eventbus;

public interface IBus {
    public void addSubscriber(ISubscriber subscriber, EventType eventType);
    public void removeSubscriber(ISubscriber subscriber, EventType eventType);
    public void publish(EventType eventType, String payload);
}