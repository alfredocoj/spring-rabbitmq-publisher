package com.javasampleapproach.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.rabbitmq.publisher.Publisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class WebController {
	
	@Autowired
	Publisher publisher;
	
	@RequestMapping("/send")
	public String sendMsg(@RequestParam("msg")String msg){
		publisher.produceMsg(msg);
		return "Done";
	}

	@RequestMapping("/send/queue")
	public String sendMsgQueue(@RequestParam("msg")String msg) throws IOException, TimeoutException {
		publisher.produceMsgQueue(msg);
		return "Done";
	}
}
