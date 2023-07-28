-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-07-2023 a las 20:09:16
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rolpagos`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarDetalle` (IN `p_idDetalle` INT, IN `p_fkEncabezado` INT, IN `p_detFechaDetalle` DATE, IN `p_detHoras` INT, IN `p_detValor` DOUBLE, IN `p_detTipo` VARCHAR(45))   BEGIN
    UPDATE `Detalle`
    SET
        `fkEncabezado` = p_fkEncabezado,
        `detFechaDetalle` = p_detFechaDetalle,
        `detHoras` = p_detHoras,
        `detValor` = p_detValor,
        `detTipo` = p_detTipo
    WHERE
        `idDetalle` = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarEncabezado` (IN `p_idEncabezado` INT, IN `p_fkUsuario` INT, IN `p_encFechaEmision` VARCHAR(45), IN `p_encFechaInicio` DATE, IN `p_encFechaFin` DATE, IN `p_encTotalPagar` DOUBLE)   BEGIN
    UPDATE `Encabezado`
    SET
        `fkUsuario` = p_fkUsuario,
        `encFechaEmision` = p_encFechaEmision,
        `encFechaInicio` = p_encFechaInicio,
        `encFechaFin` = p_encFechaFin,
        `encTotalPagar` = p_encTotalPagar
    WHERE
        `idEncabezado` = p_idEncabezado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarUsuario` (IN `p_id` INT, IN `p_usuIntento` INT, IN `p_usuUsuario` VARCHAR(45), IN `p_usuClave` VARCHAR(45), IN `p_usuRol` VARCHAR(45), IN `p_usuNombres` VARCHAR(45), IN `p_usuApellidos` VARCHAR(45), IN `p_usuTelefono` VARCHAR(45), IN `p_usuDireccion` VARCHAR(45))   BEGIN
    UPDATE `RolPagos`.`Usuario`
    SET
        `usuIntento` = p_usuIntento,
        `usuUsuario` = p_usuUsuario,
        `usuClave` = p_usuClave,
        `usuRol` = p_usuRol,
        `usuNombres` = p_usuNombres,
        `usuApellidos` = p_usuApellidos,
        `usuTelefono` = p_usuTelefono,
        `usuDireccion` = p_usuDireccion
    WHERE
        `idUsuario` = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearDetalle` (IN `p_fkEncabezado` INT, IN `p_detFechaDetalle` DATE, IN `p_detHoras` INT, IN `p_detValor` DOUBLE, IN `p_detTipo` VARCHAR(45))   BEGIN
    INSERT INTO `Detalle` (
        `fkEncabezado`,
        `detFechaDetalle`,
        `detHoras`,
        `detValor`,
        `detTipo`
    ) VALUES (
        p_fkEncabezado,
        p_detFechaDetalle,
        p_detHoras,
        p_detValor,
        p_detTipo
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearEncabezado` (IN `p_fkUsuario` INT, IN `p_encFechaEmision` DATE, IN `p_encFechaInicio` DATE, IN `p_encFechaFin` DATE, IN `p_encTotalPagar` DOUBLE)   BEGIN
    INSERT INTO `Encabezado` (
        `fkUsuario`,
        `encFechaEmision`,
        `encFechaInicio`,
        `encFechaFin`,
        `encTotalPagar`
    ) VALUES (
        p_fkUsuario,
        p_encFechaEmision,
        p_encFechaInicio,
        p_encFechaFin,
        p_encTotalPagar
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearUsuario` (IN `p_usuIntento` INT, IN `p_usuUsuario` VARCHAR(45), IN `p_usuClave` VARCHAR(45), IN `p_usuRol` VARCHAR(45), IN `p_usuNombres` VARCHAR(45), IN `p_usuApellidos` VARCHAR(45), IN `p_usuTelefono` VARCHAR(45), IN `p_usuDireccion` VARCHAR(45))   BEGIN
    INSERT INTO `RolPagos`.`Usuario` (
        `usuIntento`,
        `usuUsuario`,
        `usuClave`,
        `usuRol`,
        `usuNombres`,
        `usuApellidos`,
        `usuTelefono`,
        `usuDireccion`
    ) VALUES (
        p_usuIntento,
        p_usuUsuario,
        p_usuClave,
        p_usuRol,
        p_usuNombres,
        p_usuApellidos,
        p_usuTelefono,
        p_usuDireccion
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarDetalle` (IN `p_idDetalle` INT)   BEGIN
    DELETE FROM `Detalle`
    WHERE
        `idDetalle` = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarEncabezado` (IN `p_idEncabezado` INT)   BEGIN
    DELETE FROM `Encabezado`
    WHERE
        `idEncabezado` = p_idEncabezado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarUsuario` (IN `p_id` INT)   BEGIN
    DELETE FROM `RolPagos`.`Usuario`
    WHERE
        `idUsuario` = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdUsuario` (IN `p_usuUsuario` VARCHAR(45))   BEGIN
    SELECT `idUsuario`
    FROM `Usuario`
    WHERE
        `usuUsuario` = p_usuUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUltimoIDEnc` ()   BEGIN
    SELECT MAX(idEncabezado) FROM encabezado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarDetallePorID` (IN `p_idDetalle` INT)   BEGIN
    SELECT *
    FROM `Detalle`
    WHERE
        `idDetalle` = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarEncabezadoPorID` (IN `p_idEncabezado` INT)   BEGIN
    SELECT *
    FROM `Encabezado`
    WHERE
        `idEncabezado` = p_idEncabezado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarEncabezadosDescripcion` ()   BEGIN
    SELECT 
    encabezado.idEncabezado, 
    encabezado.fkUsuario, 
    usuario.usuApellidos,
    usuario.usuNombres,
    encabezado.encFechaEmision,
    encabezado.encFechaInicio,
    encabezado.encFechaFin,
    encabezado.encTotalPagar
    FROM encabezado
    INNER JOIN usuario
    ON encabezado.fkUsuario = usuario.idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarTodosEncabezados` ()   BEGIN
    SELECT *
    FROM `Encabezado`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarTodosUsuarios` ()   BEGIN
    SELECT *
    FROM `Usuario`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SeleccionarUsuarioPorID` (IN `p_idUsuario` INT)   BEGIN
    SELECT *
    FROM `Usuario`
    WHERE
        `idUsuario` = p_idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spObtenerUsuarioPorID` (IN `p_usuUsuario` INT)   BEGIN
    SELECT `idUsuario`
    FROM `Usuario`
    WHERE
        `usuUsuario` = p_usuUsuario;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `idDetalle` int(11) NOT NULL,
  `fkEncabezado` int(11) NOT NULL,
  `detFechaDetalle` date DEFAULT NULL,
  `detHoras` int(11) DEFAULT NULL,
  `detValor` double DEFAULT NULL,
  `detTipo` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`idDetalle`, `fkEncabezado`, `detFechaDetalle`, `detHoras`, `detValor`, `detTipo`) VALUES
(1, 3, '2023-07-28', 3, 7, 'PAGO'),
(2, 4, '2023-07-27', 9, 10, 'PAGO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encabezado`
--

CREATE TABLE `encabezado` (
  `idEncabezado` int(11) NOT NULL,
  `fkUsuario` int(11) NOT NULL,
  `encFechaEmision` date DEFAULT NULL,
  `encFechaInicio` date DEFAULT NULL,
  `encFechaFin` date DEFAULT NULL,
  `encTotalPagar` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `encabezado`
--

INSERT INTO `encabezado` (`idEncabezado`, `fkUsuario`, `encFechaEmision`, `encFechaInicio`, `encFechaFin`, `encTotalPagar`) VALUES
(1, 1, '2023-07-27', '2023-07-28', '2023-07-28', 10),
(2, 1, '2023-07-28', '2023-07-28', '2023-07-28', 4),
(3, 1, '2023-07-28', '2023-07-28', '2023-07-28', 21),
(4, 1, '2023-07-27', '2023-07-28', '2023-07-29', 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `usuIntento` int(11) DEFAULT NULL,
  `usuUsuario` varchar(45) DEFAULT NULL,
  `usuClave` varchar(45) DEFAULT NULL,
  `usuRol` varchar(45) DEFAULT NULL,
  `usuNombres` varchar(45) DEFAULT NULL,
  `usuApellidos` varchar(45) DEFAULT NULL,
  `usuTelefono` varchar(45) DEFAULT NULL,
  `usuDireccion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `usuIntento`, `usuUsuario`, `usuClave`, `usuRol`, `usuNombres`, `usuApellidos`, `usuTelefono`, `usuDireccion`) VALUES
(1, 0, 'admin', 'LFPfLv8ji08=', 'Administrador', 'Usuario', 'Usuario', '0987654321', 'Quito, Ecuador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `fk_Detalle_Encabezado_idx` (`fkEncabezado`);

--
-- Indices de la tabla `encabezado`
--
ALTER TABLE `encabezado`
  ADD PRIMARY KEY (`idEncabezado`),
  ADD KEY `fk_Encabezado_usuario1_idx` (`fkUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `encabezado`
--
ALTER TABLE `encabezado`
  MODIFY `idEncabezado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `fk_Detalle_Encabezado` FOREIGN KEY (`fkEncabezado`) REFERENCES `encabezado` (`idEncabezado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `encabezado`
--
ALTER TABLE `encabezado`
  ADD CONSTRAINT `fk_Encabezado_usuario1` FOREIGN KEY (`fkUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
