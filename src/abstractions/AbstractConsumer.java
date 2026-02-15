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
    private final ScheduledExecutorService pollingScheduler;

    public AbstractConsumer(String topic, Broker broker, Long pollRate, ScheduledExecutorService pollingScheduler) {
        this.topic = topic;
        this.broker = broker;
        this.pollRate = pollRate;
        this.pollingScheduler = pollingScheduler;
        startPolling();
    }

    private void startPolling() {
        pollingScheduler.scheduleAtFixedRate(this::pollAndDoSmth, 0, pollRate, TimeUnit.MILLISECONDS);
    }

    protected List<String> pollEvents() {
        return broker.getEvents(this.topic, this.hashCode());
    }

    public abstract void pollAndDoSmth();


}
