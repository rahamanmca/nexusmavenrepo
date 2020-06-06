package com.memorynotfound.integration;

import static com.memorynotfound.integration.ActiveMQConfig.ORDER_TOPIC;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.memorynotfound.integration.Run.PubsubOutboundGateway;

@Component
public class OrderConsumer {

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	private static Logger log = LoggerFactory.getLogger(OrderConsumer.class);

	@JmsListener(destination = ORDER_TOPIC, containerFactory = "topicListenerFactory")
	public void receiveTopicMessage(@Payload String json, @Headers MessageHeaders headers, Message message,
			Session session) {
		log.info("received <" + json + ">");
		postOrder(json);
	}

	private void postOrder(String json) {
		log.info("pub/sub sender : " + json);
		messagingGateway.sendToPubsub(json);
	}

}
