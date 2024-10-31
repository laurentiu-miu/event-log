package com.example.event_log.event.service;

import com.example.event_log.event.model.EventLog;
import com.example.event_log.event.model.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
public class StartController {
    @Autowired
    private GcpPublisher publisher;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("api/publish/{customType}")
    public String publish(@PathVariable("customType") String customType) throws JsonProcessingException {
        EventLog eventLog = EventLog.builder()
                .mesUuid(UUID.randomUUID())
                .trxUuid(UUID.randomUUID())
                .logTime(Timestamp.from(Instant.now()))
                .eventType(EventType.valueOf(customType))
                .build();
        publisher.publish(eventLog, Map.of("customType",customType));
        return objectMapper.writeValueAsString(eventLog);
    }
    @GetMapping("api/publish/bulk/{size}")
    public String publish(@PathVariable("size") Integer size) throws JsonProcessingException {
        for(int i = 0; i < size; i++) {
            EventLog eventLog = EventLog.builder()
                    .mesUuid(UUID.randomUUID())
                    .trxUuid(UUID.randomUUID())
                    .logTime(Timestamp.from(Instant.now()))
                    .eventType(EventType.START)
                    .build();
            publisher.publish(eventLog, Map.of("customType",eventLog.getEventType().toString()));
        }
        return MessageFormat.format("Published successfully {0} events!",size);
    }
}
