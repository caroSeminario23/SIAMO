
CREATE TABLE Automovil
(
  id_automovil INTEGER NOT NULL,
  placa        TEXT    NOT NULL,
  marca        TEXT    NOT NULL,
  modelo       TEXT    NOT NULL,
  id_cliente   INTEGER NOT NULL,
  PRIMARY KEY (id_automovil AUTOINCREMENT),
  FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente)
);

CREATE TABLE Cliente
(
  id_cliente INTEGER NOT NULL,
  id_persona INTEGER NOT NULL,
  PRIMARY KEY (id_cliente AUTOINCREMENT),
  FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);

CREATE TABLE Consulta
(
  id_consulta    INTEGER NOT NULL,
  id_cliente     INTEGER NOT NULL,
  -- por el cliente
  prob_declarado TEXT    NOT NULL,
  -- diagnostico inicial
  id_tecnico     INTEGER NOT NULL,
  -- 0: en espera, 1: cancelado, 2: procede
  estado         INTEGER NOT NULL DEFAULT 0,
  id_automovil   INTEGER NOT NULL,
  PRIMARY KEY (id_consulta AUTOINCREMENT),
  FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente),
  FOREIGN KEY (id_tecnico) REFERENCES Tecnico (id_tecnico),
  FOREIGN KEY (id_automovil) REFERENCES Automovil (id_automovil)
);

CREATE TABLE Empleado
(
  -- privado
  id_empleado   INTEGER NOT NULL,
  id_persona    INTEGER NOT NULL,
  -- contratación YYYY-MM-DD
  fecha_ingreso TEXT    NOT NULL,
  -- público (inicio sesión)
  cod_empleado  INTEGER NOT NULL,
  contrasenia   TEXT    NOT NULL,
  PRIMARY KEY (id_empleado AUTOINCREMENT),
  FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);

-- inventario pre internacion
CREATE TABLE Estado_vehiculo
(
  id_estado_vehiculo INTEGER NOT NULL,
  estado_carroceria  TEXT    NOT NULL,
  estado_neumaticos  TEXT    NOT NULL,
  estado_motor       TEXT    NOT NULL,
  estado_frenos      TEXT    NOT NULL,
  id_ficha_ingreso   INTEGER NOT NULL,
  PRIMARY KEY (id_estado_vehiculo AUTOINCREMENT),
  FOREIGN KEY (id_ficha_ingreso) REFERENCES Ficha_ingreso (id_ficha_ingreso)
);

CREATE TABLE Ficha_ingreso
(
  id_ficha_ingreso     INTEGER NOT NULL,
  -- YYYY-MM-DD HH:MM:SS
  fecha_ingreso        TEXT    NOT NULL,
  -- YYYY-MM-DD HH:MM:SS
  fecha_recogida_aprox TEXT    NOT NULL,
  id_ost               INTEGER NOT NULL,
  PRIMARY KEY (id_ficha_ingreso AUTOINCREMENT),
  FOREIGN KEY (id_ost) REFERENCES Orden_Servicio_Tecnico (id_ost)
);

CREATE TABLE Ficha_salida
(
  id_ficha_salida INTEGER NOT NULL,
  -- real YYYY-MM-DD HH:MM:SS
  fecha_recojo    TEXT    NOT NULL,
  id_ost          INTEGER NOT NULL,
  PRIMARY KEY (id_ficha_salida AUTOINCREMENT),
  FOREIGN KEY (id_ost) REFERENCES Orden_Servicio_Tecnico (id_ost)
);

CREATE TABLE Imprevisto
(
  id_imprevisto INTEGER       NOT NULL,
  descripcion   TEXT          NOT NULL,
  solucion      TEXT          NOT NULL,
  precio        NUMERIC(7, 2) NOT NULL,
  PRIMARY KEY (id_imprevisto AUTOINCREMENT)
);

CREATE TABLE Informe_tecnico
(
  id_informe_tecnico      INTEGER       NOT NULL,
  id_ost                  INTEGER       NOT NULL,
  -- YYYY-MM-DD
  fecha_inicio_reparacion TEXT          NULL    ,
  -- de reparaciones
  detalle_reparacion      TEXT          NULL    ,
  observaciones           TEXT          NULL    ,
  saldo_final             NUMERIC(7, 2) NOT NULL,
  -- YYYY-MM-DD
  fecha_fin_reparacion    TEXT          NULL    ,
  id_imprevisto           INTEGER       NOT NULL,
  PRIMARY KEY (id_informe_tecnico),
  FOREIGN KEY (id_ost) REFERENCES Orden_Servicio_Tecnico (id_ost),
  FOREIGN KEY (id_imprevisto) REFERENCES Imprevisto (id_imprevisto)
);

-- por el tecnico
CREATE TABLE Lista_problemas
(
  id_consulta INTEGER NOT NULL,
  id_problema INTEGER NOT NULL,
  PRIMARY KEY (id_consulta, id_problema),
  FOREIGN KEY (id_consulta) REFERENCES Consulta (id_consulta),
  FOREIGN KEY (id_problema) REFERENCES Problema (id_problema)
);

CREATE TABLE Lista_tareas
(
  id_lista_tareas INTEGER NOT NULL,
  id_ost          INTEGER NOT NULL,
  PRIMARY KEY (id_lista_tareas AUTOINCREMENT),
  FOREIGN KEY (id_ost) REFERENCES Orden_Servicio_Tecnico (id_ost)
);

CREATE TABLE Orden_Servicio_Tecnico
(
  id_ost              INTEGER NOT NULL,
  -- YYYY-MM-DD
  fecha_registro      TEXT    NOT NULL,
  -- 0: en proceso, 1: resuelto, 2: cancelado, 3: abandonado
  estado              INTEGER NOT NULL,
  id_consulta         INTEGER NOT NULL,
  -- YYYY-MM-DD
  fecha_aprox_ingreso TEXT    NOT NULL,
  PRIMARY KEY (id_ost AUTOINCREMENT),
  FOREIGN KEY (id_consulta) REFERENCES Consulta (id_consulta)
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
  -- F: femenino, M: masculino
  sexo       TEXT    NOT NULL,
  telefono   TEXT    NOT NULL,
  correo     TEXT    NOT NULL,
  PRIMARY KEY (id_persona AUTOINCREMENT)
);

CREATE TABLE Presupuesto
(
  id_presupuesto      INTEGER       NOT NULL,
  id_consulta         INTEGER       NOT NULL,
  -- segun n tecnicos y complejidad
  tarifa_mano_obra    NUMERIC(7, 2) NOT NULL,
  tarifa_repuestos    NUMERIC(7, 2) NOT NULL DEFAULT 0,
  descuento_negociado NUMERIC(7, 2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_presupuesto AUTOINCREMENT),
  FOREIGN KEY (id_consulta) REFERENCES Consulta (id_consulta)
);

CREATE TABLE Presupuesto_repuesto
(
  id_presupuesto INTEGER NOT NULL,
  id_repuesto    INTEGER NOT NULL,
  cantidad       INTEGER NOT NULL DEFAULT 1,
  PRIMARY KEY (id_presupuesto, id_repuesto),
  FOREIGN KEY (id_presupuesto) REFERENCES Presupuesto (id_presupuesto),
  FOREIGN KEY (id_repuesto) REFERENCES Repuesto (id_repuesto)
);

-- bitacora
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

CREATE TABLE Recepcionista
(
  id_recepcionista INTEGER NOT NULL,
  id_empleado      INTEGER NOT NULL,
  PRIMARY KEY (id_recepcionista AUTOINCREMENT),
  FOREIGN KEY (id_empleado) REFERENCES Empleado (id_empleado)
);

CREATE TABLE Repuesto
(
  id_repuesto INTEGER       NOT NULL,
  descripcion TEXT          NOT NULL UNIQUE,
  precio      NUMERIC(7, 2) NOT NULL,
  PRIMARY KEY (id_repuesto AUTOINCREMENT)
);

-- bitacora
CREATE TABLE Solucion
(
  id_solucion INTEGER NOT NULL,
  descripcion TEXT    NOT NULL,
  PRIMARY KEY (id_solucion AUTOINCREMENT)
);

CREATE TABLE Tarea
(
  id_tarea        INTEGER NOT NULL,
  descripcion     TEXT    NOT NULL,
  id_lista_tareas INTEGER NOT NULL,
  id_tecnico      INTEGER NOT NULL,
  -- 0: completado, 1: no completado
  estado          INTEGER NOT NULL DEFAULT 1,
  PRIMARY KEY (id_tarea AUTOINCREMENT),
  FOREIGN KEY (id_lista_tareas) REFERENCES Lista_tareas (id_lista_tareas),
  FOREIGN KEY (id_tecnico) REFERENCES Tecnico (id_tecnico)
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

CREATE UNIQUE INDEX id_persona
  ON Problema (id_problema ASC);

CREATE UNIQUE INDEX id_solucion
  ON Solucion (id_solucion ASC);
