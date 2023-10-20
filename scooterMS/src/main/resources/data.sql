
-- Drop tables if they exist
DROP TABLE IF EXISTS maintenance;
DROP TABLE IF EXISTS scooter;
DROP TABLE IF EXISTS stop;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `scooter`
--

CREATE TABLE IF NOT EXISTS scooter (
  id bigint(20) NOT NULL PRIMARY KEY,
  last_maintenance_date DATE
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

--
-- Estructura de tabla para la tabla `maintenance`
--

CREATE TABLE IF NOT EXISTS maintenance (
  id bigint(20) NOT NULL PRIMARY KEY,
  description varchar(50) NOT NULL,
  start_date DATE NOT NULL,
  finish_date DATE,
  id_scooter bigint(20) NOT NULL,
  FOREIGN KEY (id_scooter) REFERENCES scooter(id)
);

