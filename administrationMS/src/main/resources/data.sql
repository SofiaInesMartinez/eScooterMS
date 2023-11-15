
-- Drop tables if they exist
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS role;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL PRIMARY KEY,
  phone int(9) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  status int(9) NOT NULL
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account`
--

CREATE TABLE IF NOT EXISTS account (
  id bigint(20) NOT NULL PRIMARY KEY,
  money_balance int(20) NOT NULL,
  created_at date NOT NULL
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE IF NOT EXISTS role (
  id bigint(20) NOT NULL PRIMARY KEY,
  name varchar(20) NOT NULL
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_account`
--

CREATE TABLE IF NOT EXISTS user_account (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id bigint(20) NOT NULL,
  account_id bigint(20) NOT NULL

);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE IF NOT EXISTS user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL
);


----------
-- Poblate table `user`
--

-- insert into user (id, phone, email, name, surname, username, role, status) values (1, 3602936, 'cbartunek0@columbia.edu', 'Cam', 'Bartunek', 'cbartunek0', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (2, 494787, 'odevoy1@tripod.com', 'Olga', 'Devoy', 'odevoy1', 2, 0);
-- insert into user (id, phone, email, name, surname, username, role, status) values (3, 2439860, 'smacronald2@dmoz.org', 'Scotty', 'MacRonald', 'smacronald2', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (4, 3197148, 'kgypps3@oakley.com', 'Kipp', 'Gypps', 'kgypps3', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (5, 557246, 'scrohan4@webmd.com', 'Sherm', 'Crohan', 'scrohan4', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (6, 8623634, 'phailes5@patch.com', 'Parnell', 'Hailes', 'phailes5', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (7, 9549666, 'gtellwright6@paginegialle.it', 'Garrot', 'Tellwright', 'gtellwright6', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (8, 27156, 'gzanardii7@chronoengine.com', 'Gill', 'Zanardii', 'gzanardii7', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (9, 469380, 'lmclugish8@disqus.com', 'Leigh', 'McLugish', 'lmclugish8', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (10, 5014119, 'kstead9@miibeian.gov.cn', 'Katrina', 'Stead', 'kstead9', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (11, 5526895, 'msurteesa@jugem.jp', 'Madelle', 'Surtees', 'msurteesa', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (12, 4721535, 'bpatriab@livejournal.com', 'Buiron', 'Patria', 'bpatriab', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (13, 9636242, 'sgawthorpec@nytimes.com', 'Sisely', 'Gawthorpe', 'sgawthorpec', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (14, 5814978, 'ycritchardd@vkontakte.ru', 'Yule', 'Critchard', 'ycritchardd', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (15, 6524937, 'gbosticke@ow.ly', 'Gussie', 'Bostick', 'gbosticke', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (16, 227728, 'bmorradf@sourceforge.net', 'Brigg', 'Morrad', 'bmorradf', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (17, 2051725, 'gfolinig@biglobe.ne.jp', 'Gerick', 'Folini', 'gfolinig', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (18, 5835023, 'atynewellh@dedecms.com', 'Anders', 'Tynewell', 'atynewellh', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (19, 8521694, 'bbrickhilli@vistaprint.com', 'Base', 'Brickhill', 'bbrickhilli', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (20, 7445945, 'upetkovicj@usa.gov', 'Upton', 'Petkovic', 'upetkovicj', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (21, 783425, 'jmunnisk@hp.com', 'Jessamyn', 'Munnis', 'jmunnisk', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (22, 9525490, 'idonahuel@tinypic.com', 'Ivette', 'Donahue', 'idonahuel', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (23, 4153929, 'fdidsburym@netscape.com', 'Ferne', 'Didsbury', 'fdidsburym', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (24, 8767781, 'gpatesn@army.mil', 'Guinevere', 'Pates', 'gpatesn', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (25, 2317286, 'dremnanto@paginegialle.it', 'Delphine', 'Remnant', 'dremnanto', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (26, 8568779, 'lprantonip@senate.gov', 'Leontine', 'Prantoni', 'lprantonip', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (27, 9875063, 'ctrappq@nifty.com', 'Cecilla', 'Trapp', 'ctrappq', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (28, 5835616, 'mplumerr@ask.com', 'Mathias', 'Plumer', 'mplumerr', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (29, 5488710, 'jjovics@youtube.com', 'Jeniffer', 'Jovic', 'jjovics', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (30, 5857666, 'nrubyt@army.mil', 'Nanny', 'Ruby', 'nrubyt', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (31, 6053702, 'aarmisteadu@ovh.net', 'Archibaldo', 'Armistead', 'aarmisteadu', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (32, 2209163, 'ncollinv@amazonaws.com', 'Nalani', 'Collin', 'ncollinv', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (33, 8719959, 'cfarraw@cbslocal.com', 'Craggie', 'Farra', 'cfarraw', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (34, 2802894, 'steissierx@typepad.com', 'Scottie', 'Teissier', 'steissierx', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (35, 869904, 'fmacgrayy@phpbb.com', 'Fonsie', 'MacGray', 'fmacgrayy', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (36, 887605, 'oprenz@google.com.au', 'Olav', 'Pren', 'oprenz', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (37, 7308209, 'rmaxfield10@blogtalkradio.com', 'Richard', 'Maxfield', 'rmaxfield10', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (38, 4896539, 'cbrahm11@dailymail.co.uk', 'Claybourne', 'Brahm', 'cbrahm11', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (39, 1753420, 'efrier12@theglobeandmail.com', 'Emmy', 'Frier', 'efrier12', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (40, 1921425, 'crollo13@virginia.edu', 'Carree', 'Rollo', 'crollo13', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (41, 5522521, 'mtimothy14@mac.com', 'Meagan', 'Timothy', 'mtimothy14', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (42, 9508376, 'tdrever15@jigsy.com', 'Talyah', 'Drever', 'tdrever15', 1, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (43, 9911365, 'gargont16@biblegateway.com', 'Goldarina', 'Argont', 'gargont16', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (44, 8846592, 'tfeldberger17@merriam-webster.com', 'Tiffani', 'Feldberger', 'tfeldberger17', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (45, 9905104, 'hdaingerfield18@woothemes.com', 'Horatio', 'Daingerfield', 'hdaingerfield18', 3, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (46, 9706460, 'yjessett19@ted.com', 'Yolanthe', 'Jessett', 'yjessett19', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (47, 6530699, 'ggilhouley1a@wordpress.org', 'Gardy', 'Gilhouley', 'ggilhouley1a', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (48, 5168077, 'dliddington1b@hao123.com', 'Dorey', 'Liddington', 'dliddington1b', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (49, 9238310, 'jpinson1c@flickr.com', 'Jo', 'Pinson', 'jpinson1c', 2, 1);
-- insert into user (id, phone, email, name, surname, username, role, status) values (50, 7009764, 'reastam1d@miitbeian.gov.cn', 'Rafaelia', 'Eastam', 'reastam1d', 3, 1);

COMMIT;


----------------
-- poblate la tabla `account`

insert into account (id, money_balance, created_at) values (1, 805103, '2023-04-12 11:32:13');
insert into account (id, money_balance, created_at) values (2, 908227, '2023-09-25 13:27:24');
insert into account (id, money_balance, created_at) values (3, 622052, '2023-07-07 04:41:35');
insert into account (id, money_balance, created_at) values (4, 841224, '2023-02-02 06:43:02');
insert into account (id, money_balance, created_at) values (5, 56160, '2022-12-21 05:29:24');
insert into account (id, money_balance, created_at) values (6, 99048, '2022-11-18 23:40:21');
insert into account (id, money_balance, created_at) values (7, 879145, '2023-02-06 02:26:07');
insert into account (id, money_balance, created_at) values (8, 822404, '2023-09-25 22:25:07');
insert into account (id, money_balance, created_at) values (9, 591887, '2023-01-02 17:42:30');
insert into account (id, money_balance, created_at) values (10, 525481, '2023-10-10 05:59:39');
insert into account (id, money_balance, created_at) values (11, 569733, '2023-03-06 09:23:59');
insert into account (id, money_balance, created_at) values (12, 919180, '2023-07-22 06:35:20');
insert into account (id, money_balance, created_at) values (13, 390167, '2023-02-09 12:30:24');
insert into account (id, money_balance, created_at) values (14, 137165, '2023-09-21 19:37:01');
insert into account (id, money_balance, created_at) values (15, 199832, '2023-08-02 18:04:44');
insert into account (id, money_balance, created_at) values (16, 481164, '2022-11-02 15:52:32');
insert into account (id, money_balance, created_at) values (17, 818513, '2023-05-05 23:47:39');
insert into account (id, money_balance, created_at) values (18, 564575, '2023-10-01 18:29:58');
insert into account (id, money_balance, created_at) values (19, 795369, '2023-05-25 16:25:39');
insert into account (id, money_balance, created_at) values (20, 750303, '2023-06-15 19:40:42');
insert into account (id, money_balance, created_at) values (21, 174243, '2023-01-07 18:30:22');
insert into account (id, money_balance, created_at) values (22, 808115, '2023-07-03 13:35:14');
insert into account (id, money_balance, created_at) values (23, 177379, '2023-03-08 20:21:09');
insert into account (id, money_balance, created_at) values (24, 761814, '2023-10-01 06:42:54');
insert into account (id, money_balance, created_at) values (25, 927862, '2023-02-12 12:53:18');
insert into account (id, money_balance, created_at) values (26, 872589, '2023-09-17 03:42:08');
insert into account (id, money_balance, created_at) values (27, 259331, '2023-01-12 09:55:03');
insert into account (id, money_balance, created_at) values (28, 395951, '2022-12-17 20:15:28');
insert into account (id, money_balance, created_at) values (29, 218531, '2023-02-02 15:44:00');
insert into account (id, money_balance, created_at) values (30, 628969, '2023-01-30 18:02:53');
COMMIT;

----------------
-- poblate la tabla `role`
insert into role (id, name) values (1, 'ADMIN');
insert into role (id, name) values (2, 'USER');
COMMIT;