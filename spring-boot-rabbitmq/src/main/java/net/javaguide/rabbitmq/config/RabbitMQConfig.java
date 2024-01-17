package net.javaguide.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.name}")
	private String routingKey;
	
	@Value("${rabbitmq.routingKey.json.name}")
	private String routingJsonKey;
	
	// spring bean for rabbitMQ queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	// spring bean for rabbitMQ queue (store json Messages)
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	//springbean for rabbitmq exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	
	//Binding between queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
	}

	//Binding between queue and exchange using routing key
		@Bean
		public Binding bindingJSON() {
			return BindingBuilder
					.bind(jsonQueue())
					.to(exchange())
					.with(routingJsonKey);
		}
		
		
		@Bean
		public MessageConverter converter() {
			return new Jackson2JsonMessageConverter();
		}
		
		
		//Create Rabbit template and set Json Message conveter to it and it will return rabbitTemplate
		@Bean
		public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
			RabbitTemplate  rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(converter());
			return rabbitTemplate;
		}

		// ConnectionFactory
		//RabbitTemplate
		//RabbitAdmin
}
