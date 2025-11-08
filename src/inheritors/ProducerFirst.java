package inheritors;

import abstractions.AbstractProducer;
import broker.Broker;

public class ProducerFirst extends AbstractProducer {
    private final String serviceName;

    public ProducerFirst(Broker broker, String serviceName) {
        super(broker);
        this.serviceName = serviceName;
    }



}
