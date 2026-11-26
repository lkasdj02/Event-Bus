package eventbus;
import java.util.*;

public class ConcretePublisher implements IPublisher {

	@Override
	public void publish(IBus bus, EventType eventType, String message) {
		Objects.requireNonNull(bus, "Bus must not be null!");
		Objects.requireNonNull(eventType, "EventType must not be null!");
		Objects.requireNonNull(message, "Message must not be null!");

	    bus.publish(eventType, message);
	}
}