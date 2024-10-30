package com.example.event_log.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
public class PublishController {
    @Autowired
    private GcpPublisher publisher;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("api/publish/{customType}")
    public String publish(@PathVariable("customType") String customType) throws JsonProcessingException {
        EventLog eventLog = EventLog.builder()
                .mesUuid(UUID.randomUUID())
                .trxUuid(UUID.randomUUID())
                .logTime(LocalDateTime.now())
                .eventType(EventType.valueOf(customType))
                .build();
        publisher.publish(eventLog, Map.of("customType",customType));
        return objectMapper.writeValueAsString(eventLog);
    }
}
