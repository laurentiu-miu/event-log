package com.example.event_log.event.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@ConditionalOnProperty(name = "use.emulator", havingValue = "true")
public class EmulatorPubSubConfig {
    @Value(value = "${event.provider.google.subscriptionInAll}")
    public String subscriptionInAll;

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Bean
    public MessageChannel emulatorInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter emulatorAdapter() {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionInAll);
        adapter.setOutputChannel(emulatorInputChannel());
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }

    @Bean
    public IntegrationFlow emulatorIntegrationFlow() {
        return IntegrationFlow.from(emulatorInputChannel())
                .route(router())
                .get();

    }

    public HeaderValueRouter router() {
        HeaderValueRouter router = new HeaderValueRouter("customType");
        router.setChannelMapping("START", "pubsubInputChannel1");
        router.setChannelMapping("FIRST", "pubsubInputChannel2");
        router.setChannelMapping("SECOND", "pubsubInputChannel3");
        router.setChannelMapping("THIRD", "pubsubInputChannel4");
        return router;
    }
}
