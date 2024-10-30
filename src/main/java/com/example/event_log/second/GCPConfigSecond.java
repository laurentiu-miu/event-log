package com.example.event_log.second;

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
public class GCPConfigSecond {
    @Value(value = "${event.provider.google.subscriptionInSecond}")
    public String subscriptionIn2;

    //@Bean
    public PubSubInboundChannelAdapter messageChannelAdapter2(@Qualifier("pubsubInputChannel2") MessageChannel inputChannel,
                                                              PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionIn2);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }

    /*@Bean
    public MessageChannel pubsubInputChannel2() {
        return new DirectChannel();
    }*/
}
