package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.hall.BookedSeat;
import com.hantel.event_manager.entity.hall.Line;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Component
public class HallOccupancyPrinter {
    public static final String VACANT_SEAT_SYMBOL = "--";
    public static final String BOOKED_SEAT_SYMBOL = "XX";

    public String getHallOccupancyTable(List<Line> lines, List<BookedSeat> bookedSeats) {
        StringJoiner hallOccupancyString = new StringJoiner("\n");

        hallOccupancyString.add(createHeader(lines));

        for (int i = 1; i <= lines.size(); i++) {
            int lineIndex = i - 1;
            Line line = lines.get(lineIndex);
            hallOccupancyString.add(createRow(i, line, bookedSeats));
        }

        return hallOccupancyString.toString();
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

    private String createRow(int lineOrdinalNumber, Line line, List<BookedSeat> bookedSeats) {
        StringJoiner row = new StringJoiner(" ");
        List<BookedSeat> bookedSeatsForThisRow = bookedSeats.stream()
                .filter(bookedSeat -> bookedSeat.getLine().getOrdinalNumber() == lineOrdinalNumber)
                .toList();

        row.add(createRowHeader(lineOrdinalNumber));

        int lineSize = line.getSeatsPerLine();

        row.add(createSeats(lineSize, bookedSeatsForThisRow));

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
