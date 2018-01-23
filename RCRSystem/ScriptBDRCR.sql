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

INSERT INTO Proveedor VALUES ('105720400','ABEL BARQUERO BRENES',84320998,84320998,'LA GUACIMA, RINCON HERRERA COSTADO SUROESTE PLAZA DE DEPORTES','ABEL BARQUERO');
INSERT INTO Proveedor VALUES ('3101563907','ADIME DE ABANGARES SA',26620689,88984197,'GUANACASTE, LAS JUNTAS DE ABANGARES','HILDA');
INSERT INTO Proveedor VALUES ('11170346','ALBERTO ESTEBAN GARCIA BOZA',83564063,83564063,'SAN JOSE, MORAVIA','ALBERTO ESTEBAN GARCIA BOZA');
INSERT INTO Proveedor VALUES ('302130526','ALFONSO CASTRO MENDEZ',60844561,84831349,'ALAJUELA, SAN RAFAEL','ALFONSO CASTRO MENDEZ');
INSERT INTO Proveedor VALUES ('301390850','ANTONIO A. ALFARO ALFARO',25202368,25202368,'SAN JOSE, LA URUCA','ANTONIO A. ALFARO ALFARO');
INSERT INTO Proveedor VALUES ('3002349328','ASOCIACION MUJERES AMBIENTALISTAS 4R ',24757447,24757447,'FLORENCIA DE SAN CARLOS.','MARGARITA CASTRO');
INSERT INTO Proveedor VALUES ('3101666720','AUTOCENTRO RECICLAJE PLANETA LIMPIO S.A.',22899601,88212579,'EZCASU, DE LA IGLESIA CATOLICA 900 METROS SUR 150 METROS OESTE','GUSTAVO CORDOBA');
INSERT INTO Proveedor VALUES ('204570122','CARLOS LUIS VILLEGA ROJAS',84921543,84921543,'SAN CARLOS','CARLOS LUIS VILLEGA ROJAS');
INSERT INTO Proveedor VALUES ('110710941','CARMEN GUILLEN QUIROS',88966157,84019793,'SAN DIEGO DE TRES RIOS, CONTIGUO AL PUENTE','JAIRO CAMPOS');
INSERT INTO Proveedor VALUES ('3002696843','CENTRO DE ACOPIO AEB ',89610237,89610237,'ASUNCION DE BELEN','SUSAN ASTORGA');
INSERT INTO Proveedor VALUES ('3002601212','CENTRO DE ACOPIO PROMESAS DE DIOS',88258296,88258296,'GUANACASTE, CAÑAS','-');
INSERT INTO Proveedor VALUES ('3101663213','CENTRO DE ACOPIO REMAR, S.A',83251951,83251951,'HEREDIA, SANTA ROSA SANTO DOMINGO 800 METROS OESTE DE LAS BODEGAS DE MUNDO MAGICO','MARCELA ROJAS');
INSERT INTO Proveedor VALUES ('3101707220','COMPAÑÍA DE CARO S.A.',60021136,63530313,'SAN JOSE, BARRIO LUJAN','JUAN ROJAS');
INSERT INTO Proveedor VALUES ('34948-78-123','DISO, SOCIEDAD ANONIMA',22211372,22211372,'GUATEMALA.','ESTEBAN SICAY');
INSERT INTO Proveedor VALUES ('109580084','EDUARDO CORDERO MORA',22703035,87624722,'SAN JOSE, DESAMPARADOS','EDUARDO CORDERO MORA');
INSERT INTO Proveedor VALUES ('454722-5','EDWIN RENE GARCIA LOPEZ (COMERCIAL DIVERSA ',24774280,24774280,'GUATEMALA.','EDWIN RENE GARCIA');
INSERT INTO Proveedor VALUES ('204800565','EVELYN MURILLO PARRALES',89777203,72771543,'CARTAGO, LA LIMA FINCA EL ENCIERRO','EVELYN MURILLO PARRALES');
INSERT INTO Proveedor VALUES ('3101486134','FECA S.A,',22236072,88723775,'SAN JOSE, BARRIO CUBA','CARLOS SOLIS');
INSERT INTO Proveedor VALUES ('3101031523','FOTOLIT S.A.',22771200,22771200,'500 METROS OESTE DE JARDINES DEL RECUERDO LAGUNILLA DE HEREDIA','ALVARO FALLAS');
INSERT INTO Proveedor VALUES ('3006087315','FUNDACION TECNOLOGICA DE COSTA RICA ',25737851,88422589,'CARTAGO','JUAN CARLOS SALAS');
INSERT INTO Proveedor VALUES ('001-2110860077u','GEESELL NOELIA RUIZ GRIJALVA',55017174,22802649,'NICARAGUA. MASAYA','DIEDERICH ACUÑA');
INSERT INTO Proveedor VALUES ('106200949','GERARDO VARGAS MONTOYA',40345296,89934533,'POCOCI, DE LA BOMBA LA RITA 800 METROS ESTE DEL CICLO RODRIGUEZ 250 METROS SUR','GERARDO VARGAS MONTOYA');
INSERT INTO Proveedor VALUES ('243572-1','HERIBERTO GONZALEZ HERNANDEZ (SEPACA)',22582006,22582009,'GUATEMALA.','HERIBERTO GONZALEZ');
INSERT INTO Proveedor VALUES ('3101133082','INDUSTRIAS GAREND, S.A.',24584343,24584343,'CARRILLOS BAJO DE POAS','DANIELA ANDRADE');
INSERT INTO Proveedor VALUES ('302510486','JORGE LUIS CORDERO MONTERO ',22764113,70523227,'CARTAGO, TABLON DE EL GUARCO 800 METROS SUR DE LA IGLESIA CATOLICA','JORGE LUIS CORDERO MONTERO');
INSERT INTO Proveedor VALUES ('401740751','JORLENY CHAVARRIA CAMBRONERO (CENTRO DE ACOPIO RECIMA)',87992774,88924264,'ALAJUELA, PITAL DE SAN CARLOS','JORLENY CHAVARRIA');
INSERT INTO Proveedor VALUES ('103420145','JUAN FELIX ROJAS VARGAS',40315513,60021136,'SAN JOSE','JUAN FELIX ROJAS VARGAS ');
INSERT INTO Proveedor VALUES ('304420644','JUNIOR CORDERO GONZALEZ',83703915,87926860,'CARTAGO, QUEBRADILLA','JUNIOR CORDERO GONZALEZ');
INSERT INTO Proveedor VALUES ('302040899','MANUEL RIVAS MOLINA',86916367,86916367,'TURRIALBA','MANUEL RIVAS MOLINA');
INSERT INTO Proveedor VALUES ('302140444','MARIO QUIROS PEREIRA (CENTRO DE ACOPIO EL VALLE)',25573939,88303052,'TURRIALBA','MARIO QUIROS');
INSERT INTO Proveedor VALUES ('108700870','MINOR MONGE MORA',25573939,88303052,'TURRIALBA','MINOR MONGE');
INSERT INTO Proveedor VALUES ('3101555628','MUJERES SILIOÉ S.A.',24391773,83434397,'ALAJUELA, SAN RAFAEL','SANDRA MENDEZ');
INSERT INTO Proveedor VALUES ('3106555628','MUJERES SILOE SOCIEDAD CIVIL',24391656,83434397,'ALAJUELA, SAN RAFAEL','SANDRA MENDEZ');
INSERT INTO Proveedor VALUES ('3002597915','MUNICIPALIDA DE SAN RAFAEL HEREDIA CENTRO DE ACOPIO ',22627978,22627978,'HEREDIA, DE LA CLINICA DEL SEGURO SOCIAL DE SAN RAFAEL DE HEREDIA 120 METROS OESTE Y 50 METROS NORTE','-');
INSERT INTO Proveedor VALUES ('1-5580-4193832','OMAR SALGADO HERRERA',88597763,60155810,'SAN JOSE, BARRIO MEXICO','OMAR SALGADO HERRERA');
INSERT INTO Proveedor VALUES ('3101226529','PLATAFORMA INTERNACIONAL REAL, S.A.',25602171,88283595,'SAN JOSE COSTA RICA.','ERIC JIMENEZ');
INSERT INTO Proveedor VALUES ('80094068534','RAUL WILMER CHOW JIMENEZ',87846497,71663330,'ALAJUELA, SAN RAFAEL','RAUL WILMER CHOW JIMENEZ');
INSERT INTO Proveedor VALUES ('3101528447','RECICLADORA LA CALMA S.A',83930217,87304160,'SAN JOSE, DE LA ANTIGUA TELETICA 100 METROS AL SUR 75 METROS ESTE','GEOVANNY CALVO');
INSERT INTO Proveedor VALUES ('3101641420','RECICLAJE INVERCIONES LUNA LLENA YK, S.A ',24408643,87054465,'TIBAS, DE METALCO 10 METROS SUR Y 100 OESTE FRENTE A REMAQ','VICTOR CALVO');
INSERT INTO Proveedor VALUES ('3101655995','RECICLAJE VALENCIANO COSTA RICA S.A.',24397786,24387816,'ALAJUELA, SAN RAFAEL','DIEGO VALENCIANO');
INSERT INTO Proveedor VALUES ('3101196532','RECICLANDO CON DON PACO, S.A.',61416940,60075640,'ALAJUELA, CALLE 10 BIS. AVENIDA 9','EDITH');
INSERT INTO Proveedor VALUES ('3101534225','RECICLE PLANET S.A',27381629,27381629,'PEREZ ZELEDON, FRENTE AL CEMENTERIO DE PEÑAS BLANCAS','HELLEN VILLALTA');
INSERT INTO Proveedor VALUES ('3101284129','RECICLE POWER S.A.',22524016,88498908,'SAN JOSE','JORGE CAMPOS');
INSERT INTO Proveedor VALUES ('719801374151','RECIMETAL S.A.',2319274,2375381,'PANAMA','ROSARIO ARANA');
INSERT INTO Proveedor VALUES ('343242342','RECIMETAL DOMINICANA ',0,0,'REPUBLICA DOMINICANA','ROSARIO ARANA');
INSERT INTO Proveedor VALUES ('3101153501','RECOLECTORA Y EMPACADORA CAPRI, S.A.',22256227,22256227,'SAN JOSE, SAN PEDRO DE MONTES DE OCA ','NORMA CAMPOS');
INSERT INTO Proveedor VALUES ('302150430','RIGOBERTO BRENES COTO',25913243,83533032,'CARTAGO, 100 METROS ESTE GUARDIA RURAL, AGUA CALIENTE','RIGOBERTO BRENES COTO');
INSERT INTO Proveedor VALUES ('303140759','SERGIO IVAN ARIAS RAMOS (RECICLAJE LA AURORA',22395994,88029077,'HEREDIA, 150 METROS SUR DEL EBAIS DE LA AURORA','SERGIO IVAN ARIAS');
INSERT INTO Proveedor VALUES ('3101644090','SERVICIOS ECOLOGICOS DIVISION POLIMEROS S.A.',25720890,0,'CARTAGO, TEJAR DEL GUARDO 200 METROS SUR DEL PARQUE INDUSTRIAL','RICARDO');
INSERT INTO Proveedor VALUES ('104340004','SOFIA MARGARITA BARQUERO BRENES ',22925823,22451951,'SANTO DOMINGO, PARASITO','SOFIA MARGARITA BARQUERO BRENES');
INSERT INTO Proveedor VALUES ('3101594968','SOLUCIONES EMPRESARIALES MORA S.A.',89200495,87369000,'BARRIO CUBA, ESQUINA SUR CEMENTERIO OBRERO 100 SUR','CARLOS SOLIS');
INSERT INTO Proveedor VALUES ('155808326404','THELMA TALAVERA TALAVERA',84116877,84116877,'ALAJUELA CIRUELAS','THELMA TALAVERA');
INSERT INTO Proveedor VALUES ('3101697429','TICOSHRED SA ',40824397,83535138,'SAN JOSE, SAN RAFAEL DESAMPARADOS','CARLOS SANCHEZ');
INSERT INTO Proveedor VALUES ('800730783','VICENTE PAUL MONTENEGRO LOPEZ',22411481,88235736,'SAN JOSE.','VICENTE PAUL MONTENEGRO LOPEZ');
INSERT INTO Proveedor VALUES ('106870341','VICTOR CALVO SANDI ',24408643,87054465,'ESCAZU, 100 METROS SUR Y 175 OESTE DEL BNCR','YANCY CALVO');
INSERT INTO Proveedor VALUES ('3101534315','WEST COAST WASTE INDUSTRIES SOCIEDAD ANONIMA',85624810,85624810,'SAN JOSE','EDWIN BARBOZA');
INSERT INTO Proveedor VALUES ('302240235','OSCAR RODOLFO HERNANDEZ AGUILAR ',25527779,89229288,'CARTAGO, LA LIMA 100 ESTE Y 50 NORTE PLAZA DE LA LIMA','OSCAR RODOLFO HERNANDEZ AGUILAR');
INSERT INTO Proveedor VALUES ('3102626082','Todos Unidos Ayudando al Medio Ambiente S.R.L',88386806,88386806,'ALAJUELA, SAN RAMON','-');
INSERT INTO Proveedor VALUES ('3002045930','ASOCIACIÓN TALITA CUMI',24512012,24514343,'ALAJUELA, CRUCE CIRRÍ, NARANJO','-');
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

INSERT INTO Usuarios VALUES ('12345','Daniel N.',23234323,1,'123d');
INSERT INTO Usuarios VALUES ('6789','Geovanny',92372,2,'456g');
INSERT INTO Usuarios VALUES ('777','Juan',20342032,3,'777f');

 
 ------------------------------------------------------------------------------------------------------

create TABLE Cliente
( codigoC VARCHAR,
  nombreC VARCHAR,
  telefonoC VARCHAR,
  direccionC VARCHAR,
  contactoC VARCHAR,
  CONSTRAINT PKCliente  PRIMARY KEY (codigoC)
);

INSERT INTO Cliente VALUES ('05110807031013','Alas Doradas, S.A de C.V.','+503-2304-2200','KM 27 1/2 CARRETERA A SANTA ANA SAN JUAN OPICO LA LIBERTAD, EL SALVADOR','hnavas@alas-doradas.com');
INSERT INTO Cliente VALUES ('04-3651435','Canusa Hershman','+1(203)-488-0887','45 NORTHEAST INDUSTRIAL ROAD BRANFORD , CT 06405','AlejandraP@CanusaCorp.com');
INSERT INTO Cliente VALUES ('68-0012735','CELLMARK, INC','+1(415)945-1209','22 PELICAN WAY SAN RAFAEL, CA 94901 USA','kevin.baker@cellmark.com');
INSERT INTO Cliente VALUES ('68-0012662','CELLMARK ASIA PTE. LTD.','','','');
INSERT INTO Cliente VALUES ('061402209991037','Conave, S.A DE C.V.','+503-2261-9700','ALAMEDA JUAN PABLO II, BODEGAS SAN JORGE, SAN SALVADOR','vlopezcraik@interpak.com.sv');
INSERT INTO Cliente VALUES ('8536805','DISO S.A','+502-2221-1372','21 CALLE 1-33 ZONA 1, GUATEMALA','diso_sa@yahoo.com');
INSERT INTO Cliente VALUES ('06140609081021','Global Business, S.A. DE C.V','+503-7797-5579','CARRETERA TRONCAL DEL NORTE KM 9 1/2 CIUDAD DELGADO SAN SALVADOR','hispalia77@hotmail.com');
INSERT INTO Cliente VALUES ('06140707141040','Green Company El Salvador, S.A. DE C.V','+503-2510-5100','CARRETERA TRONCAL DEL NORTE KM 3 1/2 BODEGA 101 SAN SALVADOR','greencompany.sv@gmail.com');
INSERT INTO Cliente VALUES ('05110306061010','Inversiones Luz de Maria','+503-2334-6409','KM 47 1/2 CARRETERA A LA HERRADURA, LA PAZ, EL SALVADOR','ildemarsa@gmail.com');
INSERT INTO Cliente VALUES ('14-1706630','Jordan Trading INC.','+1(845)-338-5379','31 ALBANY AVENUE KINGSTON, NY 12402 USA','djordan@jordantrading.com');
INSERT INTO Cliente VALUES ('05110307630018','Kimberly Clark de Centroamerica S.A.','+503-2319-4500','KM 32 1/2 CARRETERA A SAN JUAN OPICO, SITIO DEL NINO LA LIBERTAD, EL SALVADOR','Edward.R.Garcia@kcc.com');
INSERT INTO Cliente VALUES ('KCM810226DEA','KIMBERLY CLARK DE MEXICO S.A.B DE C.V.','+52-1(55)-5282-7300','JAIME BALMES #8 PISO 9 INT 901-904 COL LOS MORALES POLANCO CIUDAD DE MEXICO','Alejandro.Vela@kcc.com');
INSERT INTO Cliente VALUES ('45-2826705','LMV Recycling Solutions, LLC','+1(949)-675-3005','1400 QUAIL STREET, SUITE 280 NEWPORT BEACH, CA 92660','blake.blomberg@lmvrecycling.com');
INSERT INTO Cliente VALUES ('05019010342038','Moldeados Centroamericanos S.A.','+503-9451-8527','1,3 KM DESPUES DEL DESVIO DE LA BARCA POTRERILLOS, CORTES, HONDURAS','fernando.amador@molpack.net');
INSERT INTO Cliente VALUES ('59-1472128','Omnisphere Corporation','+1(305)-388-4075','9950 S.W. 107TH AVE, SUITE 100 MIAMI, FLORIDA 33176','avaldes@omnisphere.net');
INSERT INTO Cliente VALUES ('06140707041020','Plycem Construsistemas del Salvador','+503-2251-9313','CARRETERA PANAMERICANA KM 12,5 FRENTE A DESVIO DE APULO','jdramirez@elementia.com');
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



