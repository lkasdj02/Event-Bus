import eventbus.ConcreteBus;
import eventbus.ConcretePublisher;
import eventbus.ConcreteSubscriber;
import eventbus.EventType;
import eventbus.IBus;
import eventbus.IPublisher;
import eventbus.ISubscriber;

public class App {
    public static void main(String[] args) throws Exception {
        // Inizializzazione dell'event bus 
        IBus bus = new ConcreteBus();
        
        // Inizializzazione di un concrete publisher        
        IPublisher p = new ConcretePublisher();

        // Inizializzazione di un concrete subscriber 
        ISubscriber s = new ConcreteSubscriber();
        ISubscriber s1 = new ConcreteSubscriber();
        ISubscriber s2 = new ConcreteSubscriber();
        
        // Sottoscrizione di s all'event bus
        bus.addSubscriber(s, EventType.EVENT_TYPE_A);
        bus.addSubscriber(s1, EventType.EVENT_TYPE_A);
        bus.addSubscriber(s2, EventType.EVENT_TYPE_B);
        
        // pubblicazione dell'evento da parte del publisher p
        p.publish(bus, EventType.EVENT_TYPE_A, "A è avvenuto");
        p.publish(bus, EventType.EVENT_TYPE_B, "notifica mandata dal publisher");
    }
}
