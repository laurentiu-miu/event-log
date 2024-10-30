package com.example.event_log.third;

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
public class GCPConfigThird {
    @Value(value = "${event.provider.google.subscriptionInThird}")
    public String subscriptionIn3;

    //@Bean
    public PubSubInboundChannelAdapter messageChannelAdapter3(@Qualifier("pubsubInputChannel3") MessageChannel inputChannel,
                                                              PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionIn3);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }

    /*@Bean
    public MessageChannel pubsubInputChannel3() {
        return new DirectChannel();
    }*/
}
