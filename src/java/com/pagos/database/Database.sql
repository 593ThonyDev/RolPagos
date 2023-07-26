-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema RolPagos
-- -----------------------------------------------------

-- Create and use the schema
CREATE SCHEMA IF NOT EXISTS `RolPagos` DEFAULT CHARACTER SET utf8;
USE `RolPagos`;

-- -----------------------------------------------------
-- Table `RolPagos`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RolPagos`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuIntento` INT NULL,
  `usuUsuario` VARCHAR(45) NULL,
  `usuClave` VARCHAR(45) NULL,
  `usuRol` VARCHAR(45) NULL,
  `usuNombres` VARCHAR(45) NULL,
  `usuApellidos` VARCHAR(45) NULL,
  `usuTelefono` VARCHAR(45) NULL,
  `usuDireccion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RolPagos`.`Encabezado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RolPagos`.`Encabezado` (
  `idEncabezado` INT NOT NULL AUTO_INCREMENT,
  `fkUsuario` INT NOT NULL,
  `encFechaEmision` VARCHAR(45) NULL,
  `encFechaInicio` DATE NULL,
  `encFechaFin` DATE NULL,
  `encTotalPagar` DOUBLE NULL,
  PRIMARY KEY (`idEncabezado`),
  INDEX `fk_Encabezado_usuario1_idx` (`fkUsuario`),
  CONSTRAINT `fk_Encabezado_usuario1`
    FOREIGN KEY (`fkUsuario`)
    REFERENCES `RolPagos`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RolPagos`.`Detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RolPagos`.`Detalle` (
  `idDetalle` INT NOT NULL AUTO_INCREMENT,
  `fkEncabezado` INT NOT NULL,
  `detFechaDetalle` DATE NULL,
  `detHoras` INT NULL,
  `detValor` DOUBLE NULL,
  `detTipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idDetalle`),
  INDEX `fk_Detalle_Encabezado_idx` (`fkEncabezado`),
  CONSTRAINT `fk_Detalle_Encabezado`
    FOREIGN KEY (`fkEncabezado`)
    REFERENCES `RolPagos`.`Encabezado` (`idEncabezado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- PROCEDIMIENTOS ALMACENADOS USUARIO
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE `CrearUsuario` (
    IN p_usuIntento INT,
    IN p_usuUsuario VARCHAR(45),
    IN p_usuClave VARCHAR(45),
    IN p_usuRol VARCHAR(45),
    IN p_usuNombres VARCHAR(45),
    IN p_usuApellidos VARCHAR(45),
    IN p_usuTelefono VARCHAR(45),
    IN p_usuDireccion VARCHAR(45)
)
BEGIN
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
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `ActualizarUsuario` (
    IN p_id INT,
    IN p_usuIntento INT,
    IN p_usuUsuario VARCHAR(45),
    IN p_usuClave VARCHAR(45),
    IN p_usuRol VARCHAR(45),
    IN p_usuNombres VARCHAR(45),
    IN p_usuApellidos VARCHAR(45),
    IN p_usuTelefono VARCHAR(45),
    IN p_usuDireccion VARCHAR(45)
)
BEGIN
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
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `EliminarUsuario` (
    IN p_id INT
)
BEGIN
    DELETE FROM `RolPagos`.`Usuario`
    WHERE
        `idUsuario` = p_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarTodosUsuarios` ()
BEGIN
    SELECT *
    FROM `Usuario`;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarUsuarioPorID` (
    IN p_idUsuario INT
)
BEGIN
    SELECT *
    FROM `Usuario`
    WHERE
        `idUsuario` = p_idUsuario;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `spObtenerUsuarioPorID` (
    IN p_usuUsuario INT
)
BEGIN
    SELECT `idUsuario`
    FROM `Usuario`
    WHERE
        `usuUsuario` = p_usuUsuario;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `getIdUsuario` (
    IN p_usuUsuario INT
)
BEGIN
    SELECT `idUsuario`
    FROM `Usuario`
    WHERE
        `usuUsuario` = p_usuUsuario;
END //
DELIMITER ;

-- -----------------------------------------------------
-- PROCEDIMIENTOS ALMACENADOS ENCABEZADO
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE `CrearEncabezado` (
    IN p_fkUsuario INT,
    IN p_encFechaEmision VARCHAR(45),
    IN p_encFechaInicio DATE,
    IN p_encFechaFin DATE,
    IN p_encTotalPagar DOUBLE
)
BEGIN
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
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `ActualizarEncabezado` (
    IN p_idEncabezado INT,
    IN p_fkUsuario INT,
    IN p_encFechaEmision VARCHAR(45),
    IN p_encFechaInicio DATE,
    IN p_encFechaFin DATE,
    IN p_encTotalPagar DOUBLE
)
BEGIN
    UPDATE `Encabezado`
    SET
        `fkUsuario` = p_fkUsuario,
        `encFechaEmision` = p_encFechaEmision,
        `encFechaInicio` = p_encFechaInicio,
        `encFechaFin` = p_encFechaFin,
        `encTotalPagar` = p_encTotalPagar
    WHERE
        `idEncabezado` = p_idEncabezado;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `EliminarEncabezado` (
    IN p_idEncabezado INT
)
BEGIN
    DELETE FROM `Encabezado`
    WHERE
        `idEncabezado` = p_idEncabezado;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarTodosEncabezados` ()
BEGIN
    SELECT *
    FROM `Encabezado`;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarEncabezadoPorID` (
    IN p_idEncabezado INT
)
BEGIN
    SELECT *
    FROM `Encabezado`
    WHERE
        `idEncabezado` = p_idEncabezado;
END //
DELIMITER ;


-- -----------------------------------------------------
-- PROCEDIMIENTOS ALMACENADOS DETALLE
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE `CrearDetalle` (
    IN p_fkEncabezado INT,
    IN p_detFechaDetalle DATE,
    IN p_detHoras INT,
    IN p_detValor DOUBLE,
    IN p_detTipo VARCHAR(45)
)
BEGIN
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
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `ActualizarDetalle` (
    IN p_idDetalle INT,
    IN p_fkEncabezado INT,
    IN p_detFechaDetalle DATE,
    IN p_detHoras INT,
    IN p_detValor DOUBLE,
    IN p_detTipo VARCHAR(45)
)
BEGIN
    UPDATE `Detalle`
    SET
        `fkEncabezado` = p_fkEncabezado,
        `detFechaDetalle` = p_detFechaDetalle,
        `detHoras` = p_detHoras,
        `detValor` = p_detValor,
        `detTipo` = p_detTipo
    WHERE
        `idDetalle` = p_idDetalle;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `EliminarDetalle` (
    IN p_idDetalle INT
)
BEGIN
    DELETE FROM `Detalle`
    WHERE
        `idDetalle` = p_idDetalle;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarTodosDetalles` ()
BEGIN
    SELECT *
    FROM `Detalle`;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `SeleccionarDetallePorID` (
    IN p_idDetalle INT
)
BEGIN
    SELECT *
    FROM `Detalle`
    WHERE
        `idDetalle` = p_idDetalle;
END //
DELIMITER ;
