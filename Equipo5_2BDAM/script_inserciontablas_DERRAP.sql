-- Inserción de datos para todas las tablas de la base de datos 'derrap'

-- Datos para la tabla `proveedor`
INSERT INTO `derrap`.`proveedor` (`cif`, `nombre`, `direccion`, `telefono`, `email`) VALUES
('A12345678', 'Proveedor Uno', 'Calle Falsa 123', 123456789, 'contacto1@proveedor.com'),
('B87654321', 'Proveedor Dos', 'Avenida Siempre Viva 742', 987654321, 'contacto2@proveedor.com'),
('C11223344', 'Proveedor Tres', 'Plaza Mayor 1', 654321987, 'contacto3@proveedor.com'),
('D55667788', 'Proveedor Cuatro', 'Calle Larga 45', 456789123, 'contacto4@proveedor.com'),
('E99887766', 'Proveedor Cinco', 'Ronda Norte 7', 789123456, 'contacto5@proveedor.com'),
('F44332211', 'Proveedor Seis', 'Calle Estrecha 9', 321987654, 'contacto6@proveedor.com'),
('G33445566', 'Proveedor Siete', 'Paseo del Parque 22', 213546879, 'contacto7@proveedor.com'),
('H77665544', 'Proveedor Ocho', 'Carretera Central 56', 147258369, 'contacto8@proveedor.com'),
('I22334455', 'Proveedor Nueve', 'Callejón Oscuro 13', 369258147, 'contacto9@proveedor.com'),
('J88990011', 'Proveedor Diez', 'Autovía del Sur KM 10', 963852741, 'contacto10@proveedor.com');

-- Datos para la tabla `almacen`
INSERT INTO `derrap`.`almacen` (`ID_pieza`, `nombre`, `cantidad`, `marca`, `precio_compra`, `precio_venta`, `Proveedor_cif`) VALUES
('PZ001', 'Filtro de aceite', 50, 'Bosch', 5.5, 10.0, 'A12345678'),
('PZ002', 'Pastillas de freno', 30, 'Ferodo', 15.0, 25.0, 'B87654321'),
('PZ003', 'Amortiguador', 20, 'Monroe', 50.0, 75.0, 'C11223344'),
('PZ004', 'Aceite de motor', 100, 'Castrol', 20.0, 35.0, 'D55667788'),
('PZ005', 'Batería', 40, 'Varta', 60.0, 90.0, 'E99887766'),
('PZ006', 'Correa de distribución', 25, 'Contitech', 40.0, 70.0, 'F44332211'),
('PZ007', 'Filtro de aire', 60, 'Mann', 10.0, 18.0, 'G33445566'),
('PZ008', 'Juego de bujías', 35, 'NGK', 25.0, 40.0, 'H77665544'),
('PZ009', 'Radiador', 15, 'Valeo', 80.0, 120.0, 'I22334455'),
('PZ010', 'Turbo', 10, 'Garrett', 150.0, 250.0, 'J88990011');

-- Datos para la tabla `clientes`
INSERT INTO `derrap`.`clientes` (`DNI`, `Nombre`, `Apellidos`, `Direccion`, `Telefono`, `email`) VALUES
(12345678, 'Juan', 'Pérez', 'Calle Falsa 123', '600111222', 'juan.perez@gmail.com'),
(87654321, 'María', 'Gómez', 'Avenida Siempre Viva 742', '600333444', 'maria.gomez@gmail.com'),
(11223344, 'Luis', 'López', 'Plaza Mayor 1', '600555666', 'luis.lopez@gmail.com'),
(55667788, 'Ana', 'Martínez', 'Calle Larga 45', '600777888', 'ana.martinez@gmail.com'),
(99887766, 'Carlos', 'Sánchez', 'Ronda Norte 7', '600999000', 'carlos.sanchez@gmail.com'),
(44332211, 'Laura', 'Hernández', 'Calle Estrecha 9', '600222333', 'laura.hernandez@gmail.com'),
(33445566, 'David', 'Ruiz', 'Paseo del Parque 22', '600444555', 'david.ruiz@gmail.com'),
(77665544, 'Sara', 'Fernández', 'Carretera Central 56', '600666777', 'sara.fernandez@gmail.com'),
(22334455, 'Jorge', 'Jiménez', 'Callejón Oscuro 13', '600888999', 'jorge.jimenez@gmail.com'),
(88990011, 'Elena', 'Torres', 'Autovía del Sur KM 10', '600000111', 'elena.torres@gmail.com'),
('11224466', 'Pablo', 'García', 'Calle Nueva 12', '601123456', 'pablo.garcia@gmail.com'),
('12348765', 'Lucía', 'Romero', 'Camino Viejo 8', '601987654', 'lucia.romero@gmail.com'),
('22331144', 'Andrés', 'Santos', 'Avenida del Sol 15', '601222333', 'andres.santos@gmail.com'),
('33446688', 'Marta', 'Navarro', 'Plaza del Carmen 5', '601444555', 'marta.navarro@gmail.com'),
('44557799', 'Javier', 'Ortega', 'Calle Luna 20', '601666777', 'javier.ortega@gmail.com'),
('55668800', 'Alba', 'Vargas', 'Carretera Nueva 3', '601888999', 'alba.vargas@gmail.com'),
('66779911', 'Rafael', 'Crespo', 'Paseo de la Victoria 17', '601000111', 'rafael.crespo@gmail.com'),
('77880022', 'Carmen', 'Castro', 'Callejón Bonito 22', '601333444', 'carmen.castro@gmail.com'),
('88991133', 'Isabel', 'Moreno', 'Autovía Norte KM 12', '601555666', 'isabel.moreno@gmail.com'),
('99002244', 'Sergio', 'Molina', 'Ronda Este 8', '601777888', 'sergio.molina@gmail.com');


-- Datos para la tabla `vehiculos`
INSERT INTO `derrap`.`vehiculos` (`Matricula`, `Modelo`, `Marca`, `anio`, `kmtotales`, `Clientes_DNI`) VALUES
('1234ABC', 'Ibiza', 'Seat', 2015, 75000, 12345678),
('5678DEF', 'Focus', 'Ford', 2017, 60000, 87654321),
('9101GHI', 'Civic', 'Honda', 2018, 45000, 11223344),
('1213JKL', 'Corolla', 'Toyota', 2016, 70000, 55667788),
('1415MNO', 'Golf', 'Volkswagen', 2019, 30000, 99887766),
('1617PQR', 'Astra', 'Opel', 2014, 80000, 44332211),
('1819STU', 'Fiesta', 'Ford', 2020, 15000, 33445566),
('2021VWX', 'Qashqai', 'Nissan', 2012, 90000, 77665544),
('2223YZA', 'Corsa', 'Opel', 2013, 85000, 22334455),
('2425BCD', 'Clio', 'Renault', 2011, 95000, 88990011),
('2627EFG', 'Auris', 'Toyota', 2017, 65000, 55667788),
('2829HIJ', 'Leon', 'Seat', 2021, 20000, 12345678),
('3031KLM', 'Passat', 'Volkswagen', 2018, 50000, 99887766),
('3233NOP', 'Meriva', 'Opel', 2016, 72000, 44332211),
('3435QRS', 'Kuga', 'Ford', 2019, 35000, 33445566),
('3637TUV', 'Juke', 'Nissan', 2014, 85000, 77665544),
('3839WXY', 'Kadjar', 'Renault', 2020, 30000, 22334455),
('4041ZAB', 'Twingo', 'Renault', 2013, 87000, 88990011),
('4243CDE', 'Mondeo', 'Ford', 2015, 68000, 87654321),
('4445FGH', 'CR-V', 'Honda', 2019, 40000, 11223344),
('4647IJK', 'Micra', 'Nissan', 2018, 52000, 55667788),
('4849LMN', 'Polo', 'Volkswagen', 2021, 10000, 12345678),
('5051OPQ', 'Zafira', 'Opel', 2017, 63000, 99887766),
('5253RST', 'Focus', 'Ford', 2016, 75000, 44332211),
('5455UVW', 'Captur', 'Renault', 2019, 32000, 33445566),
('5657XYZ', 'Verso', 'Toyota', 2014, 89000, 77665544),
('5859ABC', 'Q3', 'Audi', 2013, 94000, 22334455),
('6061DEF', 'A3', 'Audi', 2018, 48000, 88990011),
('6263GHI', 'Civic', 'Honda', 2020, 23000, 87654321),
('6465JKL', 'Ibiza', 'Seat', 2021, 18000, 11223344);

-- Datos para la tabla `cita`
INSERT INTO `derrap`.`cita` (`ID_Cita`, `fecha_reparacion`, `Vehiculos_Matricula`) VALUES
(1, '2025-01-15', '1234ABC'),
(2, '2025-01-16', '5678DEF'),
(3, '2025-01-17', '9101GHI'),
(4, '2025-01-18', '1213JKL'),
(5, '2025-01-19', '1415MNO'),
(6, '2025-01-20', '1617PQR'),
(7, '2025-01-21', '1819STU'),
(8, '2025-01-22', '2021VWX'),
(9, '2025-01-23', '2223YZA'),
(10, '2025-01-24', '2425BCD');

-- Datos para la tabla `usuarios`
INSERT INTO `derrap`.`usuarios` (`ID_Usuario`, `clave`, `email`, `tipo`, `nombre`) VALUES
('1', '1', 'ivangcortes6@gmail.com', 'Administrador', 'Ivan'),
('2', '2', 'agcisneros@gmail.com', 'Mecanico', 'Alvaro'),
('3', '3', 'mecanico3@derrap.com', 'Mecanico', 'Mecánico Tres'),
('4', '4', 'mecanico4@derrap.com', 'Mecanico', 'Mecánico Cuatro'),
('5', '5', 'mecanico5@derrap.com', 'Mecanico', 'Mecánico Cinco'),
('6', '6', 'mecanico6@derrap.com', 'Mecanico', 'Mecánico Seis'),
('7', '7', 'mecanico7@derrap.com', 'Mecanico', 'Mecánico Siete'),
('8', '8', 'jaimepello@gmail.com', 'Mecanico', 'Jaime'),
('9', '9', 'mecanico9@derrap.com', 'Mecanico', 'Mecánico Nueve'),
('10', '10', 'mecanico10@derrap.com', 'Mecanico', 'Mecánico Diez'),
('11', '11', 'mecanico11@derrap.com', 'Mecanico', 'Mecánico Once'),
('12', '12', 'mecanico12@derrap.com', 'Mecanico', 'Mecánico Doce'),
('13', '13', 'mecanico13@derrap.com', 'Mecanico', 'Mecánico Trece'),
('14', '14', 'mecanico14@derrap.com', 'Mecanico', 'Mecánico Catorce'),
('15', '15', 'mecanico15@derrap.com', 'Mecanico', 'Mecánico Quince'),
('16', '16', 'mecanico16@derrap.com', 'Mecanico', 'Mecánico Dieciséis'),
('17', '17', 'mecanico17@derrap.com', 'Mecanico', 'Mecánico Diecisiete'),
('18', '18', 'mecanico18@derrap.com', 'Mecanico', 'Mecánico Dieciocho'),
('19', '19', 'mecanico19@derrap.com', 'Mecanico', 'Mecánico Diecinueve'),
('20', '20', 'mecanico20@derrap.com', 'Mecanico', 'Mecánico Veinte'),
('21', '21', 'mecanico21@derrap.com', 'Mecanico', 'Mecánico Veintiuno'),
('22', '22', 'mecanico22@derrap.com', 'Mecanico', 'Mecánico Veintidós'),
('23', '23', 'mecanico23@derrap.com', 'Mecanico', 'Mecánico Veintitrés'),
('24', '24', 'mecanico24@derrap.com', 'Mecanico', 'Mecánico Veinticuatro'),
('25', '25', 'mecanico25@derrap.com', 'Mecanico', 'Mecánico Veinticinco'),
('26', '26', 'mecanico26@derrap.com', 'Mecanico', 'Mecánico Veintiséis'),
('27', '27', 'mecanico27@derrap.com', 'Mecanico', 'Mecánico Veintisiete'),
('28', '28', 'mecanico28@derrap.com', 'Mecanico', 'Mecánico Veintiocho');

-- Datos para la tabla `orden_reparacion`
INSERT INTO `derrap`.`orden_reparacion` (`ID_Orden`, `fecha_entrada`, `fecha_salida`, `estado_rep`, `estado_ord`, `Vehiculos_Matricula`, `Usuarios_ID_Usuario`) VALUES
(1, '2025-01-10', '2025-01-12', 'En reparacion', 'Asignadas', '1234ABC', '2'),
(2, '2025-01-11', '2025-01-13', 'En diagnostico', 'Pendiente', '5678DEF', '3'),
(3, '2025-01-12', '2025-01-14', 'Finalizada', 'Asignadas', '9101GHI', '4'),
(4, '2025-01-13', '2025-01-15', 'Sin comenzar', 'Pendiente', '1213JKL', '5'),
(5, '2025-01-14', '2025-01-16', 'En reparacion', 'Asignadas', '1415MNO', '6'),
(6, '2025-01-15', '2025-01-17', 'En diagnostico', 'Pendiente', '1617PQR', '7');

-- Datos for derrap.facturación
INSERT INTO derrap.facturación (ID_Facturacion, precio, Metodo_pago, Orden_Reparacion_N_Orden) VALUES 
(1, 150.00, 'Tarjeta', 1),
(2, 200.00, 'Efectivo', 2),
(3, 120.50, 'Tarjeta', 3),
(4, 75.00, 'Efectivo', 4),
(5, 250.00, 'Tarjeta', 5);

-- Datos for derrap.pedidos_material
INSERT INTO derrap.pedidos_material (idPedidos_Material, nombre, cantidad, descripcion) VALUES 
(1, 'Aceite motor', 10, 'Aceite para vehículos de motor'),
(2, 'Filtro de aire', 20, 'Filtro de aire para vehículos'),
(3, 'Neumáticos', 15, 'Neumáticos para vehículos de uso general'),
(4, 'Bujías', 30, 'Bujías de repuesto para coches'),
(5, 'Pastillas de freno', 25, 'Pastillas de freno');

-- Datos for derrap.peticion
INSERT INTO derrap.peticion (Orden_Reparacion_ID_Orden, Pedidos_Material_idPedidos_Material, fecha) VALUES 
(1, 1, '2025-01-10'),
(2, 2, '2025-01-11'),
(3, 3, '2025-01-12'),
(4, 4, '2025-01-13'),
(5, 5, '2025-01-14');

-- Datos for derrap.recambio
INSERT INTO derrap.recambio (Orden_Reparacion_ID_Orden, Almacen_ID_pieza, id_recambio) VALUES 
(1, 'PZ001', 'RC001'),
(2, 'PZ002', 'RC002'),
(3, 'PZ003', 'RC003'),
(4, 'PZ004', 'RC004'),
(5, 'PZ005', 'RC005');

-- Datos for derrap.servicio
INSERT INTO derrap.servicio (ID_Servicio, tipo, Orden_Reparacion_ID_Orden) VALUES 
('S001', 'Cambio de aceite', 1),
('S002', 'Reemplazo de filtro', 2),
('S003', 'Cambio de neumáticos', 3),
('S004', 'Reemplazo de bujías', 4),
('S005', 'Cambio de pastillas de freno', 5);
