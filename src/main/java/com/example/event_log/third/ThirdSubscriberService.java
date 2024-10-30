package com.example.event_log.third;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ThirdSubscriberService {
    @ServiceActivator(inputChannel = "pubsubInputChannel3")
    public void messageReceiver1(String payload,
                                 @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message,
                                 @Header("customType") String customType){
        log.info("Subscription 3 Message arrived! Payload: {}", payload);
        log.info("Subscription 3 Header customType: {}", customType);
        message.ack();
    }
}
