package eventbus;

public interface ISubscriber {
    public void handleEvenet(EventType eventType, String payload);
}