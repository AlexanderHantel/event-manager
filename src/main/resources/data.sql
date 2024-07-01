INSERT INTO hall (id, name) VALUES (1, 'Main Hall');

INSERT INTO line (number, hall_id) VALUES
                                      (1, 1),
                                      (2, 1),
                                      (3, 1),
                                      (4, 1),
                                      (5, 1);

-- Create seats for Line 1
INSERT INTO seat (id, number, line_number, is_free) VALUES
                                                   (1, 1, 1, true),
                                                   (2, 2, 1, true),
                                                   (3, 3, 1, true),
                                                   (4, 4, 1, false),  -- booked
                                                   (5, 5, 1, true),
                                                   (6, 6, 1, true),
                                                   (7, 7, 1, false),  -- booked
                                                   (8, 8, 1, true),
                                                   (9, 9, 1, true),
                                                   (10, 10, 1, true);

-- Create seats for Line 2
INSERT INTO seat (id, number, line_number, is_free) VALUES
                                                   (11, 1, 2, true),
                                                   (12, 2, 2, true),
                                                   (13, 3, 2, false), -- booked
                                                   (14, 4, 2, true),
                                                   (15, 5, 2, true),
                                                   (16, 6, 2, true),
                                                   (17, 7, 2, true),
                                                   (18, 8, 2, false), -- booked
                                                   (19, 9, 2, true),
                                                   (20, 10, 2, true);

-- Create seats for Line 3
INSERT INTO seat (id, number, line_number, is_free) VALUES
                                                   (21, 1, 3, true),
                                                   (22, 2, 3, false), -- booked
                                                   (23, 3, 3, true),
                                                   (24, 4, 3, true),
                                                   (25, 5, 3, true),
                                                   (26, 6, 3, true),
                                                   (27, 7, 3, true),
                                                   (28, 8, 3, false), -- booked
                                                   (29, 9, 3, true),
                                                   (30, 10, 3, true),
                                                   (31, 11, 3, true),
                                                   (32, 12, 3, true);

-- Create seats for Line 4
INSERT INTO seat (id, number, line_number, is_free) VALUES
                                                   (33, 1, 4, true),
                                                   (34, 2, 4, true),
                                                   (35, 3, 4, true),
                                                   (36, 4, 4, true),
                                                   (37, 5, 4, false), -- booked
                                                   (38, 6, 4, true),
                                                   (39, 7, 4, true),
                                                   (40, 8, 4, true),
                                                   (41, 9, 4, true),
                                                   (42, 10, 4, false),-- booked
                                                   (43, 11, 4, true),
                                                   (44, 12, 4, true);

-- Create seats for Line 5
INSERT INTO seat (id, number, line_number, is_free) VALUES
                                                   (45, 1, 5, true),
                                                   (46, 2, 5, true),
                                                   (47, 3, 5, false), -- booked
                                                   (48, 4, 5, true),
                                                   (49, 5, 5, true),
                                                   (50, 6, 5, true),
                                                   (51, 7, 5, false), -- booked
                                                   (52, 8, 5, true),
                                                   (53, 9, 5, true),
                                                   (54, 10, 5, true),
                                                   (55, 11, 5, false),-- booked
                                                   (56, 12, 5, true),
                                                   (57, 13, 5, true),
                                                   (58, 14, 5, true);
