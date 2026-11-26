package eventbus;

import java.util.*;

public class ConcreteBus implements IBus {
    private Map<EventType, List<ISubscriber>> subscribers;

    public ConcreteBus() {
        subscribers = new HashMap<EventType, List<ISubscriber>>();
    }

	@Override
    public void addSubscriber(ISubscriber subscriber, EventType eventType) {
        Objects.requireNonNull(subscriber, "subscriber must not be null");
        Objects.requireNonNull(eventType, "eventType must not be null");

        if (!subscribers.containsKey(eventType)) {
            subscribers.put(eventType, new ArrayList<ISubscriber>());
        }
        subscribers.get(eventType).add(subscriber);
    }

	@Override
	public void removeSubscriber(ISubscriber subscriber, EventType eventType) {
        List<ISubscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            eventSubscribers.remove(subscriber);
            if (eventSubscribers.isEmpty()) {
                subscribers.remove(eventType);
            }
        }
    }

	@Override
    public void publish(EventType eventType, String payload) {
        Objects.requireNonNull(eventType, "eventType must not be null");

        List<ISubscriber> snapshot;
        synchronized (subscribers) {
            List<ISubscriber> current = subscribers.get(eventType);
            if (current == null || current.isEmpty()) {
                return;
            }
            snapshot = new ArrayList<>(current);
        }

        for (ISubscriber subscriber : snapshot) {
            subscriber.handleEvenet(eventType, payload);
        }
    }
}