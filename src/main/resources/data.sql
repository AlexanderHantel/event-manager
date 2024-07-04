INSERT INTO hall (name) VALUES ('Main Hall');

INSERT INTO line (ordinal_number, seats_per_line, hall_id) VALUES
                                               (1, 10, 1),
                                               (2, 10, 1),
                                               (3, 12, 1),
                                               (4, 12, 1),
                                               (5, 14, 1);

INSERT INTO musical (name, price) VALUES ('Hairspray', 25.5);

INSERT INTO concert (start_date_time, musical_id, hall_id) VALUES
                                            (DATEADD('SECOND', 16 * 3600, DATEADD('DAY', 2, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 18 * 3600, DATEADD('DAY', 4, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 20 * 3600, DATEADD('DAY', 6, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 19 * 3600, DATEADD('DAY', 8, CURRENT_DATE)), 1, 1),
                                            (DATEADD('SECOND', 21 * 3600, DATEADD('DAY', 10, CURRENT_DATE)), 1, 1);

INSERT INTO booked_seat (hall_id, concert_id, line_id, seat_ordinal_number) VALUES
                                                                        (1, 1, 2, 5),
                                                                        (1, 1, 2, 6),
                                                                        (1, 1, 4, 2),
                                                                        (1, 1, 1, 2);

