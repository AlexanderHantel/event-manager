package com.hantel.event_manager;

import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.entity.hall.Line;
import com.hantel.event_manager.entity.hall.Seat;
import com.hantel.event_manager.repository.HallRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SpringBootApplication
public class EventManagerApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HallRepository hallRepository;

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

	}

	private void printHall() {
		Hall hall = hallRepository.findById(1L);
		List<Line> lines = hall.getLines();
		StringBuilder wholeHall = new StringBuilder();

		for (Line line : lines) {
			List<Seat> seats = line.getSeats();
			wholeHall.append("\n");
			for(Seat seat : seats) {
				if (seat.getIsFree()) {
					wholeHall.append(" == ");
				} else {
					wholeHall.append(" XX ");
				}
			}
		}

		logger.info("{}", wholeHall);
	}
}
