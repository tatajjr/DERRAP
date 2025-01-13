-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema derrap
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema derrap
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `derrap` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `derrap` ;

-- -----------------------------------------------------
-- Table `derrap`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`proveedor` (
  `cif` VARCHAR(30) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cif`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`almacen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`almacen` (
  `ID_pieza` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cantidad` INT NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `precio_compra` DOUBLE NOT NULL,
  `precio_venta` DOUBLE NOT NULL,
  `Proveedor_cif` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ID_pieza`),
  INDEX `fk_Almacen_Proveedor1_idx` (`Proveedor_cif` ASC) VISIBLE,
  CONSTRAINT `fk_Almacen_Proveedor1`
    FOREIGN KEY (`Proveedor_cif`)
    REFERENCES `derrap`.`proveedor` (`cif`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`clientes` (
  `DNI` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DNI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`vehiculos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`vehiculos` (
  `Matricula` VARCHAR(7) NOT NULL,
  `Modelo` VARCHAR(45) NOT NULL,
  `Marca` VARCHAR(45) NOT NULL,
  `anio` INT NOT NULL,
  `kmtotales` INT NOT NULL,
  `Clientes_DNI` INT NOT NULL,
  PRIMARY KEY (`Matricula`),
  INDEX `fk_Vehiculos_Clientes_idx` (`Clientes_DNI` ASC) VISIBLE,
  CONSTRAINT `fk_Vehiculos_Clientes`
    FOREIGN KEY (`Clientes_DNI`)
    REFERENCES `derrap`.`clientes` (`DNI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`cita` (
  `ID_Cita` INT NOT NULL,
  `fecha_reparacion` DATE NOT NULL,
  `Vehiculos_Matricula` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`ID_Cita`),
  INDEX `fk_Cita_Vehiculos1_idx` (`Vehiculos_Matricula` ASC) VISIBLE,
  CONSTRAINT `fk_Cita_Vehiculos1`
    FOREIGN KEY (`Vehiculos_Matricula`)
    REFERENCES `derrap`.`vehiculos` (`Matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`usuarios` (
  `ID_Usuario` VARCHAR(9) NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `tipo` ENUM('Mecanico', 'Administrador') NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`orden_reparacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`orden_reparacion` (
  `ID_Orden` INT NOT NULL,
  `fecha_entrada` DATE NOT NULL,
  `fecha_salida` DATE NOT NULL,
  `estado_rep` ENUM('Sin comenzar', 'En diagnostico', 'En reparacion', 'Finalizada') NOT NULL,
  `estado_ord` ENUM('Pendiente', 'Asignadas') NOT NULL,
  `Vehiculos_Matricula` VARCHAR(7) NOT NULL,
  `Usuarios_ID_Usuario` VARCHAR(9) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_Orden`),
  INDEX `fk_Orden_Reparacion_Vehiculos1_idx` (`Vehiculos_Matricula` ASC) VISIBLE,
  INDEX `fk_Orden_Reparacion_Usuarios1_idx` (`Usuarios_ID_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Orden_Reparacion_Usuarios1`
    FOREIGN KEY (`Usuarios_ID_Usuario`)
    REFERENCES `derrap`.`usuarios` (`ID_Usuario`),
  CONSTRAINT `fk_Orden_Reparacion_Vehiculos1`
    FOREIGN KEY (`Vehiculos_Matricula`)
    REFERENCES `derrap`.`vehiculos` (`Matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`facturación`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`facturación` (
  `ID_Facturacion` INT NOT NULL,
  `precio` DOUBLE NOT NULL,
  `Metodo_pago` ENUM('Tarjeta', 'Efectivo') NOT NULL,
  `Orden_Reparacion_N_Orden` INT NOT NULL,
  INDEX `fk_Facturación_Orden_Reparacion1_idx` (`Orden_Reparacion_N_Orden` ASC) VISIBLE,
  CONSTRAINT `fk_Facturación_Orden_Reparacion1`
    FOREIGN KEY (`Orden_Reparacion_N_Orden`)
    REFERENCES `derrap`.`orden_reparacion` (`ID_Orden`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`pedidos_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`pedidos_material` (
  `idPedidos_Material` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cantidad` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPedidos_Material`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`peticion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`peticion` (
  `Orden_Reparacion_ID_Orden` INT NOT NULL,
  `Pedidos_Material_idPedidos_Material` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`Orden_Reparacion_ID_Orden`, `Pedidos_Material_idPedidos_Material`),
  INDEX `fk_Orden_Reparacion_has_Pedidos_Material_Pedidos_Material1_idx` (`Pedidos_Material_idPedidos_Material` ASC) VISIBLE,
  INDEX `fk_Orden_Reparacion_has_Pedidos_Material_Orden_Reparacion1_idx` (`Orden_Reparacion_ID_Orden` ASC) VISIBLE,
  CONSTRAINT `fk_Orden_Reparacion_has_Pedidos_Material_Orden_Reparacion1`
    FOREIGN KEY (`Orden_Reparacion_ID_Orden`)
    REFERENCES `derrap`.`orden_reparacion` (`ID_Orden`),
  CONSTRAINT `fk_Orden_Reparacion_has_Pedidos_Material_Pedidos_Material1`
    FOREIGN KEY (`Pedidos_Material_idPedidos_Material`)
    REFERENCES `derrap`.`pedidos_material` (`idPedidos_Material`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`recambio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`recambio` (
  `Orden_Reparacion_ID_Orden` INT NOT NULL,
  `Almacen_ID_pieza` VARCHAR(10) NOT NULL,
  `id_recambio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Orden_Reparacion_ID_Orden`, `Almacen_ID_pieza`),
  INDEX `fk_Orden_Reparacion_has_Almacen_Almacen1_idx` (`Almacen_ID_pieza` ASC) VISIBLE,
  INDEX `fk_Orden_Reparacion_has_Almacen_Orden_Reparacion1_idx` (`Orden_Reparacion_ID_Orden` ASC) VISIBLE,
  CONSTRAINT `fk_Orden_Reparacion_has_Almacen_Almacen1`
    FOREIGN KEY (`Almacen_ID_pieza`)
    REFERENCES `derrap`.`almacen` (`ID_pieza`),
  CONSTRAINT `fk_Orden_Reparacion_has_Almacen_Orden_Reparacion1`
    FOREIGN KEY (`Orden_Reparacion_ID_Orden`)
    REFERENCES `derrap`.`orden_reparacion` (`ID_Orden`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `derrap`.`servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `derrap`.`servicio` (
  `ID_Servicio` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(100) NOT NULL,
  `Orden_Reparacion_ID_Orden` INT NOT NULL,
  PRIMARY KEY (`ID_Servicio`),
  INDEX `fk_Servicio_Orden_Reparacion1_idx` (`Orden_Reparacion_ID_Orden` ASC) VISIBLE,
  CONSTRAINT `fk_Servicio_Orden_Reparacion1`
    FOREIGN KEY (`Orden_Reparacion_ID_Orden`)
    REFERENCES `derrap`.`orden_reparacion` (`ID_Orden`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
