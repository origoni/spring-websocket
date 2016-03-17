package com.millky.websocket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NowDateSender {

	@Autowired
	private SimpMessagingTemplate messaging;

	@Scheduled(fixedRate = 10000)
	public void sendRandomNumber() {
		Greeting random = new Greeting("Date : " + new Date());
		messaging.convertAndSend("/topic/v1/greetings", random);
	}
}
