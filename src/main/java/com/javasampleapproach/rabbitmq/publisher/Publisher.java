package com.javasampleapproach.rabbitmq.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Publisher {

	private final static String QUEUE_NAME = "queue.sample";
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.queue}")
	private String queue;
	
	public void produceMsg(String msg){
		amqpTemplate.convertAndSend(queue, "",msg);

		System.out.println("Send msg = " + msg);
	}

	public void produceMsgQueue(String msg) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.144");
		factory.setUsername("mqadmin");
		factory.setPassword("Admin123XX_");
		factory.setPort(5672);

		Connection connection = factory.newConnection();
	 	Channel channel = connection.createChannel();
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("Send msg = " + msg.getBytes());
	}
}