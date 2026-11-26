package Classes;

public interface IPublisher {
    public void publish(IBus bus, EventType eventType, String payload);
}