package abstractions;

import broker.Broker;

public abstract class AbstractProducer {
    private final Broker broker;

    public AbstractProducer(Broker broker) {
        this.broker = broker;
    }


    public void postEvent(String topic, String event) {
        broker.publish(topic,event);
    }
}
