package broker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Broker {

    private final Integer maxTopicSize;
    private final Map<String, Queue<String>> map = new ConcurrentHashMap<>();
    private final Map<Integer, AtomicInteger> consumersOffsets = new ConcurrentHashMap<>();

    public Broker(Integer maxSize) {
        this.maxTopicSize = maxSize;
    }

    public void publish(String topic, String message) {
        Queue<String> topicMessages =
                this.map.computeIfAbsent(topic, t -> new ConcurrentLinkedQueue<>());
        topicMessages.add(message);


    }

    public List<String> getEvents(String topic, Integer consumerId) {
        Queue<String> messages = map.get(topic);

        if (messages == null) {
            System.out.println("messages is null");
            return Collections.emptyList();
        }
        AtomicInteger offset = consumersOffsets.computeIfAbsent(consumerId, t -> new AtomicInteger(0));

        List<String> res = messages.stream()
                .toList()
                .subList(offset.get(), messages.size());

        offset.addAndGet(res.size());
        return res;
    }
}
