INSERT INTO stop(id, coordinates) VALUES(1, 123), (2, 456);

INSERT INTO tariff(id_tariff, normal, extra, start_date) VALUES(1, 50, 100, "2023-10-23 10:00:00"), (2, 50, 120, "2023-10-29 10:00:00");

INSERT INTO scooter(id, last_maintenance_date, kilometers, status, total_time, time_pause) VALUES (1, null, null, "available", null, null), (2, null, null, "available", null, null);