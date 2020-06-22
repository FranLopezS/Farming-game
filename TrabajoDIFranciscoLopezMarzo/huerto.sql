-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-03-2020 a las 20:25:07
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `huerto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parcelas`
--

CREATE TABLE `parcelas` (
  `id_parcela` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `parcelas`
--

INSERT INTO `parcelas` (`id_parcela`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43),
(44),
(45),
(46),
(47),
(48),
(49),
(50),
(51),
(52),
(53),
(54),
(55),
(56),
(57),
(58),
(59),
(60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE `partidas` (
  `id_partida` int(11) NOT NULL,
  `nombre_partida` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `dia` int(2) NOT NULL,
  `mes` int(2) NOT NULL,
  `anio` int(11) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `admin` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `partidas`
--

INSERT INTO `partidas` (`id_partida`, `nombre_partida`, `password`, `dia`, `mes`, `anio`, `fecha_creacion`, `admin`) VALUES
(25, 'Admin', 'admin', 5, 2, 1, '2020-03-15', 1),
(26, 'Fran', 'fran', 5, 2, 1, '2020-03-15', 0),
(27, 'MarioHeras', 'mario', 4, 1, 1, '2020-03-16', 0),
(28, 'Alex', 'culo', 17, 1, 1, '2020-03-16', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes`
--

CREATE TABLE `personajes` (
  `id_personaje` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `energia` int(3) NOT NULL,
  `energia_max` int(3) NOT NULL,
  `agua` int(3) NOT NULL,
  `agua_max` int(3) NOT NULL,
  `dinero` int(7) NOT NULL,
  `tam_bolsillo` int(3) NOT NULL,
  `id_partida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `personajes`
--

INSERT INTO `personajes` (`id_personaje`, `nombre`, `energia`, `energia_max`, `agua`, `agua_max`, `dinero`, `tam_bolsillo`, `id_partida`) VALUES
(23, 'Admin', 100, 100, 100, 100, 560, 10, 25),
(24, 'Fran', 100, 100, 100, 100, 600, 10, 26),
(25, 'MH', 0, 100, 100, 100, 5000, 10, 27),
(26, 'Alex', 0, 100, 100, 100, 420, 10, 28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `precio` int(11) NOT NULL,
  `img` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(300) COLLATE utf8_spanish_ci NOT NULL,
  `id_tipo_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre`, `precio`, `img`, `descripcion`, `id_tipo_producto`) VALUES
(1, 'Semillas de ajo', 0, 'semillasdeajo', 'Para plantar ajo.', 1),
(2, 'Brote de arroz', 0, 'brotedearroz', 'Para plantar arroz.', 1),
(3, 'Semillas de chirivía', 0, 'semillasdechirivia', 'Para plantar chirivías.', 1),
(4, 'Semillas de col rizada', 0, 'semillasdecolrizada', 'Para plantar col rizada.', 1),
(5, 'Semillas de amapola', 0, 'semillasdeamapola', 'Para plantar amapolas.', 1),
(6, 'Semillas de arándano', 0, 'semillasdearandano', 'Para plantar arándanos.', 1),
(7, 'Semillas de girasol', 0, 'semillasdegirasol', 'Para plantar girasoles.', 1),
(8, 'Semillas de lombarda', 0, 'semillasdelombarda', 'Para plantar lombarda.', 1),
(9, 'Ajo', 100, 'ajo', 'Para hacer alioli.', 2),
(10, 'Arroz', 120, 'arroz', 'A la cubana está bueno.', 2),
(11, 'Chirivía', 100, 'chirivia', 'Asadsad', 2),
(12, 'Col rizada', 110, 'colrizada', 'Ablsadjnsdg fgfdf.', 2),
(13, 'Amapola', 135, 'amapola', 'Jljñl sdkfnsdk dfd.', 2),
(14, 'Arándano', 160, 'arandano', 'Jakn ASD DSFAJNnjsd dsksd.', 2),
(15, 'Girasol', 200, 'girasol', 'Siempre mirando hacia el sol.', 2),
(16, 'Lombarda', 170, 'lombarda', 'Muy morada siempre.', 2),
(17, 'Nada', 0, 'nada', 'No hay nada.', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_de_personajes_en_parcelas`
--

CREATE TABLE `productos_de_personajes_en_parcelas` (
  `id_personaje` int(11) NOT NULL,
  `id_parcela` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `dia` int(5) NOT NULL,
  `mes` int(5) NOT NULL,
  `anio` int(5) NOT NULL,
  `dias_sin_regar` int(5) NOT NULL,
  `regada` int(1) NOT NULL,
  `fase` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos_de_personajes_en_parcelas`
--

INSERT INTO `productos_de_personajes_en_parcelas` (`id_personaje`, `id_parcela`, `id_producto`, `fecha`, `dia`, `mes`, `anio`, `dias_sin_regar`, `regada`, `fase`) VALUES
(23, 1, 2, '2020-03-18', 1, 2, 1, 0, 0, 0),
(23, 8, 5, '2020-03-17', 13, 1, 1, 0, 0, 0),
(23, 10, 7, '2020-03-17', 13, 1, 1, 0, 0, 0),
(23, 31, 17, '2020-03-18', 4, 2, 1, 0, 0, 0),
(23, 32, 17, '2020-03-18', 1, 2, 1, 0, 0, 0),
(23, 33, 17, '2020-03-18', 19, 1, 1, 0, 0, 0),
(23, 34, 17, '2020-03-18', 19, 1, 1, 0, 0, 0),
(23, 35, 17, '2020-03-18', 19, 1, 1, 0, 0, 0),
(23, 36, 17, '2020-03-18', 1, 2, 1, 0, 0, 0),
(24, 1, 1, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 2, 3, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 3, 8, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 4, 7, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 5, 4, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 6, 6, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 7, 7, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 8, 1, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 9, 8, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 10, 2, '2020-03-17', 18, 1, 1, 0, 0, 0),
(24, 31, 17, '2020-03-16', 10, 1, 1, 0, 0, 0),
(24, 32, 17, '2020-03-16', 10, 1, 1, 0, 0, 0),
(24, 33, 17, '2020-03-16', 10, 1, 1, 0, 0, 0),
(24, 34, 17, '2020-03-16', 11, 1, 1, 0, 0, 0),
(24, 35, 17, '2020-03-16', 8, 1, 1, 0, 0, 0),
(25, 31, 14, '2020-03-16', 1, 1, 1, 1, 0, 3),
(25, 32, 15, '2020-03-16', 2, 1, 1, 1, 0, 2),
(25, 33, 17, '2020-03-16', 1, 1, 1, 0, 0, 0),
(25, 34, 17, '2020-03-16', 1, 1, 1, 0, 0, 0),
(25, 35, 17, '2020-03-18', 4, 1, 1, 0, 0, 0),
(25, 36, 17, '2020-03-18', 4, 1, 1, 0, 0, 0),
(26, 1, 2, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 2, 5, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 3, 4, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 4, 2, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 5, 5, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 6, 6, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 7, 8, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 8, 7, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 9, 1, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 10, 8, '2020-03-17', 17, 1, 1, 0, 0, 0),
(26, 31, 12, '2020-03-17', 13, 1, 1, 2, 0, 4),
(26, 32, 17, '2020-03-16', 5, 1, 1, 0, 0, 0),
(26, 33, 17, '2020-03-16', 6, 1, 1, 0, 0, 0),
(26, 34, 17, '2020-03-16', 6, 1, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_de_personajes_en_tiendas`
--

CREATE TABLE `productos_de_personajes_en_tiendas` (
  `id_personaje` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_tienda` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `dia` int(2) NOT NULL,
  `mes` int(2) NOT NULL,
  `anio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos_de_personajes_en_tiendas`
--

INSERT INTO `productos_de_personajes_en_tiendas` (`id_personaje`, `id_producto`, `id_tienda`, `precio`, `fecha`, `dia`, `mes`, `anio`) VALUES
(23, 9, 16, 100, '2020-03-16', 10, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiendas`
--

CREATE TABLE `tiendas` (
  `id_tienda` int(10) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `id_partida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tiendas`
--

INSERT INTO `tiendas` (`id_tienda`, `nombre`, `id_partida`) VALUES
(16, 'Tienda de Admin', 25),
(17, 'Tienda de Fran', 26),
(18, 'Tienda de Mario', 27),
(19, 'Tienda de Alex', 28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_productos`
--

CREATE TABLE `tipos_productos` (
  `id_tipo_producto` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipos_productos`
--

INSERT INTO `tipos_productos` (`id_tipo_producto`, `nombre`) VALUES
(1, 'Semilla'),
(2, 'Hortaliza'),
(3, 'Coleccionable'),
(4, 'Nada');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  ADD PRIMARY KEY (`id_parcela`);

--
-- Indices de la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`id_partida`);

--
-- Indices de la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD PRIMARY KEY (`id_personaje`),
  ADD KEY `id_partida` (`id_partida`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_tipo_producto` (`id_tipo_producto`);

--
-- Indices de la tabla `productos_de_personajes_en_parcelas`
--
ALTER TABLE `productos_de_personajes_en_parcelas`
  ADD PRIMARY KEY (`id_personaje`,`id_parcela`,`id_producto`,`fecha`),
  ADD KEY `id_parcela` (`id_parcela`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos_de_personajes_en_tiendas`
--
ALTER TABLE `productos_de_personajes_en_tiendas`
  ADD PRIMARY KEY (`id_personaje`,`id_producto`,`id_tienda`,`fecha`),
  ADD KEY `id_personaje` (`id_personaje`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_tienda` (`id_tienda`);

--
-- Indices de la tabla `tiendas`
--
ALTER TABLE `tiendas`
  ADD PRIMARY KEY (`id_tienda`),
  ADD KEY `id_partida` (`id_partida`);

--
-- Indices de la tabla `tipos_productos`
--
ALTER TABLE `tipos_productos`
  ADD PRIMARY KEY (`id_tipo_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  MODIFY `id_parcela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `id_partida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `personajes`
--
ALTER TABLE `personajes`
  MODIFY `id_personaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `tiendas`
--
ALTER TABLE `tiendas`
  MODIFY `id_tienda` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD CONSTRAINT `personajes_ibfk_1` FOREIGN KEY (`id_partida`) REFERENCES `partidas` (`id_partida`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipos_productos` (`id_tipo_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos_de_personajes_en_parcelas`
--
ALTER TABLE `productos_de_personajes_en_parcelas`
  ADD CONSTRAINT `productos_de_personajes_en_parcelas_ibfk_1` FOREIGN KEY (`id_parcela`) REFERENCES `parcelas` (`id_parcela`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_de_personajes_en_parcelas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_de_personajes_en_parcelas_ibfk_3` FOREIGN KEY (`id_personaje`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos_de_personajes_en_tiendas`
--
ALTER TABLE `productos_de_personajes_en_tiendas`
  ADD CONSTRAINT `productos_de_personajes_en_tiendas_ibfk_1` FOREIGN KEY (`id_personaje`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_de_personajes_en_tiendas_ibfk_2` FOREIGN KEY (`id_tienda`) REFERENCES `tiendas` (`id_tienda`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_de_personajes_en_tiendas_ibfk_3` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tiendas`
--
ALTER TABLE `tiendas`
  ADD CONSTRAINT `tiendas_ibfk_1` FOREIGN KEY (`id_partida`) REFERENCES `partidas` (`id_partida`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
