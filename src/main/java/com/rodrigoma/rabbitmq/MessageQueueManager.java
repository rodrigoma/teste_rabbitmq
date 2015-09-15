package com.rodrigoma.rabbitmq;

import org.springframework.amqp.core.MessageListener;

/**
 * Manager responsible for interacting with RabbitMQ. Is responsible for
 * 1. creating queues in RabbitMQ
 * 2. sending messages to queues
 * 3. acting as a listener for messages received over the queues.
 */
public interface MessageQueueManager extends MessageListener {

// teste CI

    String createQueue(String queueName);

    void sendMessage(String message, String destinationQueueName) throws Exception;
}
