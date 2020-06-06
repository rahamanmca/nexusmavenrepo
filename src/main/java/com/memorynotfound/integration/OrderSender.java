package com.memorynotfound.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static com.memorynotfound.integration.ActiveMQConfig.ORDER_TOPIC;

@Service
public class OrderSender {

    private static Logger log = LoggerFactory.getLogger(OrderSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTopic(String json) {
        log.info("sending with convertAndSend() to queue <" + json + ">");
        jmsTemplate.convertAndSend(ORDER_TOPIC, json);
    }

}
