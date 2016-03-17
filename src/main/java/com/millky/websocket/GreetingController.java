package com.millky.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping("/v1")
public class GreetingController {

	private SimpMessagingTemplate messaging;

	@Autowired
	public GreetingController(SimpMessagingTemplate messaging) {
		this.messaging = messaging;
	}

	@MessageMapping("/hello")
	@SendTo("/topic/v1/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {

		for (int i = 1; i <= 10; i++) {
			Greeting random = new Greeting(i * 10 + "%");
			messaging.convertAndSend("/topic/v1/progress", random);
			Thread.sleep(100);
		}

		return new Greeting("Hello, " + message.getName() + "!");
	}

	@MessageMapping("/test")
	public Greeting test(HelloMessage message) throws Exception {
		Thread.sleep(500);
		return new Greeting("Test, " + message.getName() + "!");
	}
}
