package Tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eventbus.*;

class ConcreteSubscriberTest {

    private SubscriberCaller subscriberCaller;
    private ConcreteSubscriber subscriber;

    @BeforeEach 
    void setUp() {
        subscriber = new ConcreteSubscriber();
        subscriberCaller = new SubscriberCaller(subscriber);
    }
    
    @Test
    public void handleEventThrowsWhenEventTypeIsNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> subscriberCaller.callSubscriber(null, "lala")
        );

        assertEquals("EventType must not be null!", ex.getMessage());
        assertEquals(0, subscriberCaller.succesfulReturns);
    }

    @Test
    public void handleEventThrowsWhenEventPayloadIsNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> subscriberCaller.callSubscriber(EventType.EVENT_TYPE_A, null)
        );

        assertEquals("Payload must not be null!", ex.getMessage());
        assertEquals(0, subscriberCaller.succesfulReturns);
    }

    @Test
    public void handleEventrunsSuccesfullyWhenParametersAreNotNull() {

        assertDoesNotThrow(() -> subscriberCaller.callSubscriber(EventType.EVENT_TYPE_A, "PAYLOAD"));
        assertEquals(1, subscriberCaller.succesfulReturns);
    }

    private static class SubscriberCaller {

        private int succesfulReturns;
        ISubscriber subscriberToWatch;

        public SubscriberCaller(ISubscriber _subscriberToWatch) {
            subscriberToWatch = _subscriberToWatch;
            succesfulReturns = 0;
        }

        public void callSubscriber(EventType eventType, String payload) {
            subscriberToWatch.handleEvenet(eventType, payload); 
            succesfulReturns += 1;
        }
    }
}
