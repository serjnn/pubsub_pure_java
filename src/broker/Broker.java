package broker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Broker {

    private final Integer maxTopicSize;
    private final Map<String, Queue<String>> messages = new ConcurrentHashMap<>();

    public Broker(Integer maxSize) {
        this.maxTopicSize = maxSize;
    }

    public void publish(String topic, String message) {
        Queue<String> topicMessages = messages.computeIfAbsent(topic, t -> new ConcurrentLinkedQueue<>());
        topicMessages.add(message);
        System.out.println(messages);
        if (topicMessages.size() >= maxTopicSize) {
            topicMessages.poll();

        }

    }

    public List<String> getEvents(String topic, Integer offset) {
        Queue<String> messages = this.messages.getOrDefault(topic, new LinkedList<>());
        if (offset >= messages.size()) {
            return Collections.emptyList();
        }
        return messages.stream()
                .toList()
                .subList(offset, messages.size());
    }
}
