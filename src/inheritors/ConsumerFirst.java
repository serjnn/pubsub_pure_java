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
        List<String> events = super.pollEvents();
        if (events.isEmpty()) return;
        System.out.println(String.format("%s polling %s", serviceName, events));
        super.offset.addAndGet(events.size());
    }

}
