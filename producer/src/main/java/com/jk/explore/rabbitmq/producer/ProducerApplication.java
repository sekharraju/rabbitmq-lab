package com.jk.explore.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kondurj
 */
@SpringBootApplication
public class ProducerApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);

        LOGGER.info("Initialization is done for: "+ProducerApplication.class.getSimpleName());
    }

}
