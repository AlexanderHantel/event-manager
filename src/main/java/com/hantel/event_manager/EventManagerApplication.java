package com.hantel.event_manager;

import com.hantel.event_manager.service.BookingService;
import com.hantel.event_manager.service.ConcertService;
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
	private final ConcertService concertService;
	private final BookingService bookingService;

	@Autowired
	public EventManagerApplication(HallService hallService,
								   ConcertService concertService,
								   BookingService bookingService) {
		this.hallService = hallService;
		this.concertService = concertService;
		this.bookingService = bookingService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		List<String> reservedDatesAsStrings = concertService.getReservedDatesAsStrings();
//		LOGGER.info("reserved dates: {}", reservedDatesAsStrings);
//		int amount = bookingService.getVacantSeatsAmount(1L, 1L);
//		LOGGER.info("AAAAAAAAAAAAAAAAAAAAAAAA VacantSeatsAmount: {}", amount);
	}
}
