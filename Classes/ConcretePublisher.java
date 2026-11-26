package Classes;

public class ConcretePublisher implements IPublisher {

	@Override
	public void publish(IBus bus, EventType eventType, String message) {
	    bus.publish(eventType, message);
	}
}