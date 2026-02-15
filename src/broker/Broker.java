package broker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Broker {

    private final Integer maxTopicSize;
    private final Map<String, Queue<String>> map = new ConcurrentHashMap<>();

    public Broker(Integer maxSize) {
        this.maxTopicSize = maxSize;
    }

    public void publish(String topic, String message) {
        Queue<String> topicMessages = this.map.computeIfAbsent(topic, t -> new ConcurrentLinkedQueue<>());
        topicMessages.add(message);


    }

    public List<String> getEvents(String topic, Integer offset) {
        Queue<String> messages = map.get(topic);

        if (messages == null) {
            System.out.println("messages is null");
            return Collections.emptyList();
        }
        System.out.println(messages);
        System.out.println("offset " +offset );
        return messages.stream()
                .toList()
                .subList(offset, messages.size());
    }
}
