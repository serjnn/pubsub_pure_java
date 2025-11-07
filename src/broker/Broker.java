package broker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {

    private final int maxTopicSize;
    private final Map<String, Queue<String>> messages = new ConcurrentHashMap<>();

    public Broker(Integer maxSize) {
        this.maxTopicSize = maxSize;
    }

    public void publish(String topic, String message) {
        Queue<String> eventQueue = messages.getOrDefault(topic, new LinkedList<>());
        synchronized (eventQueue) {
            eventQueue.add(message);
            messages.put(topic, eventQueue);
            if (eventQueue.size() > maxTopicSize) {
                eventQueue.poll();
            }
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
