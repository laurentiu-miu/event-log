package com.example.event_log.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class GcpPublisher {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Value(value = "${event.provider.google.topic}")
    private String topic;

    public void publish(EventLog eventLog, Map<String, String> headers) {
        try {
            var eventLogString = objectMapper.writeValueAsString(eventLog);
            log.info("Publishing event log: {} headers: {}", eventLogString, headers);
            pubSubTemplate.publish(topic, eventLogString, headers);
        }catch (Exception e){
            log.error(e);
        }
    }
}
