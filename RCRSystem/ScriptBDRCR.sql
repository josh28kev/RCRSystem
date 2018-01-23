create TABLE Materiales
( codigoMaterial VARCHAR,
  nombre VARCHAR,
  CONSTRAINT PKMATERIALES PRIMARY KEY (codigoMaterial)
);

-----------------------------------------------------------------------------------------------------

CREATE TABLE TipoMaterial
( codigoTM VARCHAR,
  Tmaterial VARCHAR,
  CONSTRAINT PKTipoMaterial PRIMARY KEY (codigoTM),
  CONSTRAINT FKTipoMaterial FOREIGN KEY (Tmaterial) REFERENCES Materiales (codigoMaterial) 
);

---------------------------------------------------------------------------------------------------

CREATE TABLE Bulto
( codigoBulto VARCHAR,
  tipoBulto INT,
  pesoBulto FLOAT,
  materialBulto VARCHAR,
  estado INT DEFAULT 1, --si es 1 = bulto comprado 0 = vendido
  CONSTRAINT PKBulto PRIMARY KEY (codigoBulto),
  CONSTRAINT FKBulto FOREIGN KEY (materialBulto) REFERENCES TipoMaterial (codigoTM) 
);

------------------------------------------------------------------------------------------------------------------

CREATE TABLE RegistroCompra
( numCompra INT,
  pesoTotal FLOAT,
  bultosTotal INT,
  CONSTRAINT PKRegistroCompra PRIMARY KEY (numCompra)
);

-----------------------------------------------------------------------------------------------------------------

 create sequence sec_numeroRegCompra
  start with 1
  increment by 1
  cycle;
  
-----------------------------------------------------------------------------------------------------------------

 create sequence sec_codListEmpaque
  start with 1
  increment by 1
  cycle;
  
-----------------------------------------------------------------------------------------------------------------

CREATE TABLE RegCompra_U_Bulto
( regCompra INT,
  bulto VARCHAR,
  CONSTRAINT FKRegCompra_U_Bulto FOREIGN KEY (regCompra) REFERENCES RegistroCompra (numCompra) ,
  CONSTRAINT FKRegCompra_U_Bulto2 FOREIGN KEY (bulto) REFERENCES Bulto (codigoBulto) 
);

-----------------------------------------------------------------------------------------------------------

CREATE TABLE TotalMaterialComprado
( materialComprado VARCHAR,
  regComp INT,
  pesoTotalC FLOAT,
  cantBultosC INT DEFAULT 1,
  CONSTRAINT PKTotalMaterialComprado  PRIMARY KEY (materialComprado,regComp),
  CONSTRAINT FKTotalMaterialComprado1 FOREIGN KEY (materialComprado) REFERENCES Materiales,
  CONSTRAINT FKTotalMaterialComprado2 FOREIGN KEY (regComp) REFERENCES RegistroCompra
);

-------------------------------------------------------------------------------------------------------

CREATE TABLE Inventario
( material VARCHAR,
  cantidadT FLOAT,
  CONSTRAINT PKInventario  PRIMARY KEY (material),
  CONSTRAINT FKInventario FOREIGN KEY (material) REFERENCES Materiales
);

------------------------------------------------------------------------------------------------------

CREATE TABLE Proveedor
( codigo VARCHAR,
  nombre VARCHAR,
  telFijo INTEGER,
  telCelular INTEGER,
  lugar VARCHAR,
  personaContacto VARCHAR,
  CONSTRAINT PKProveedor  PRIMARY KEY (codigo)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE ReporteCompra
( regCompra INTEGER,
  fecha DATE,
  proveedor VARCHAR,
  chofer VARCHAR,
  placaVehiculo VARCHAR,
  tiposBultos INT, -- Este atributo es para los tipos de bultos que entraron en la compra. Si fueron solo pacas=1, solo sacas = 2, ambos =3
  CONSTRAINT PKReporteCompra  PRIMARY KEY (regCompra),
  CONSTRAINT FKReporteCompra1 FOREIGN KEY (regCompra) REFERENCES RegistroCompra,
  CONSTRAINT FKReporteCompra2 FOREIGN KEY (proveedor) REFERENCES Proveedor
);

------------------------------------------------------------------------------------------------------

CREATE TABLE Usuarios
( identificacion VARCHAR,
  nombre VARCHAR,
  numTelefono FLOAT,
  puesto INTEGER,
  contraseña VARCHAR,
  CONSTRAINT PKUsuarios PRIMARY KEY (identificacion)
);

------------------------------------------------------------------------------------   


 
 ------------------------------------------------------------------------------------------------------

create TABLE Cliente
( codigoC VARCHAR,
  nombreC VARCHAR,
  telefonoC VARCHAR,
  direccionC VARCHAR,
  contactoC VARCHAR,
  CONSTRAINT PKCliente  PRIMARY KEY (codigoC)
);

------------------------------------------------------------------------------------------------------

create TABLE Conductor
( identificacionConductor VARCHAR,
  nombreConductor VARCHAR,
  nacionalidadConductor VARCHAR,
  fechaNacimientoConductor DATE,
  CONSTRAINT PKConductor  PRIMARY KEY (identificacionConductor)
);

------------------------------------------------------------------------------------------------------

CREATE TABLE ListaEmpaque
( codigoListEm INT,
  fechaLE DATE,
  medioTransporte INT,
  clienteLE VARCHAR,
  destino VARCHAR,
  pesoBruto FLOAT,
  pesoNeto FLOAT,
  conductor VARCHAR,
  placa VARCHAR,
  marca VARCHAR,
  chasis VARCHAR,
  furgon VARCHAR,
  estado INTEGER DEFAULT 0,  --si es 1 = lista facturada / 0 = lista pendiente por facturar
  totalBultos INT,
  numMarchamo VARCHAR,
  transportista VARCHAR,
  codigoTransportista VARCHAR,
  numeroContenedor VARCHAR,
  CONSTRAINT PKListaEmpaque PRIMARY KEY (codigoListEm),
  CONSTRAINT FKListaEmpaque FOREIGN KEY (clienteLE) REFERENCES Cliente (codigoC),
  CONSTRAINT FKListaEmpaque2 FOREIGN KEY (conductor) REFERENCES Conductor (identificacionConductor)
);

------------------------------------------------------------------------------------------------------

CREATE TABLE ListEmpaque_U_Bulto
( listEmpaque INT,
  bultoVendido VARCHAR,
  CONSTRAINT FKListEmpaque_U_Bulto FOREIGN KEY (listEmpaque) REFERENCES ListaEmpaque (codigoListEm) ,
  CONSTRAINT FKListEmpaque_U_Bulto2 FOREIGN KEY (bultoVendido) REFERENCES Bulto (codigoBulto) 
);

---------------------------------------------------------------

create or replace function ingresarTotalMaterialComprado(IN XtipoBultoComprado character varying, IN XregCompra integer,IN XpesoBulto FLOAT)-------Procedimiento que se llamará desde la aplicacion Java para insertar y/o actualizar el/los material(es) ingresados en el registro de Compra
RETURNS integer AS
$BODY$
  DECLARE
  v_cant int DEFAULT 0;
  BEGIN
SELECT COUNT(1) INTO v_cant FROM TotalMaterialComprado WHERE  regComp = XregCompra
and materialComprado = XtipoBultoComprado;
IF V_CANT > 0 THEN ------------si ya existe un registro con material XtipoBultoComprado actualice el pesototal 

UPDATE TotalMaterialComprado SET pesoTotalC = pesoTotalC + XpesoBulto,
		cantBultosC = cantBultosC + 1
WHERE  regComp = XregCompra
and materialComprado = XtipoBultoComprado;
RETURN 0;
     END IF;
-- SINO
INSERT INTO TotalMaterialComprado(materialComprado,regComp,pesoTotalC)
VALUES (XtipoBultoComprado,XregCompra,XpesoBulto);

     RETURN 1;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
------------------------------------------------------------------------------------
   
create or replace function actualizarCantidadInv()
RETURNS TRIGGER AS $actualizarCantidadInv$
  DECLARE
  BEGIN
UPDATE Inventario SET cantidadT = cantidadT + NEW.pesoBulto
from Materiales,Bulto,TipoMaterial
where Inventario.material = TipoMaterial.Tmaterial 
and TipoMaterial.codigoTM = NEW.materialBulto;
   RETURN NEW;
  END;
$actualizarCantidadInv$ LANGUAGE plpgsql;
---------------------------------------------------------------------------------------------------

CREATE TRIGGER actualizarCantidadInv 
  BEFORE INSERT ON Bulto 
  for each row EXECUTE PROCEDURE actualizarCantidadInv();
------------------------------------------------------------------------------------

CREATE TABLE TotalMaterialVendido
( materialVendido VARCHAR,
  listEmpaque INT,
  cantBultosV INT DEFAULT 1,
  pesoTotalV FLOAT,
  precioUnid FLOAT,
  importe FLOAT,
  CONSTRAINT PKTotalMaterialVendido  PRIMARY KEY (materialVendido,listEmpaque),
  CONSTRAINT FKTotalMaterialVendido1 FOREIGN KEY (materialVendido) REFERENCES Materiales,
  CONSTRAINT FKTotalMaterialVendido2 FOREIGN KEY (listEmpaque) REFERENCES ListaEmpaque
);

------------------------------------------------------------------------------------

CREATE TABLE Factura
( numeroF VARCHAR,
  listEmpaque INT,
  tipoFactura INT, -- 0 = exportacion 1= local o nacional
  cliente VARCHAR,
  enviadoA VARCHAR,
  fecha DATE,
  paisOrigen VARCHAR,
  fechaEmbarque DATE,
  puertoEmbarque VARCHAR,
  fechaExportacion DATE,
  metodoTransporte INT,
  puertoExportacion VARCHAR,
  puertoDestino VARCHAR,
  PO VARCHAR,
  Icoterm VARCHAR,
  fechaDespacho DATE,
  totalBultos INT,
  pesoNet FLOAT,
  pesoBrut FLOAT,
  flete FLOAT,
  subTotal FLOAT,
  totalF FLOAT,
  CONSTRAINT PKFactura PRIMARY KEY (numeroF),
  CONSTRAINT FKFactura1 FOREIGN KEY (listEmpaque) REFERENCES ListaEmpaque,
  CONSTRAINT FKFactura2 FOREIGN KEY (cliente) REFERENCES Cliente
);

---------------------------------------------------------------------------------------------------

create or replace function ingresarTotalMaterialVendido(IN XtipoBultoVendido character varying, IN XlistaEmpaque integer,IN XpesoBulto FLOAT)-------Procedimiento que se llamará desde la aplicacion Java para insertar y/o actualizar el/los material(es) ingresados en la lista de empaque
RETURNS integer AS
$BODY$
  DECLARE
  v_cant int DEFAULT 0;
  BEGIN
SELECT COUNT(1) INTO v_cant FROM TotalMaterialVendido WHERE  listEmpaque = XlistaEmpaque
and materialVendido = XtipoBultoVendido;
IF V_CANT > 0 THEN ------------si ya existe un registro con material XtipoBultoVendido actualice el pesototal 

UPDATE TotalMaterialVendido SET pesoTotalV = pesoTotalV + XpesoBulto,
		cantBultosV = cantBultosV + 1
WHERE  listEmpaque = listEmpaque
and materialVendido = XtipoBultoVendido;
RETURN 0;
     END IF;
-- SINO
INSERT INTO TotalMaterialVendido(materialVendido,listEmpaque,pesoTotalV,precioUnid,importe)
VALUES (XtipoBultoVendido,XlistaEmpaque,XpesoBulto,0.0,0.0);

     RETURN 1;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

INSERT INTO Materiales VALUES ('fbi','Fibra Blanco Impreso');
INSERT INTO Materiales VALUES ('fmp','Fibra Mezclado de Primera');
INSERT INTO Materiales VALUES ('fms','Fibra Mezclado Segunda');
INSERT INTO Materiales VALUES ('ftm','Fibra Termomecánica');
INSERT INTO Materiales VALUES ('fmepe','Fibra Mezclado Pega');
INSERT INTO Materiales VALUES ('ffm','Fibra Folder Manilla');
INSERT INTO Materiales VALUES ('fpi','Fibra Periodico Impreso');
INSERT INTO Materiales VALUES ('fc','Fibra Cartoncillo');

INSERT INTO Inventario VALUES ('fbi',0.0);
INSERT INTO Inventario VALUES ('fmp',0.0);
INSERT INTO Inventario VALUES ('fms',0.0);
INSERT INTO Inventario VALUES ('ftm',0.0);
INSERT INTO Inventario VALUES ('fmepe',0.0);
INSERT INTO Inventario VALUES ('ffm',0.0);
INSERT INTO Inventario VALUES ('fpi',0.0);
INSERT INTO Inventario VALUES ('fc',0.0);

INSERT INTO TipoMaterial VALUES ('Pfbi','fbi');
INSERT INTO TipoMaterial VALUES ('Sfbi','fbi');
INSERT INTO TipoMaterial VALUES ('Pfmp','fmp');
INSERT INTO TipoMaterial VALUES ('Sfmp','fmp');
INSERT INTO TipoMaterial VALUES ('Pfms','fms');
INSERT INTO TipoMaterial VALUES ('Sfms','fms');
INSERT INTO TipoMaterial VALUES ('Pftm','ftm');
INSERT INTO TipoMaterial VALUES ('Sftm','ftm');
INSERT INTO TipoMaterial VALUES ('Pfmepe','fmepe');
INSERT INTO TipoMaterial VALUES ('Sfmepe','fmepe');
INSERT INTO TipoMaterial VALUES ('Pffm','ffm');
INSERT INTO TipoMaterial VALUES ('Sffm','ffm');
INSERT INTO TipoMaterial VALUES ('Pfpi','fpi');
INSERT INTO TipoMaterial VALUES ('Sfpi','fpi');
INSERT INTO TipoMaterial VALUES ('Pfc','fc');
INSERT INTO TipoMaterial VALUES ('Sfc','fc');



