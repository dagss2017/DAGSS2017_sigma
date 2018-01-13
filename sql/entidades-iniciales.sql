-- Ajustar el contador de IDs para que no colision con los IDs anteriores
UPDATE USUARIO_GEN set GEN_VAL=100 WHERE GEN_NAME='USUARIO_GEN';


-- Administrador inicial con login "admin" y contraseña "admin"
INSERT INTO `ADMINISTRADOR` (`ID`,`FECHAALTA`,`LOGIN`,`NOMBRE`,
                             `PASSWORD`,`TIPO_USUARIO`,`ULTIMOACCESO`)
   VALUES (1,'2017-11-11 01:04:42','admin','Administrador inicial',
           'ggm44T97GWJ6Rryx3do4yvl1bZ+gmUfG','ADMINISTRADOR','2017-11-11 01:04:42');

-- Medico con dni "11111111A", num. colegiado "11111" y contraseña "11111"
INSERT INTO `CENTROSALUD` (`ID`,`NOMBRE`,`TELEFONO`,`CODIGOPOSTAL`,
                           `DOMICILIO`,`LOCALIDAD`,`PROVINCIA`)
   VALUES (1,'Centro salud pepe','988888888','12345','C/. Pepe, nº 3','Ourense','Ourense'),
		  (2,'Centro salud sarria','982222222','54321','C/. Siliva, nº 1','Sarria','Lugo'),
		  (3,'Centro salud monforte','982123123','12211','C/. Brais, nº 8','Monforte','Lugo');
INSERT INTO `MEDICO` (`ID`,`APELLIDOS`,`DNI`,`EMAIL`,`FECHAALTA`,`NOMBRE`,
                      `NUMEROCOLEGIADO`,`PASSWORD`,`TELEFONO`,`TIPO_USUARIO`,
                      `ULTIMOACCESO`,`CENTROSALUD_ID`)
   VALUES (2,'Gomez Gomez','11111111A','a@a.com','2017-11-11 01:04:42','Antonio',
           '11111','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','988123456','MEDICO',
           '2017-11-11 01:04:42',1),
		   (3,'Perez Perez','22222222A','bb@b.com','2017-10-10 01:04:42','Sara',
           '22222','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','988654321','MEDICO',
           '2017-11-11 01:04:42',1),
		   (4,'Sainz Sainz','33333333A','cc@c.com','2017-11-11 01:04:42','Paco',
           '33333','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','982112233','MEDICO',
           '2017-11-11 01:04:42',2),
		   (5,'Sotelo Sotelo','44444444A','dd@d.com','2017-11-11 01:04:42','Lourdes',
           '44444','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','98223344','MEDICO',
           '2017-11-11 01:04:42',2),
		   (6,'Fajin Fajin','55555555A','ee@e.com','2017-11-11 01:04:42','Manuel',
           '55555','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','982556699','MEDICO',
           '2017-11-11 01:04:42',3),
		   (7,'Dominguez Iglesias','66666666A','ff@f.com','2017-11-11 01:04:42','Andrea',
           '66666','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','982131313','MEDICO',
           '2017-11-11 01:04:42',3);

-- Paciente con dni "22222222B", num. tarjeta sanitaria "2222222222", num seg. social "2222222222222" y contraseña "22222"
INSERT INTO `PACIENTE` (`ID`,`APELLIDOS`,`DNI`,`EMAIL`,`FECHAALTA`,`NOMBRE`,`NUMEROSEGURIDADSOCIAL`,`NUMEROTARJETASANITARIA`,
                        `PASSWORD`,`TELEFONO`,`TIPO_USUARIO`,`ULTIMOACCESO`,
                        `CODIGOPOSTAL`,`DOMICILIO`,`LOCALIDAD`,`PROVINCIA`,`MEDICO_ID`)
   VALUES (3,'Benito Carmona','22222222B','b@b.com','2017-11-11 01:04:42','Ana','2222222222222','2222222222',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','981123456','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Benito, nº 2, 4º N','Coruña','Coruña',2),
		   (4,'Dominguez Perez','33333333B','c@c.com','2017-10-11 01:00:42','Belen','3333333333333','3333333333',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','981111112','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Benito, nº 10, 4º N','Coruña','Coruña',2),
		   (5,'Perez Sanz','44444444B','d@d.com','2017-19-11 01:01:40','Paco','4444444444444','4444444444',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','981443452','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Lara, nº 4, 2º N','Betanzos','Coruña',2),
		   
		   (6,'Martinez Carmona','55555555B','e@e.com','2017-11-11 01:04:42','Gabriel','5555555555555','5555555555',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','981155556','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Flor, nº 1, 1º N','Coruña','Coruña',2),
		   (7,'Dominguez Vazquez','66666666B','f@f.com','2017-10-11 01:00:42','Marcos','6666666666666','6666666666',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','982444412','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Principal, nº 10, 4º N','Sarria','Lugo',3),
		   (8,'Fajin Sanz','44444444B','g@g.com','2017-19-11 01:01:40','Alonso','7777777777777','7777777777',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','982773752','PACIENTE','2017-11-11 01:07:42',
           '12345','C/. Caracola, nº 4, 4º N','Paradela','Lugo',3),
		   
		   (9,'Banderas Garcia','77777777B','h@h.com','2017-11-11 01:04:42','Saul','8888888888888','8888888888',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','982183456','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Suo, nº 2, 4º N','Lugo','Lugo',3),
		   (10,'Mendez Perez','88888888B','i@i.com','2017-10-11 01:00:42','Marta','9999999999999','9999999999',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','982133112','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Grande, nº 10, 4º N','Sarria','Lugo',3),
		   (11,'Perez Torres','99999999B','j@j.com','2017-19-11 01:01:40','Iria','1212121212124','1212121212',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','982123452','PACIENTE','2017-11-11 01:04:42',
           '12345','C/. Lila, nº 6, 4º N','Lugo','Lugo',2);

-- Cita con fecha "2017-11-23" y hora "09:00:00"
INSERT INTO `CITA`(`ID`,`DURACION`,`ESTADO`,`FECHA`,`HORA`,`MEDICO_ID`,`PACIENTE_ID`)
   VALUES (1, 15, 'PLANIFICADA', '2017-11-23', '09:00:00', '2', '3'),
		  (2, 15, 'PLANIFICADA', '2017-11-23', '09:15:00', '2', '4'),
		  (3, 15, 'PLANIFICADA', '2017-11-23', '09:30:00', '2', '5'),
		  (4, 15, 'PLANIFICADA', '2017-11-23', '09:45:00', '2', '6'),
		  (5, 20, 'PLANIFICADA', '2017-11-23', '10:05:00', '2', '7'),
		  (6, 15, 'PLANIFICADA', '2017-11-23', '09:00:00', '3', '8'),
		  (7, 15, 'PLANIFICADA', '2017-11-23', '09:15:00', '3', '9'),
		  (8, 15, 'PLANIFICADA', '2017-11-23', '09:30:00', '3', '10'),
		  (9, 15, 'PLANIFICADA', '2017-11-23', '09:45:00', '3', '11'),
		  (10, 15, 'PLANIFICADA', '2017-12-23', '09:00:00', '3', '3'),
		  (11, 15, 'PLANIFICADA', '2017-12-23', '09:00:00', '4', '4'),
		  (12, 15, 'PLANIFICADA', '2017-12-23', '09:15:00', '4', '5'),
		  (13, 15, 'PLANIFICADA', '2017-12-23', '09:30:00', '4', '6'),
		  (14, 15, 'PLANIFICADA', '2017-12-23', '09:45:00', '4', '7'),
		  (15, 15, 'PLANIFICADA', '2017-12-23', '10:00:00', '4', '8');

-- Farmacia con nif "33333333C" y contraseña "33333"
INSERT INTO `FARMACIA`(`ID`,`FECHAALTA`,`NIF`,`NOMBREFARMACIA`,
                       `PASSWORD`,`TIPO_USUARIO`,`ULTIMOACCESO`,
                       `CODIGOPOSTAL`,`DOMICILIO`,`LOCALIDAD`,`PROVINCIA`)
   VALUES (4,'2017-11-11 01:04:42','33333333C','Farmacia de prueba',
           '/QpUw+ZRH3ndoNb3N4gRpT5cz0C7pT9v','FARMACIA','2017-11-11 01:04:42',
           '12345','C/. Farmacia, nº 2, 4º N','Coruña','Coruña'),
		   
		   (5,'2017-11-11 01:04:42','33333333C','Farmacia de monforte',
           '/QpUw+ZRH3ndoNb3N4gRpT5cz0C7pT9v','FARMACIA','2017-11-11 01:04:42',
           '12211','C/. Duquesa, nº 1, 1º N','Monforte','Monforte'),
		   
		   (6,'2017-11-11 01:04:42','33333333C','Farmacia de sarria',
           '/QpUw+ZRH3ndoNb3N4gRpT5cz0C7pT9v','FARMACIA','2017-11-11 01:04:42',
           '54321','C/. Principe, nº 6, 2º N','Sarria','Sarria');

-- Medicamentos

INSERT INTO `medicamento` (`ID`, `FABRICANTE`, `FAMILIA`, `NOMBRE`, `NUMERODOSIS`, `PRINCIPIOACTIVO`) 
VALUES (1, 'Fabrica1', 'Vacunas', 'Vacuna gripe', '1', 'Principio1'),
		(2, 'Fabrica1', 'Vacunas', 'Vacuna meningitis', '1', 'Principio2'),
		(3, 'Fabrica1', 'Antibiótico', 'Garamicina', '1', 'Principio3'),
		(4, 'Fabrica1', 'Antibiótico', '	Amikin', '1', 'Principio4'),
		(5, 'Fabrica1', 'Antibiótico', 'Nebcin', '1', 'Principio5');

-- Prescripcion
INSERT INTO `prescripcion` (`ID`, `DOSIS`, `FECHAFIN`, `FECHAINICIO`, `INDICACIONES`, `MEDICAMENTO_ID`, `MEDICO_ID`, `PACIENTE_ID`) 
VALUES (1, '1', '2018-01-20', '2018-01-13', 'No superar la dosis', '1', '2', '3'),
		(2, '1', '2018-01-21', '2018-01-14', 'No superar la dosis', '1', '2', '4'),
		(3, '1', '2018-01-22', '2018-01-15', 'No superar la dosis', '2', '2', '5'),
		(4, '1', '2018-01-23', '2018-01-13', 'No superar la dosis', '1', '2', '6'),
		(5, '1', '2018-01-24', '2018-01-16', 'No superar la dosis', '3', '2', '7'),
		
		(6, '1', '2018-01-25', '2018-01-13', 'No superar la dosis', '4', '3', '3'),
		(7, '1', '2018-01-26', '2018-01-17', 'No superar la dosis', '1', '3', '8'),
		(8, '1', '2018-01-27', '2018-01-18', 'No superar la dosis', '5', '3', '9'),
		(9, '1', '2018-01-28', '2018-01-19', 'No superar la dosis', '2', '3', '10'),
		(10, '1', '2018-01-29', '2018-01-13', 'No superar la dosis', '4', '3', '11');

-- Receta
INSERT INTO `receta` (`ID`, `CANTIDAD`, `ESTADORECETA`, `FINVALIDEZ`, `INICIOVALIDEZ`, `FARMACIADISPENSADORA_ID`, `PRESCRIPCION_ID`) 
VALUES (1, '1', 'Estado1', '2018-01-20', '2018-01-13', '4', '1'),
		(2, '1', 'Estado1', '2018-01-20', '2018-01-13', '4', '2'),
		(3, '1', 'Estado1', '2018-01-20', '2018-01-13', '4', '3'),
		(4, '1', 'Estado1', '2018-01-20', '2018-01-13', '5', '4'),
		(5, '1', 'Estado1', '2018-01-20', '2018-01-13', '5', '5'),
		(6, '1', 'Estado1', '2018-01-20', '2018-01-13', '5', '6'),
		(7, '1', 'Estado1', '2018-01-20', '2018-01-13', '6', '7'),
		(8, '1', 'Estado1', '2018-01-20', '2018-01-13', '6', '8'),
		(9, '1', 'Estado1', '2018-01-20', '2018-01-13', '6', '9'),
		(10, '1', 'Estado1', '2018-01-20', '2018-01-13', '6', '10');