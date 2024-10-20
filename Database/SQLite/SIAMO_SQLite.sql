
-- técnicos de apoyo
CREATE TABLE Apoyo_informe
(
  id_tecnico         INTEGER NOT NULL,
  id_informe_tecnico INTEGER NOT NULL,
  PRIMARY KEY (id_tecnico, id_informe_tecnico),
  FOREIGN KEY (id_tecnico) REFERENCES Tecnico (id_tecnico),
  FOREIGN KEY (id_informe_tecnico) REFERENCES Informe_tecnico (id_informe_tecnico)
);

CREATE TABLE Cliente
(
  id_cliente INTEGER NOT NULL,
  id_persona INTEGER NOT NULL,
  PRIMARY KEY (id_cliente AUTOINCREMENT),
  FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);

CREATE TABLE Empleado
(
  -- privado
  id_empleado   INTEGER NOT NULL,
  id_persona    INTEGER NOT NULL,
  -- contratación (YYYY-MM-DD)
  fecha_ingreso TEXT    NOT NULL,
  -- público (inicio sesión)
  cod_empleado  INTEGER NOT NULL UNIQUE,
  contrasenia   TEXT    NOT NULL,
  PRIMARY KEY (id_empleado AUTOINCREMENT),
  FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);

CREATE TABLE Informe_tecnico
(
  id_informe_tecnico INTEGER NOT NULL,
  id_ost             INTEGER NOT NULL,
  -- de reparaciones
  detalle            TEXT    NULL    ,
  PRIMARY KEY (id_informe_tecnico AUTOINCREMENT),
  FOREIGN KEY (id_ost) REFERENCES Orden_Servicio_Tecnico (id_ost)
);

CREATE TABLE Orden_Servicio_Tecnico
(
  id_ost         INTEGER NOT NULL,
  id_cliente     INTEGER NOT NULL,
  -- asignado
  id_tecnico     INTEGER NOT NULL,
  -- auto
  placa          TEXT    NOT NULL,
  -- auto
  marca          TEXT    NOT NULL,
  -- auto
  modelo         TEXT    NOT NULL,
  -- 0: en proceso, 1: resuelto
  estado         INTEGER NOT NULL,
  -- (YYYY-MM-DD)
  fecha_registro TEXT    NOT NULL,
  -- por el cliente
  prob_declarado TEXT    NOT NULL,
  PRIMARY KEY (id_ost AUTOINCREMENT),
  FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente),
  FOREIGN KEY (id_tecnico) REFERENCES Tecnico (id_tecnico)
);

CREATE TABLE Persona
(
  id_persona INTEGER NOT NULL,
  -- 0: dni, 1: carnet extranjería
  tipo_doc   INTEGER NOT NULL,
  num_doc    TEXT    NOT NULL UNIQUE,
  nombres    TEXT    NOT NULL,
  apellidos  TEXT    NOT NULL,
  direccion  TEXT    NOT NULL,
  -- 0: femenino, 1: masculino
  sexo       INTEGER NOT NULL,
  telefono   TEXT    NOT NULL UNIQUE,
  correo     TEXT    NOT NULL UNIQUE,
  PRIMARY KEY (id_persona AUTOINCREMENT)
);

CREATE TABLE Problema
(
  id_problema INTEGER NOT NULL,
  id_solucion INTEGER NOT NULL,
  -- breve
  descripcion TEXT    NOT NULL UNIQUE,
  detalle     TEXT    NOT NULL,
  PRIMARY KEY (id_problema AUTOINCREMENT),
  FOREIGN KEY (id_solucion) REFERENCES Solucion (id_solucion)
);

CREATE TABLE Problemas_informe
(
  id_problema        INTEGER NOT NULL,
  id_informe_tecnico INTEGER NOT NULL,
  PRIMARY KEY (id_problema, id_informe_tecnico),
  FOREIGN KEY (id_problema) REFERENCES Problema (id_problema),
  FOREIGN KEY (id_informe_tecnico) REFERENCES Informe_tecnico (id_informe_tecnico)
);

CREATE TABLE Recepcionista
(
  id_recepcionista INTEGER NOT NULL,
  id_empleado      INTEGER NOT NULL,
  PRIMARY KEY (id_recepcionista AUTOINCREMENT),
  FOREIGN KEY (id_empleado) REFERENCES Empleado (id_empleado)
);

CREATE TABLE Solucion
(
  id_solucion INTEGER NOT NULL,
  descripcion TEXT    NOT NULL,
  PRIMARY KEY (id_solucion AUTOINCREMENT)
);

CREATE TABLE Tecnico
(
  id_tecnico  INTEGER NOT NULL,
  id_empleado INTEGER NOT NULL,
  PRIMARY KEY (id_tecnico AUTOINCREMENT),
  FOREIGN KEY (id_empleado) REFERENCES Empleado (id_empleado)
);

CREATE UNIQUE INDEX id_persona
  ON Persona (id_persona ASC);

CREATE UNIQUE INDEX id_cliente
  ON Cliente (id_cliente ASC);

CREATE UNIQUE INDEX id_empleado
  ON Empleado (id_empleado ASC);

CREATE UNIQUE INDEX id_recepcionista
  ON Recepcionista (id_recepcionista ASC);

CREATE UNIQUE INDEX id_tecnico
  ON Tecnico (id_tecnico ASC);

CREATE UNIQUE INDEX id_ost
  ON Orden_Servicio_Tecnico (id_ost ASC);

CREATE UNIQUE INDEX id_detalle_ost
  ON Informe_tecnico (id_informe_tecnico ASC);

CREATE UNIQUE INDEX id_problema
  ON Problema (id_problema ASC);

CREATE UNIQUE INDEX id_solucion
  ON Solucion (id_solucion ASC);
