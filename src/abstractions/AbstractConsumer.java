package abstractions;

import broker.Broker;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractConsumer {
    private final String topic;
    private final Broker broker;
    private final Long pollRate;
    private final ScheduledExecutorService scheduler;
    protected AtomicInteger offset = new AtomicInteger(0);


    public AbstractConsumer(String topic, Broker broker, Long pollRate, ScheduledExecutorService scheduler) {
        this.topic = topic;
        this.broker = broker;
        this.pollRate = pollRate;
        this.scheduler = scheduler;

    }

    private void startPolling() {
        this.pollAndDoSmth();
        scheduler.scheduleAtFixedRate(this::startPolling, 0, pollRate, TimeUnit.MILLISECONDS);

    }

    protected List<String> pollEvents() {
        return broker.getEvents(this.topic, this.offset.get());
    }

    public abstract void pollAndDoSmth();


}
