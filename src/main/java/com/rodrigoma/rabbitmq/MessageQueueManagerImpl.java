package com.rodrigoma.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("messageQueueManager")
public class MessageQueueManagerImpl implements MessageQueueManager {
    @Autowired
    private AmqpAdmin admin;
    @Autowired
    private AmqpTemplate template;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private SimpleMessageListenerContainer container;

    private String exchange = "directExchange";

    @Override
    public String createQueue(String queueName) {
        System.out.println("creating queue with name: " + queueName);

        //create queue
        Queue newQueue = new Queue(queueName, true, false, false);
        queueName = admin.declareQueue(newQueue);

        //create binding with exchange
        admin.declareBinding(new Binding(queueName, DestinationType.QUEUE, exchange, queueName, new HashMap<String, Object>()));

        System.out.println("queue successfully created: " + queueName);

        //add queue to listener
        container.addQueues(newQueue);

        //start listener
        container.start();
        return queueName;
    }

    @Override
    public void sendMessage(String message, String destinationQueueName) throws Exception {
        template.convertAndSend(exchange, destinationQueueName, MessageBuilder.withBody(message.getBytes()).build());
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Msg Lida: " + new String(message.getBody()));
    }
}