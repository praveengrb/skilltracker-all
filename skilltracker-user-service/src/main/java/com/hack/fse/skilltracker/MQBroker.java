package com.hack.fse.skilltracker;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.jms.*;

@Configuration
public class MQBroker {
    Session producerSession = null;
    MessageProducer producer = null;
    Connection producerConnection=null;
    @Value("${spring.activemq.url}")
    String mqUrl;
    @Value("${spring.activemq.user.name}")
    String userName;
    @Value("${spring.activemq.user.password}")
    String passWord;
    @Value("${spring.activemq.queue.name}")
    String queueName;

    @Bean
    public Session  producerSession() throws JMSException {
        // Create a connection factory.
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqUrl);

// Pass the username and password.
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(passWord);

// Create a pooled connection factory.
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        pooledConnectionFactory.setMaxConnections(10);

// Establish a connection for the producer.
        producerConnection = pooledConnectionFactory.createConnection();
        producerConnection.start();
        // Create a session.
        producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return producerSession;
    }

    @Bean
    public MessageProducer  producer() throws JMSException {
        // Create a producer from the session to the queue.
        Destination producerDestination = producerSession.createQueue(queueName);
        producer = producerSession.createProducer(producerDestination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return producer;
    }

    @PreDestroy
    // Method
    public void destroy() throws JMSException {
        producer.close();
        producerSession.close();
        producerConnection.close();
    }
}
