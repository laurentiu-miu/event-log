package com.example.event_log.event;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
@ConditionalOnProperty(name = "use.emulator", havingValue = "false", matchIfMissing = true)
public class ProductionPubSubConfig {
    @Bean
    public PubSubInboundChannelAdapter firstAdapter(@Qualifier("pubsubInputChannel1") MessageChannel inputChannel,
                                                    @Value("${event.provider.google.subscriptionInFirst}") String subscription,
                                                    PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscription);
        adapter.setOutputChannel(inputChannel);
        return adapter;
    }
    @Bean
    public PubSubInboundChannelAdapter secondAdapter(@Qualifier("pubsubInputChannel1") MessageChannel inputChannel,
                                                    @Value("${event.provider.google.subscriptionInFirst}") String subscription,
                                                    PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscription);
        adapter.setOutputChannel(inputChannel);
        return adapter;
    }
    @Bean
    public PubSubInboundChannelAdapter thirdAdapter(@Qualifier("pubsubInputChannel1") MessageChannel inputChannel,
                                                    @Value("${event.provider.google.subscriptionInThird}") String subscription,
                                                    PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscription);
        adapter.setOutputChannel(inputChannel);
        return adapter;
    }

}
