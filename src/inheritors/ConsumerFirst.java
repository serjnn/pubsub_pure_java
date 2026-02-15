package inheritors;

import abstractions.AbstractConsumer;
import broker.Broker;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class ConsumerFirst extends AbstractConsumer {

    private final String serviceName;

    public ConsumerFirst(String topic, Broker broker, Long pollRate, String name, ScheduledExecutorService scheduler) {
        super(topic, broker, pollRate, scheduler);
        this.serviceName = name;
    }

    public void pollAndDoSmth() {
        List<String> events = pollEvents();
        System.out.println("zxcxzc");
        if (events.isEmpty()) return;
        System.out.printf("%s polling %s %n", serviceName, events);
        offset.addAndGet(events.size());
    }

}
