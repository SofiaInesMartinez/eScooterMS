INSERT INTO stop(id, latitude, longitude) VALUES(1, 70.525, 86.525), (2, 71.878, 87.949);

INSERT INTO tariff(id, normal, extra, start_date) VALUES(1, 50, 100, "2023-10-23 10:00:00"), (2, 50, 120, "2023-10-29 10:00:00");

INSERT INTO scooter(id, last_maintenance_date, kilometers, status, total_time, time_pause, latitude, longitude) VALUES (1, null, 0, "available", 0, 0, 70.525, 86.525), (2, null, 0, "available", 0, 0, 71.878, 87.949);

INSERT INTO trip(start_date, end_date, kms, trip_amount, pause_time, initial_pause_time, is_paused, id_user, id_account, scooter_id, origin_stop_id, destination_stop_id) VALUES("2023-1-23 10:00:00", "2023-1-23 11:00:00", 10.2, 200.0, 15, 15, false, 1, 1, 1 ,1, 2), ("2023-3-23 12:00:00", "2023-3-23 13:00:00", 10.2, 40.0, 15, 15, false, 2, 2, 2, 2, 1);