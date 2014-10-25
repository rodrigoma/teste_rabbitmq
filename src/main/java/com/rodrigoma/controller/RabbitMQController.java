package com.rodrigoma.controller;

import com.rodrigoma.business.startup.ContextFactory;
import com.rodrigoma.rabbitmq.MessageQueueManager;
import com.rodrigoma.rabbitmq.MessageQueueManagerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RabbitMQController extends AbstractController {

    String queueName = "queueTest";

    int index = 0;

	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public void msgSendRabbitMQ(HttpServletRequest request, HttpServletResponse response) {

        MessageQueueManager manager = ContextFactory.getInstance().getApplicationContext().getBean(MessageQueueManagerImpl.class);
        manager.createQueue(queueName);

        try {
            String msg = "This is msg number " + index++;
            manager.sendMessage(msg, queueName);
            System.out.println("Msg Enviada: " + msg);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
}