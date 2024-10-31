package com.example.event_log.third;

import com.example.event_log.event.model.ErrorLog;
import com.example.event_log.event.model.EventLog;
import com.example.event_log.event.model.EventType;
import com.example.event_log.event.repository.ErrorLogRepository;
import com.example.event_log.event.service.GcpPublisher;
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
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class ThirdSubscriberService {
    private final GcpPublisher publisher;
    private final ObjectMapper objectMapper;
    private final EventThirdRepository repository;
    private final ErrorLogRepository errorRepository;

    @ServiceActivator(inputChannel = "pubsubInputChannel3")
    public void messageReceiver1(String payload,
                                 @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message,
                                 @Header("customType") String customType){
        log.info("Process Third: {}", payload);
        try {
            var currentEventLog = objectMapper.readValue(payload, EventLog.class);
            var newEventLog = EventLog.builder()
                    .mesUuid(UUID.randomUUID())
                    .trxUuid(currentEventLog.getTrxUuid())
                    .eventType(EventType.THIRD)
                    .logTime(Timestamp.from(Instant.now()))
                    .build();
            repository.save(EventThird.builder()
                    .uuid(UUID.randomUUID())
                    .eventJson(objectMapper.writeValueAsString(newEventLog))
                    .mesUuid(currentEventLog.getMesUuid())
                    .build());
            publisher.publish(newEventLog, Map.of("customType", newEventLog.getEventType().name()));
        } catch (Exception e) {
            var exString = e.getMessage().length() <= 299 ? e.getMessage() : e.getMessage().substring(0, 299);
            errorRepository.save(ErrorLog.builder()
                            .uuid(UUID.randomUUID())
                            .eventJson(payload)
                            .exceptionLog(exString)
                            .logTime(Timestamp.from(Instant.now()))
                    .build());
        }
        message.ack();
    }
}
