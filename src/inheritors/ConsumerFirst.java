package inheritors;

import abstractions.AbstractConsumer;
import broker.Broker;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsumerFirst extends AbstractConsumer {

    private final String serviceName;

    public ConsumerFirst(String topic, Broker broker, Long pollRate, String name,
                         ScheduledExecutorService scheduler) {
        super(topic, broker, pollRate, scheduler);
        this.serviceName = name;


    }

    public void pollAndDoSmth() {
        List<String> events = pollEvents();
        if (events.isEmpty()) return;
        System.out.printf("%s polling %s %n", serviceName, events);

    }

}
