package com.jk.explore.rabbitmq.producer.controller;

import com.jk.explore.rabbitmq.producer.ProducerApplication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kondurj
 */
@RestController
public class ProducerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);


    private final static String QUEUE_NAME = "hello";

    private AtomicInteger messageCounter = new AtomicInteger();

    @GetMapping(value = "/send")
    @ResponseBody
    public String sendMessage() throws IOException, TimeoutException {
        int messageCountValue = messageCounter.getAndIncrement();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        String sent = null;
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = String.valueOf(messageCountValue) + "  " +
                    ": Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
            System.out.println(" sending message with value: "+ message);
            LOGGER.info("sent message: "+message);
            sent = message;
        }
        return sent;
    }

}