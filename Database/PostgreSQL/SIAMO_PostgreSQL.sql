
CREATE TABLE Apoyo_informe
(
  id_tecnico         int NOT NULL,
  id_informe_tecnico int NOT NULL,
  PRIMARY KEY (id_tecnico, id_informe_tecnico)
);

COMMENT ON TABLE Apoyo_informe IS 'técnicos de apoyo';

CREATE TABLE Cliente
(
  id_cliente int NOT NULL,
  id_persona int NOT NULL,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE Empleado
(
  id_empleado   int          NOT NULL,
  id_persona    int          NOT NULL,
  fecha_ingreso date         NOT NULL,
  cod_empleado  int          NOT NULL,
  contrasenia   varchar(255) NOT NULL,
  PRIMARY KEY (id_empleado)
);

COMMENT ON COLUMN Empleado.id_empleado IS 'privado';

COMMENT ON COLUMN Empleado.fecha_ingreso IS 'contratación';

COMMENT ON COLUMN Empleado.cod_empleado IS 'público (inicio sesión)';

CREATE TABLE Informe_tecnico
(
  id_informe_tecnico int          NOT NULL,
  id_ost             int          NOT NULL,
  detalle            varchar(250),
  PRIMARY KEY (id_informe_tecnico)
);

COMMENT ON COLUMN Informe_tecnico.detalle IS 'de reparaciones';

CREATE TABLE Orden_Servicio_Tecnico
(
  id_ost         int          NOT NULL,
  id_cliente     int          NOT NULL,
  id_tecnico     int          NOT NULL,
  placa          varchar(8)   NOT NULL,
  marca          varchar(15)  NOT NULL,
  modelo         varchar(50)  NOT NULL,
  estado         boolean      NOT NULL,
  fecha_registro date         NOT NULL,
  prob_declarado varchar(200) NOT NULL,
  PRIMARY KEY (id_ost)
);

COMMENT ON COLUMN Orden_Servicio_Tecnico.id_tecnico IS 'asignado';

COMMENT ON COLUMN Orden_Servicio_Tecnico.placa IS 'auto';

COMMENT ON COLUMN Orden_Servicio_Tecnico.marca IS 'auto';

COMMENT ON COLUMN Orden_Servicio_Tecnico.modelo IS 'auto';

COMMENT ON COLUMN Orden_Servicio_Tecnico.estado IS 'true: en proceso, false: resuelto';

COMMENT ON COLUMN Orden_Servicio_Tecnico.prob_declarado IS 'por el cliente';

CREATE TABLE Persona
(
  id_persona int          NOT NULL,
  tipo_doc   boolean      NOT NULL,
  num_doc    varchar(8)   NOT NULL,
  nombres    varchar(30)  NOT NULL,
  apellidos  varchar(50)  NOT NULL,
  direccion  varchar(100) NOT NULL,
  sexo       char(1)      NOT NULL,
  telefono   varchar(9)   NOT NULL,
  correo     varchar(50)  NOT NULL,
  PRIMARY KEY (id_persona)
);

COMMENT ON COLUMN Persona.tipo_doc IS 'true: dni, false: carnet extranjería';

COMMENT ON COLUMN Persona.sexo IS 'F: femenino, M: masculino';

CREATE TABLE Problema
(
  id_problema int          NOT NULL,
  id_solucion int          NOT NULL,
  descripcion varchar(50)  NOT NULL,
  detalle     varchar(250) NOT NULL,
  PRIMARY KEY (id_problema)
);

COMMENT ON COLUMN Problema.descripcion IS 'breve';

CREATE TABLE Problemas_informe
(
  id_problema        int NOT NULL,
  id_informe_tecnico int NOT NULL,
  PRIMARY KEY (id_problema, id_informe_tecnico)
);

CREATE TABLE Recepcionista
(
  id_recepcionista int NOT NULL,
  id_empleado      int NOT NULL,
  PRIMARY KEY (id_recepcionista)
);

CREATE TABLE Solucion
(
  id_solucion int          NOT NULL,
  descripcion varchar(250) NOT NULL,
  PRIMARY KEY (id_solucion)
);

CREATE TABLE Tecnico
(
  id_tecnico  int NOT NULL,
  id_empleado int NOT NULL,
  PRIMARY KEY (id_tecnico)
);

ALTER TABLE Cliente
  ADD CONSTRAINT FK_Persona_TO_Cliente
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

ALTER TABLE Empleado
  ADD CONSTRAINT FK_Persona_TO_Empleado
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

ALTER TABLE Recepcionista
  ADD CONSTRAINT FK_Empleado_TO_Recepcionista
    FOREIGN KEY (id_empleado)
    REFERENCES Empleado (id_empleado);

ALTER TABLE Tecnico
  ADD CONSTRAINT FK_Empleado_TO_Tecnico
    FOREIGN KEY (id_empleado)
    REFERENCES Empleado (id_empleado);

ALTER TABLE Orden_Servicio_Tecnico
  ADD CONSTRAINT FK_Cliente_TO_Orden_Servicio_Tecnico
    FOREIGN KEY (id_cliente)
    REFERENCES Cliente (id_cliente);

ALTER TABLE Informe_tecnico
  ADD CONSTRAINT FK_Orden_Servicio_Tecnico_TO_Informe_tecnico
    FOREIGN KEY (id_ost)
    REFERENCES Orden_Servicio_Tecnico (id_ost);

ALTER TABLE Problema
  ADD CONSTRAINT FK_Solucion_TO_Problema
    FOREIGN KEY (id_solucion)
    REFERENCES Solucion (id_solucion);

ALTER TABLE Orden_Servicio_Tecnico
  ADD CONSTRAINT FK_Tecnico_TO_Orden_Servicio_Tecnico
    FOREIGN KEY (id_tecnico)
    REFERENCES Tecnico (id_tecnico);

ALTER TABLE Apoyo_informe
  ADD CONSTRAINT FK_Tecnico_TO_Apoyo_informe
    FOREIGN KEY (id_tecnico)
    REFERENCES Tecnico (id_tecnico);

ALTER TABLE Apoyo_informe
  ADD CONSTRAINT FK_Informe_tecnico_TO_Apoyo_informe
    FOREIGN KEY (id_informe_tecnico)
    REFERENCES Informe_tecnico (id_informe_tecnico);

ALTER TABLE Problemas_informe
  ADD CONSTRAINT FK_Problema_TO_Problemas_informe
    FOREIGN KEY (id_problema)
    REFERENCES Problema (id_problema);

ALTER TABLE Problemas_informe
  ADD CONSTRAINT FK_Informe_tecnico_TO_Problemas_informe
    FOREIGN KEY (id_informe_tecnico)
    REFERENCES Informe_tecnico (id_informe_tecnico);

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
