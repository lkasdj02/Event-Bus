package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eventbus.*;

class ConcretePublisherTest {

    private RecordingBus bus;
    private ConcretePublisher publisher;

    @BeforeEach
    void setUp() {
        bus = new RecordingBus();
        publisher = new ConcretePublisher();
    }

    @Test
    void publishThrowsWhenBusIsNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> publisher.publish(null, EventType.EVENT_TYPE_A, "hello")
        );

        assertEquals("Bus must not be null!", ex.getMessage());
        assertEquals(0, bus.publishCallCount);
    }

    @Test
    void publishThrowsWhenEventTypeIsNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> publisher.publish(bus, null, "hello")
        );

        assertEquals("EventType must not be null!", ex.getMessage());
        assertEquals(0, bus.publishCallCount);
    }

    @Test
    void publishThrowsWhenMessageIsNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> publisher.publish(bus, EventType.EVENT_TYPE_A, null)
        );

        assertEquals("Message must not be null!", ex.getMessage());
        assertEquals(0, bus.publishCallCount);
    }

    @Test
    void publishDelegatesEventTypeAndMessageToBus() {
        publisher.publish(bus, EventType.EVENT_TYPE_B, "order created");

        assertEquals(1, bus.publishCallCount);
        assertEquals(EventType.EVENT_TYPE_B, bus.lastEventType);
        assertEquals("order created", bus.lastPayload);
    }

    private static class RecordingBus implements IBus {
        private int publishCallCount = 0;
        private EventType lastEventType;
        private String lastPayload;

        @Override
        public void addSubscriber(ISubscriber subscriber, EventType eventType) {
            // Not needed for publish tests
        }

        @Override
        public void removeSubscriber(ISubscriber subscriber, EventType eventType) {
            // Not needed for publish tests 
        }

        @Override
        public void publish(EventType eventType, String payload) {
            publishCallCount++;
            this.lastEventType = eventType;
            this.lastPayload = payload;
        }
    }
}