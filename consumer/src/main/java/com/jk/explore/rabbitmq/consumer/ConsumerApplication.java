package com.jk.explore.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kondurj
 */
@SpringBootApplication
public class ConsumerApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class, args);

        LOGGER.info("Initialization is done for: " + ConsumerApplication.class.getSimpleName());
    }

}
