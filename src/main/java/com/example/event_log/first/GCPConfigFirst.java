package com.example.event_log.first;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class GCPConfigFirst {
    @Value(value = "${event.provider.google.subscriptionInFirst}")
    public String subscriptionIn1;

    //@Bean
    public PubSubInboundChannelAdapter messageChannelAdapter1(@Qualifier("pubsubInputChannel1") MessageChannel inputChannel,
                                                              PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionIn1);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }

    /*@Bean
    public MessageChannel pubsubInputChannel1() {
        return new DirectChannel();
    }*/
}
