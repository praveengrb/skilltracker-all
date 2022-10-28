package com.hack.fse.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class ActiveMqJmsConfiguration {
    @Value("${spring.activemq.url}")
    String brokerUrl;
    @Value("${spring.activemq.user.name}")
    String user;
    @Value("${spring.activemq.user.password}")
    String password;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
       ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, brokerUrl);
        factory.setTrustAllPackages(true);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setConcurrency("50");
        return factory;
    }
}