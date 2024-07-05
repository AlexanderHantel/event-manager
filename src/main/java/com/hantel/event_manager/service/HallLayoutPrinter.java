package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.hall.BookedSeat;
import com.hantel.event_manager.entity.hall.Line;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HallLayoutPrinter {
    private static final String VACANT_SEAT_SYMBOL = "--";
    private static final String BOOKED_SEAT_SYMBOL = "XX";

    public String getHallLayoutTable(List<Line> lines, Long concertId) {
        StringJoiner hallLayoutString = new StringJoiner("\n");

        hallLayoutString.add(createHeader(lines));

        for (Line line : lines) {
            hallLayoutString.add(createRow(line, concertId));
        }

        return hallLayoutString.toString();
    }

    private String createHeader(List<Line> lines) {
        int longestLineSize = lines.stream().max(Comparator.comparing(Line::getSeatsPerLine))
                .orElseThrow(() -> new RuntimeException("Exception while search longest seat per line"))
                .getSeatsPerLine();

        StringJoiner head = new StringJoiner(" ");
        head.add("  Seat:");

        for (int i = 1; i <= longestLineSize; i++) {
            if (i < 10) {
                head.add(" " + i);
            } else {
                head.add(String.valueOf(i));
            }
        }

        return head.toString();
    }

    private String createRow(Line line, Long concertId) {
        StringJoiner row = new StringJoiner(" ");
        List<BookedSeat> bookedSeats = line.getBookedSeats().stream()
                .filter(bookedSeat -> Objects.equals(bookedSeat.getConcert().getId(), concertId))
                .toList();

        row.add(createRowHeader(line.getOrdinalNumber()));

        int lineSize = line.getSeatsPerLine();

        row.add(createSeats(lineSize, bookedSeats));

        return row.toString();
    }

    private String createRowHeader(int rowCounter) {
        StringBuilder rowHeader = new StringBuilder("Row ");

        if (rowCounter < 10) {
            rowHeader.append(" ").append(rowCounter);
        } else {
            rowHeader.append(rowCounter);
        }
        rowHeader.append(":");

        return rowHeader.toString();
    }

    private String createSeats(int lineSize, List<BookedSeat> bookedSeatsForThisRow) {
        StringJoiner rowString = new StringJoiner(" ");
        for (int j = 1; j <= lineSize; j++) {
            int finalJ = j;
            Optional<BookedSeat> bookedSeat = bookedSeatsForThisRow.stream()
                    .filter(seat -> seat.getSeatOrdinalNumber() == finalJ)
                    .findFirst();

            if (bookedSeat.isPresent()) {
                rowString.add(BOOKED_SEAT_SYMBOL);
            } else {
                rowString.add(VACANT_SEAT_SYMBOL);
            }
        }

        return rowString.toString();
    }
}
