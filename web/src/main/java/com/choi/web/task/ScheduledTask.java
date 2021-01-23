package com.choi.web.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	@Scheduled(cron = "0 0 * * * *")
	public void run() {
		System.out.println("ScheduledTask run!");
	}
	
}
