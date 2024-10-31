package com.example.event_log.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class CommonChannelsConfig {
    @Bean
    public MessageChannel pubsubInputChannel1() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel pubsubInputChannel2() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel pubsubInputChannel3() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel pubsubInputChannel4() {
        return new DirectChannel();
    }
}
