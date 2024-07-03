INSERT INTO hall (name) VALUES ('Main Hall');

INSERT INTO line (ordinal_number, seats_per_line, hall_id) VALUES
                                               (1, 10, 1),
                                               (2, 10, 1),
                                               (3, 12, 1),
                                               (4, 12, 1),
                                               (5, 14, 1);

INSERT INTO musical (name, price) VALUES ('Hairspray', 25.5);

INSERT INTO concert (start_date_time, musical_id, hall_id) VALUES
                                              ('2024-07-05 10:17:14', 1, 1),
                                              ('2024-07-07 10:17:14', 1, 1),
                                              ('2024-07-09 10:17:14', 1, 1),
                                              ('2024-07-11 10:17:14', 1, 1),
                                              ('2024-07-13 10:17:14', 1, 1);

INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                        (1, 1, 2, 5),
                                                                        (1, 1, 2, 6),
                                                                        (1, 1, 4, 2),
                                                                        (1, 1, 1, 2);

