import abstractions.AbstractConsumer;
import broker.Broker;
import inheritors.ConsumerFirst;
import inheritors.ProducerFirst;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Broker broker = new Broker(3);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        

        ProducerFirst producer = new ProducerFirst(broker, "producer-1");

        ConsumerFirst consumer1 = new ConsumerFirst("second", broker, 3000L, "consumer-1", scheduler);
        ConsumerFirst consumer2 = new ConsumerFirst("second", broker, 3000L, "consumer-2", scheduler);


        scheduler.schedule(() -> producer.postEvent("first", "event about first"), 3000, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> producer.postEvent("second", "event about second1"), 3500, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> producer.postEvent("second", "event about second2"), 3500, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> producer.postEvent("second", "event about second3"), 6002, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> producer.postEvent("second", "event about second4"), 9000, TimeUnit.MILLISECONDS);


    }
}
