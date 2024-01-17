package net.javaguide.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.javaguide.rabbitmq.dto.User;

@Service
public class RabbitMQJSONProducer {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(RabbitMQJSONProducer.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.json.name}")
	private String routingJsonKey;
	
	
	private RabbitTemplate rabbitTemplate;


	public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendJsonMessage(User user) {
		LOGGER.info(String.format("Json message sent -> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
	}

}
