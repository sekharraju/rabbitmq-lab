package com.jk.explore.rabbitmq.consumer.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kondurj
 */
@RestController
public class ConsumerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);


    private AtomicInteger messageCounter = new AtomicInteger();

    private final static String QUEUE_NAME = "hello";

    @PostConstruct
    public void init() throws IOException, TimeoutException {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            ((Channel) channel).queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                LOGGER.info("received message: "+message);
                messageCounter.getAndIncrement();
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    @GetMapping(value = "/count")
    @ResponseBody
    public String receivedCount() {

        return String.valueOf(messageCounter.get());
    }


}
