INSERT INTO hall (name) VALUES ('Hall A');
INSERT INTO hall (name) VALUES ('Hall B');

INSERT INTO line (ordinal_number, seats_per_line, hall_id) VALUES
                                               (1, 10, 1),
                                               (2, 10, 1),
                                               (3, 12, 1),
                                               (4, 12, 1),
                                               (5, 14, 1);

INSERT INTO line (ordinal_number, seats_per_line, hall_id) VALUES
                                                              (1, 10, 2),
                                                              (2, 10, 2),
                                                              (3, 12, 2),
                                                              (4, 12, 2),
                                                              (5, 14, 2);

INSERT INTO musical (name, price) VALUES ('Hairspray', 25.5);

INSERT INTO concert (start_date_time, musical_id, hall_id) VALUES
                                            (DATEADD('SECOND', 16 * 3600, DATEADD('DAY', 2, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 18 * 3600, DATEADD('DAY', 4, CURRENT_DATE)), 1, 2),
                                            (DATEADD('SECOND', 20 * 3600, DATEADD('DAY', 6, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 19 * 3600, DATEADD('DAY', 8, CURRENT_DATE)), 1, 1);

-- Hall 1 Fully booked row 3
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 1, 3, 1),
                                                                                (1, 1, 3, 2),
                                                                                (1, 1, 3, 3),
                                                                                (1, 1, 3, 4),
                                                                                (1, 1, 3, 5),
                                                                                (1, 1, 3, 6),
                                                                                (1, 1, 3, 7),
                                                                                (1, 1, 3, 8),
                                                                                (1, 1, 3, 9),
                                                                                (1, 1, 3, 10),
                                                                                (1, 1, 3, 11),
                                                                                (1, 1, 3, 12);

-- Hall 1 Row 4 with one free seat
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 1, 4, 2),
                                                                                (1, 1, 4, 3),
                                                                                (1, 1, 4, 4),
                                                                                (1, 1, 4, 5),
                                                                                (1, 1, 4, 6),
                                                                                (1, 1, 4, 7),
                                                                                (1, 1, 4, 8),
                                                                                (1, 1, 4, 9),
                                                                                (1, 1, 4, 10),
                                                                                (1, 1, 4, 11),
                                                                                (1, 1, 4, 12);

-- Hall 1 Randomly booked 10 seats
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 1, 1, 3),
                                                                                (1, 1, 1, 7),
                                                                                (1, 1, 2, 4),
                                                                                (1, 1, 2, 8),
                                                                                (1, 1, 5, 1),
                                                                                (1, 1, 5, 5),
                                                                                (1, 1, 5, 6),
                                                                                (1, 1, 5, 8),
                                                                                (1, 1, 1, 9),
                                                                                (1, 1, 2, 10);

-- Hall 2 Fully booked row 2
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (2, 2, 7, 1),
                                                                                (2, 2, 7, 2),
                                                                                (2, 2, 7, 3),
                                                                                (2, 2, 7, 4),
                                                                                (2, 2, 7, 5),
                                                                                (2, 2, 7, 6),
                                                                                (2, 2, 7, 7),
                                                                                (2, 2, 7, 8),
                                                                                (2, 2, 7, 9),
                                                                                (2, 2, 7, 10);

-- Hall 2 row 5 with one free seat
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (2, 2, 10, 1),
                                                                                (2, 2, 10, 2),
                                                                                (2, 2, 10, 3),
                                                                                (2, 2, 10, 4),
                                                                                (2, 2, 10, 5),
                                                                                (2, 2, 10, 6),
                                                                                (2, 2, 10, 7),
                                                                                (2, 2, 10, 8),
                                                                                (2, 2, 10, 9),
                                                                                (2, 2, 10, 10),
                                                                                (2, 2, 10, 11),
                                                                                (2, 2, 10, 12),
                                                                                (2, 2, 10, 13);

-- Hall 2 Randomly distributed 10 seats
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (2, 2, 6, 1),
                                                                                (2, 2, 6, 3),
                                                                                (2, 2, 6, 5),
                                                                                (2, 2, 8, 4),
                                                                                (2, 2, 8, 6),
                                                                                (2, 2, 8, 8),
                                                                                (2, 2, 9, 1),
                                                                                (2, 2, 9, 7),
                                                                                (2, 2, 9, 11),
                                                                                (2, 2, 9, 12);

-- Concert 3 Fully booked row 1
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 3, 1, 1),
                                                                                (1, 3, 1, 2),
                                                                                (1, 3, 1, 3),
                                                                                (1, 3, 1, 4),
                                                                                (1, 3, 1, 5),
                                                                                (1, 3, 1, 6),
                                                                                (1, 3, 1, 7),
                                                                                (1, 3, 1, 8),
                                                                                (1, 3, 1, 9),
                                                                                (1, 3, 1, 10);
-- Concert 3 Fully booked row 2
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 3, 2, 1),
                                                                                (1, 3, 2, 2),
                                                                                (1, 3, 2, 3),
                                                                                (1, 3, 2, 4),
                                                                                (1, 3, 2, 5),
                                                                                (1, 3, 2, 6),
                                                                                (1, 3, 2, 7),
                                                                                (1, 3, 2, 8),
                                                                                (1, 3, 2, 9),
                                                                                (1, 3, 2, 10);
-- Concert 3 Fully booked row 3
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 3, 3, 1),
                                                                                (1, 3, 3, 2),
                                                                                (1, 3, 3, 3),
                                                                                (1, 3, 3, 4),
                                                                                (1, 3, 3, 5),
                                                                                (1, 3, 3, 6),
                                                                                (1, 3, 3, 7),
                                                                                (1, 3, 3, 8),
                                                                                (1, 3, 3, 9),
                                                                                (1, 3, 3, 10),
                                                                                (1, 3, 3, 11),
                                                                                (1, 3, 3, 12);
-- Concert 3 Fully booked row 4
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 3, 4, 1),
                                                                                (1, 3, 4, 2),
                                                                                (1, 3, 4, 3),
                                                                                (1, 3, 4, 4),
                                                                                (1, 3, 4, 5),
                                                                                (1, 3, 4, 6),
                                                                                (1, 3, 4, 7),
                                                                                (1, 3, 4, 8),
                                                                                (1, 3, 4, 9),
                                                                                (1, 3, 4, 10),
                                                                                (1, 3, 4, 11),
                                                                                (1, 3, 4, 12);
-- Concert 3 row 5 with one free seat
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 3, 5, 1),
                                                                                (1, 3, 5, 2),
                                                                                (1, 3, 5, 3),
                                                                                (1, 3, 5, 4),
                                                                                (1, 3, 5, 5),
                                                                                (1, 3, 5, 6),
                                                                                (1, 3, 5, 7),
                                                                                (1, 3, 5, 8),
                                                                                (1, 3, 5, 9),
                                                                                (1, 3, 5, 10),
                                                                                (1, 3, 5, 11),
                                                                                (1, 3, 5, 12),
                                                                                (1, 3, 5, 13);

-- Concert 4 Fully booked row 1
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 4, 1, 1),
                                                                                (1, 4, 1, 2),
                                                                                (1, 4, 1, 3),
                                                                                (1, 4, 1, 4),
                                                                                (1, 4, 1, 5),
                                                                                (1, 4, 1, 6),
                                                                                (1, 4, 1, 7),
                                                                                (1, 4, 1, 8),
                                                                                (1, 4, 1, 9),
                                                                                (1, 4, 1, 10);
-- Concert 4 Fully booked row 2
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 4, 2, 1),
                                                                                (1, 4, 2, 2),
                                                                                (1, 4, 2, 3),
                                                                                (1, 4, 2, 4),
                                                                                (1, 4, 2, 5),
                                                                                (1, 4, 2, 6),
                                                                                (1, 4, 2, 7),
                                                                                (1, 4, 2, 8),
                                                                                (1, 4, 2, 9),
                                                                                (1, 4, 2, 10);
-- Concert 4 Fully booked row 3
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 4, 3, 1),
                                                                                (1, 4, 3, 2),
                                                                                (1, 4, 3, 3),
                                                                                (1, 4, 3, 4),
                                                                                (1, 4, 3, 5),
                                                                                (1, 4, 3, 6),
                                                                                (1, 4, 3, 7),
                                                                                (1, 4, 3, 8),
                                                                                (1, 4, 3, 9),
                                                                                (1, 4, 3, 10),
                                                                                (1, 4, 3, 11),
                                                                                (1, 4, 3, 12);
-- Concert 4 Fully booked row 4
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 4, 4, 1),
                                                                                (1, 4, 4, 2),
                                                                                (1, 4, 4, 3),
                                                                                (1, 4, 4, 4),
                                                                                (1, 4, 4, 5),
                                                                                (1, 4, 4, 6),
                                                                                (1, 4, 4, 7),
                                                                                (1, 4, 4, 8),
                                                                                (1, 4, 4, 9),
                                                                                (1, 4, 4, 10),
                                                                                (1, 4, 4, 11),
                                                                                (1, 4, 4, 12);
-- Concert 4 row 5 with one free seat
INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                                (1, 4, 5, 1),
                                                                                (1, 4, 5, 2),
                                                                                (1, 4, 5, 3),
                                                                                (1, 4, 5, 4),
                                                                                (1, 4, 5, 5),
                                                                                (1, 4, 5, 6),
                                                                                (1, 4, 5, 7),
                                                                                (1, 4, 5, 8),
                                                                                (1, 4, 5, 9),
                                                                                (1, 4, 5, 10),
                                                                                (1, 4, 5, 11),
                                                                                (1, 4, 5, 12),
                                                                                (1, 4, 5, 13),
                                                                                (1, 4, 5, 14);
