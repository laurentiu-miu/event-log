package com.example.event_log.event.service;

import com.example.event_log.event.model.EventLog;
import com.example.event_log.event.model.EventType;
import com.example.event_log.event.repository.EventLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class EndSubscriberService {
    private final ObjectMapper objectMapper;
    private final EventLogRepository repository;

    @ServiceActivator(inputChannel = "pubsubInputChannel4")
    public void messageReceiver1(String payload,
                                 @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message,
                                 @Header("customType") String customType){
        log.info("Process End: {}", payload);
        try {
            var currentEventLog = objectMapper.readValue(payload, EventLog.class);
            var newEventLog = EventLog.builder()
                    .mesUuid(UUID.randomUUID())
                    .trxUuid(currentEventLog.getTrxUuid())
                    .eventType(EventType.END)
                    .logTime(Timestamp.from(Instant.now()))
                    .build();
            repository.save(newEventLog);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        message.ack();
    }
}
