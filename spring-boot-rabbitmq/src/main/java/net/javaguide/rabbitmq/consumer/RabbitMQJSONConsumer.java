package net.javaguide.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.javaguide.rabbitmq.dto.User;

@Service
public class RabbitMQJSONConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.queue.json.name}"} )
	public void consumeJsonMessage(User user) {
		
		LOGGER.info(String.format(String.format("Recived JSON message ->%s", user.toString())));
		
	}

}
