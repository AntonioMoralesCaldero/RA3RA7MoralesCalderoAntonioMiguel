-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2024 a las 12:38:18
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `segprivado`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id`, `password`, `username`) VALUES
(1, '$2a$10$dRQP..nWQ8th.29p5Jt7G.dtoBfdI5ztRMrZ0569i48B70UrbP8Fq', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `id` int(11) NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `id_medico` int(11) DEFAULT NULL,
  `id_paciente` int(11) DEFAULT NULL,
  `tratamiento` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id`, `fecha`, `observaciones`, `id_medico`, `id_paciente`, `tratamiento`) VALUES
(62, '2024-06-11 15:32:00.000000', NULL, 6, 9, NULL),
(63, '2024-06-12 15:30:00.000000', NULL, 6, 9, NULL),
(64, '2024-06-10 14:32:00.000000', 'Dolor de cabeza agudo', 6, 1, 'Ibuprofeno 2 veces al día durante 2 semanas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `id_paciente` int(11) DEFAULT NULL,
  `dispensada` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id`, `fecha`, `precio`, `id_paciente`, `dispensada`) VALUES
(1, '2024-05-26 15:45:20.000000', 14.2, 1, b'1'),
(2, '2024-05-26 15:46:33.000000', 49.6, 1, b'1'),
(3, '2024-05-26 16:11:31.000000', 49.6, 9, b'1'),
(4, '2024-05-26 16:23:00.000000', 99.2, 9, b'1'),
(5, '2024-05-26 16:24:10.000000', 35.4, 1, b'1'),
(6, '2024-05-26 16:29:43.000000', 28.4, 1, b'1'),
(7, '2024-05-26 16:30:16.000000', 14.2, 1, b'1'),
(8, '2024-05-26 16:37:28.000000', 14.2, 1, b'1'),
(9, '2024-05-26 16:40:33.000000', 14.2, 1, b'1'),
(10, '2024-05-26 17:27:02.000000', 14.2, 1, b'1'),
(11, '2024-06-10 13:16:34.000000', 94.2, 9, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compramedicamento`
--

CREATE TABLE `compramedicamento` (
  `id` int(11) NOT NULL,
  `id_compra` int(11) DEFAULT NULL,
  `id_medicamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compramedicamento`
--

INSERT INTO `compramedicamento` (`id`, `id_compra`, `id_medicamento`) VALUES
(1, 1, 4),
(2, 2, 4),
(3, 2, 4),
(4, 2, 6),
(5, 3, 4),
(6, 3, 4),
(7, 3, 6),
(8, 4, 4),
(9, 4, 4),
(10, 4, 4),
(11, 4, 6),
(12, 4, 6),
(13, 4, 4),
(14, 5, 4),
(15, 5, 6),
(16, 6, 4),
(17, 6, 4),
(18, 7, 4),
(19, 8, 4),
(20, 9, 4),
(21, 10, 4),
(22, 11, 7),
(23, 11, 7),
(24, 11, 6),
(25, 11, 6),
(26, 11, 6),
(27, 11, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`id`, `nombre`) VALUES
(1, 'MEDICO_DE_FAMILIA'),
(2, 'DIGESTIVO'),
(3, 'NEUROLOGO'),
(4, 'DERMATOLOGO'),
(5, 'TRAUMATOLOGO'),
(8, 'PODOLOGO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--

CREATE TABLE `medicamento` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `receta` bit(1) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT INTO `medicamento` (`id`, `descripcion`, `nombre`, `precio`, `receta`, `stock`) VALUES
(4, 'Para el dolor de cabeza', 'Paracetamol', 14.2, b'1', 34),
(6, 'Para el dolor de estomago', 'Ibuprofeno', 21.2, b'0', -1),
(7, 'Para quemaduras y heridas superficiales', 'Betadine', 10.2, b'1', 76);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `fechaalta` datetime(6) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `id_especialidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`id`, `apellidos`, `edad`, `fechaalta`, `nombre`, `password`, `username`, `id_especialidad`) VALUES
(3, 'Morales Caldero', 25, '2024-05-16 22:00:00.000000', 'Antonio', '$2a$10$gRmN5nWGXsPNYub0zu0XqOaKx6/avBGZrERdQnAjc8iNty2kW4OfC', 'Medico', 4),
(5, 'García Aragón', 35, '2024-05-29 22:00:00.000000', 'Jesús', '$2a$10$RMeZz/fsJRMO6cThwY0oW.V1f18CtLL5rEvWvHMooe1ycnZ6f4FY6', 'Antonio', 1),
(6, 'González Pérez', 32, '2024-05-01 22:00:00.000000', 'Manolo', '$2a$10$/j3i7qrK0qAMPKOgyj1jSujl23ASZZaaNNzekTEL34lC/HzuTrpTm', 'Caldero', 8),
(10, 'López Serrano', 34, '2024-05-08 00:00:00.000000', 'Alejandro', '$2a$10$Y14F/fAXeFHvEuV9DFiAG.nwCd/yHW9DeqbjbW6CfNX.JvyUsq2Fy', 'Alejandro', 2),
(11, 'Gutiérrez Palacios', 56, '2024-05-10 00:00:00.000000', 'Manuel', '$2a$10$KU2SJB1BWkzYrwp4yO0c4OMdGBkGNiZ8GggtBeUZqA96JiR2G5VYm', 'Manuel', 5),
(12, 'Heredia Vela', 45, '2024-05-04 00:00:00.000000', 'Miguel', '$2a$10$wgi1GxAU/rwdDvvD1PUqvunvPz0KdQn6euiEp6JqVeXHwKHDmT1mm', 'Miguel', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id`, `apellidos`, `direccion`, `edad`, `foto`, `nombre`, `password`, `username`, `is_active`) VALUES
(1, 'Morales Caldero', 'Calle Tsunami N.6', 19, '17fa8f75-c12d-4b03-a38c-3787919e2b04_miedo.jpeg', 'Antonio', '$2a$10$dRQP..nWQ8th.29p5Jt7G.dtoBfdI5ztRMrZ0569i48B70UrbP8Fq', 'Antonio', b'1'),
(9, 'Vela Heredia', 'Calle Tsunami N.6', 34, '2ae4f9f8-d338-43c7-80f9-8d7d82a83f4d_frio.jpeg', 'Antonio', '$2a$10$gRKFSkZEDxvQ7GlAx7p31O3iU9Pv3wQNqaIn907bL/zjVY8kcHOai', 'AntonioM', b'1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3m4sa30jkr6uyy4krl6k7jir3` (`id_medico`),
  ADD KEY `FK7fljkhue1c7r80b4li70f6fh3` (`id_paciente`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc9qpir6dgwojk65ctouhc3mia` (`id_paciente`);

--
-- Indices de la tabla `compramedicamento`
--
ALTER TABLE `compramedicamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKco4udiw131y9suhk3jawav0fc` (`id_compra`),
  ADD KEY `FKlwk42fh86ut09a7jrcm546a1m` (`id_medicamento`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq27llf67jwglboxfkggn3wmhf` (`id_especialidad`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `compramedicamento`
--
ALTER TABLE `compramedicamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `FK3m4sa30jkr6uyy4krl6k7jir3` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`),
  ADD CONSTRAINT `FK7fljkhue1c7r80b4li70f6fh3` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`);

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FKc9qpir6dgwojk65ctouhc3mia` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`);

--
-- Filtros para la tabla `compramedicamento`
--
ALTER TABLE `compramedicamento`
  ADD CONSTRAINT `FKco4udiw131y9suhk3jawav0fc` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id`),
  ADD CONSTRAINT `FKlwk42fh86ut09a7jrcm546a1m` FOREIGN KEY (`id_medicamento`) REFERENCES `medicamento` (`id`);

--
-- Filtros para la tabla `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `FKq27llf67jwglboxfkggn3wmhf` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
