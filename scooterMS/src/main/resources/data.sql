
-- Drop tables if they exist
DROP TABLE IF EXISTS scooter;
DROP TABLE IF EXISTS stop;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `scooter`
--

CREATE TABLE IF NOT EXISTS scooter (
  id bigint(20) NOT NULL PRIMARY KEY,
  last_maintenance_date DATE,
  kilometers double(10,2),
  status varchar(25),
  total_time bigint(20),
  time_pause bigint(20)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stop`
--

CREATE TABLE IF NOT EXISTS stop (
  id bigint(20) NOT NULL PRIMARY KEY,
  coordinates bigint(20) NOT NULL
);

-- --------------------------------------------------------



