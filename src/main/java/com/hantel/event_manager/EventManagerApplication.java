package com.hantel.event_manager;

import com.hantel.event_manager.service.HallService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagerApplication implements CommandLineRunner {

	public static final Logger LOGGER = LoggerFactory.getLogger(EventManagerApplication.class);
	private final HallService hallService;

	@Autowired
	public EventManagerApplication(HallService hallService) {
		this.hallService = hallService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		String hallString = hallService.createHallString();
		LOGGER.info("{}", hallString);
	}
}
