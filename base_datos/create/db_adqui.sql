create table adquisiciones.almacen(
cod_almacen numeric(2) primary key,
almacen varchar2(150),
estado varchar2(5) default 'V'
) tablespace adq_tablespace;

create table adquisiciones.estados (
codigo varchar2(5) primary key,
estado varchar2(50) not null
)  tablespace adq_tablespace;

create table adquisiciones.adm_enlace (
cod_enlace number(2) primary key, 
enlace varchar2(50) not null
) tablespace adq_tablespace ;

create table adquisiciones.adm_rol (
cod_rol number(2) primary key,
rol varchar2(50) not null
) tablespace adq_tablespace ;

create table adquisiciones.adm_usuario (
cod_usuario number(3) primary key, 
apodo varchar2(20) not null, 
clave varchar2(20) not null, 
usuario varchar2(150) not null, 
nombre_resumen varchar2(50) not null,
estado varchar2(5) default 'V'
) tablespace adq_tablespace ;

create table adquisiciones.adm_menu(
cod_rol numeric(2) not null references adquisiciones.adm_rol(cod_rol),
cod_enlace numeric(2) not null references adquisiciones.adm_enlace(cod_enlace)
) tablespace adq_tablespace;

create table adquisiciones.adm_usr_rol(
cod_usuario numeric(3) references adquisiciones.adm_usuario(cod_usuario),
cod_rol numeric(2) references adquisiciones.adm_rol(cod_rol),
fecha_exp date,
estado varchar2(5) default 'V' 
) tablespace adq_tablespace;

create table adquisiciones.tipo_item(
cod_tipo_item number(2) primary key,
detalle varchar2(50) not null
) tablespace adq_tablespace ;

create table adquisiciones.item(
cod_item numeric(4) primary key,
cod_tipo_item numeric(2) references adquisiciones.tipo_item(cod_tipo_item),
codigo varchar2(10),
detalle varchar2(200),
detalle_sub_rubro varchar2(200),
articulo varchar2(200),
unidad_medida varchar2(30),
estado varchar2(5) default 'V'
) tablespace adq_tablespace;

create table adquisiciones.tramites(
cod_tramite numeric(3) primary key,
cod_almacen numeric(2) references adquisiciones.almacen(cod_almacen),
nro numeric(10),
tipo_reporte varchar2(100)
) tablespace adq_tablespace;

create table adquisiciones.transaccion(
cod_transaccion numeric(7) primary key,
cod_cuantia numeric(7) references adquisiciones.cuantia(cod_cuantia),
cod_almacen number(2),
cod_w number(3),
nro_transaccion numeric(5),
gestion numeric(4),
fecha date,
de varchar2(300),
a varchar2(300),
usuario_sol varchar(200),
usuario_reg numeric(3) references adquisiciones.adm_usuario(cod_usuario),
tipo_sol numeric(2),
estado varchar2(5) DEFAULT 'V',
obs varchar2(300),
fecha_sol date,
ingreso_material varchar2(5),
hoja_ruta varchar2(20),
certif_ppto varchar2(20),
cbte varchar2(20),
obs_ppto varchar2(300),
monto_ppto number(10,2),
fondo varchar2(100),
detalle varchar2(300),
obs_adqui varchar2(300)
) tablespace adq_tablespace;

create table adquisiciones.transaccion_detalle(
cod_trans_detalle numeric(7) primary key,
cod_transaccion numeric(7) references adquisiciones.transaccion(cod_transaccion),
cod_item numeric(4) references adquisiciones.item(cod_item),
gestion numeric(4),
unidad_medida varchar2(20),
cantidad_pedido numeric(7),
precio_unit numeric(12,2),
estado varchar2(5) default 'B',
dbc varchar2(150),
contrato varchar2(150)
) tablespace adq_tablespace;

create table adquisiciones.transaccion_det_complemento(
cod_complemento numeric(7) primary key,
cod_trans_detalle numeric(7) references adquisiciones.transaccion_detalle(cod_trans_detalle),
detalle_solicitud varchar2(200),
detalle_adqui varchar2(200),
detalle_alm varchar2(200),
existe numeric(1)
) tablespace adq_tablespace;

create table adquisiciones.transaccion_nro (
cod_trans_nro numeric(7) primary key,
cod_trans_nro_ant numeric(7),
cod_transaccion numeric(7) references adquisiciones.transaccion(cod_transaccion),
cod_tramite numeric(3) references adquisiciones.tramites(cod_tramite),
cod_proveedor numeric(5) references adquisiciones.proveedores(cod_proveedor),
nro numeric(7),
fecha date,
factura VARCHAR2(100),
fecha_fact DATE,
memo VARCHAR2(20),
obs varchar2(300),
resolucion_adm varchar2(50),
inf_comision varchar2(50),
nro_propuesta varchar2(50),
cuce varchar2(50)
) tablespace adq_tablespace;


create table adquisiciones.transaccion_detalle_nro(
cod_trans_detalle numeric(7) references adquisiciones.transaccion_detalle(cod_trans_detalle),
cod_trans_nro numeric(7) references adquisiciones.transaccion_nro(cod_trans_nro)
) tablespace adq_tablespace;

create table adquisiciones.transaccion_envio_fecha (
cod_trans_detalle numeric(7) references adquisiciones.transaccion_detalle(cod_trans_detalle),
estado varchar2(5),
fecha_envio date
) tablespace adq_tablespace;

create table adquisiciones.cuantia(
cod_cuantia numeric(7) primary key,
cuantia varchar2(30),
del numeric(12,2),
hasta numeric(12,2)
) tablespace adq_tablespace;

create table adquisiciones.documentos(
cod_docs numeric(7) primary key,
cod_transaccion numeric(7) references adquisiciones.transaccion(cod_transaccion),
terminos_ref varchar2(150)
) tablespace adq_tablespace;



--es mejor sacar la apertura programatica de las tablas de presupuestos del
--sistema zodiaco-leo-web pero con un evento de cierre y apertura de gestion
create table adquisiciones.programatic_apert(
cod_apert varchar2(20) primary key,
cod_faculty varchar2(10),
cod_almacen numeric(2) references adquisiciones.almacen(cod_almacen),
detalle varchar2(200),
apertura varchar2(20),
estado varchar2(5)
) tablespace adq_tablespace;

create table adquisiciones.adm_usr_apert(
cod_usuario numeric(3) references adquisiciones.adm_usuario(cod_usuario),
cod_apert varchar2(20)  
) tablespace adq_tablespace;

create table adquisiciones.adm_workflow (
cod_w numeric(3) primary key,
detalle varchar2(50)
) tablespace adq_tablespace;

create table adquisiciones.adm_workflow_usr (
cod_usuario numeric(3) references adquisiciones.adm_usuario(cod_usuario),
cod_w numeric(3) references adquisiciones.adm_workflow(cod_w),
codigo varchar2(5)
) tablespace adq_tablespace;

create table adquisiciones.adm_workflow_estados (
cod_w numeric(3) references adquisiciones.adm_workflow(cod_w),
origen varchar2(5),
destino varchar2(5)
) tablespace adq_tablespace;

create table adquisiciones.proveedores(
cod_proveedor numeric(5) primary key,
partida numeric(5),
casa_comercial varchar2(150),
servicio varchar2(300),
direccion varchar2(300),
telefono varchar2(20),
fax varchar2(20),
web varchar2(150),
nit varchar2(20),
gran_actividad_nit varchar2(150),
razon_social_nit varchar2(150),
obs varchar2(300),
estado varchar2(5) default 'HA'
) tablespace adq_tablespace;

create table adquisiciones.proveedores_sansion(
cod_prov_sansion numeric(5) primary key,
cod_proveedor numeric(5) references proveedores(cod_proveedor),
fec_ini date,
fec_fin date,
obs varchar2(300)
) tablespace adq_tablespace;

-- secuencias de tablas

create sequence adquisiciones.sec_cod_almacen increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_complemento increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_cuantia increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_docs increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_enlace increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_item increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_menu increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_prov_sansion increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_proveedor increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;
    
create sequence adquisiciones.sec_cod_rol increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_tipo_item increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_tramite increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_trans_detalle increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_trans_nro increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_transaccion increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_usuario increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_w increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;

create sequence adquisiciones.sec_cod_proveedor increment by 1 start 
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;



--  ==========================================================
--        GUARDA LOS DATOS CON LOS ARRANCARA EL SISTEMA
-- ============================================================

INSERT INTO ADQUISICIONES.ALMACEN(COD_ALMACEN,ALMACEN)
VALUES(ADQUISICIONES.SEC_COD_ALMACEN.NEXTVAL,'Almacen Central');

INSERT INTO ADQUISICIONES.ADM_USUARIO(COD_USUARIO,APODO,CLAVE,USUARIO,NOMBRE_RESUMEN,ESTADO)
VALUES(ADQUISICIONES.SEC_COD_USUARIO.NEXTVAL,'admin','admin','ADMINISTRADOR','ADMINISTRADOR','V');

INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'ADMINISTRADOR');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'APROBADOR ALMACEN');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'UNIDAD EJECUTORA');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'PRESUPUESTO');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'ADQUISICIONES');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'APROB ING ALMACEN');
INSERT INTO ADQUISICIONES.ADM_ROL
VALUES(ADQUISICIONES.SEC_COD_ROL.NEXTVAL,'ASESORIA JURIDICA');

INSERT INTO ADQUISICIONES.ADM_USR_ROL
VALUES(1,1,sysdate,'V');

INSERT INTO ADQUISICIONES.TIPO_ITEM(COD_TIPO_ITEM,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_TIPO_ITEM.NEXTVAL,'Materiales e Insumos');
INSERT INTO ADQUISICIONES.TIPO_ITEM(COD_TIPO_ITEM,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_TIPO_ITEM.NEXTVAL,'Activos');
INSERT INTO ADQUISICIONES.TIPO_ITEM(COD_TIPO_ITEM,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_TIPO_ITEM.NEXTVAL,'Servicios');
INSERT INTO ADQUISICIONES.TIPO_ITEM(COD_TIPO_ITEM,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_TIPO_ITEM.NEXTVAL,'Consultorias');
INSERT INTO ADQUISICIONES.TIPO_ITEM(COD_TIPO_ITEM,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_TIPO_ITEM.NEXTVAL,'Obras');


INSERT INTO ADQUISICIONES.TRAMITES(COD_TRAMITE,COD_ALMACEN,NRO,TIPO_REPORTE)
VALUES(ADQUISICIONES.SEC_COD_TRAMITE.NEXTVAL,1,1,'SOLICITUD DE COMPRAS');
INSERT INTO ADQUISICIONES.TRAMITES(COD_TRAMITE,COD_ALMACEN,NRO,TIPO_REPORTE)
VALUES(ADQUISICIONES.SEC_COD_TRAMITE.NEXTVAL,1,1,'ORDEN DE COMPRA Y/U ORDEN DE SERVICIO');
INSERT INTO ADQUISICIONES.TRAMITES(COD_TRAMITE,COD_ALMACEN,NRO,TIPO_REPORTE)
VALUES(ADQUISICIONES.SEC_COD_TRAMITE.NEXTVAL,1,1,'INGRESO DE MATERIAL');
INSERT INTO ADQUISICIONES.TRAMITES(COD_TRAMITE,COD_ALMACEN,NRO,TIPO_REPORTE)
VALUES(ADQUISICIONES.SEC_COD_TRAMITE.NEXTVAL,1,1,'PEDIDO DE MATERIALES');

INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('V','VALIDO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('B','BORRADOR');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('ALM','ALMACEN');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('PPTO','PRESUPUESTO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('ADQ','ADQUISICIONES');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('ALM1','INGRESO ALMACEN');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('C','CONCLUIDO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('A','ANULADO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('X','ELIMINADO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('D','DESIERTO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('HA','HABILITADO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('IN','INHABILITADO');
INSERT INTO ADQUISICIONES.ESTADOS(CODIGO,ESTADO)
VALUES('JUR','JURIDICAS');


INSERT INTO ADQUISICIONES.ADM_WORKFLOW(COD_W,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_W.NEXTVAL,'SOLICITUD DE MATERIALES Y/O ACTIVOS');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW(COD_W,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_W.NEXTVAL,'PEDIDO DE MATERIALES Y/O ACTIVOS');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW(COD_W,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_W.NEXTVAL,'CONSULTORIAS');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW(COD_W,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_W.NEXTVAL,'OBRAS');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW(COD_W,DETALLE)
VALUES(ADQUISICIONES.SEC_COD_W.NEXTVAL,'COMPRAS MAYORES');

INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(1,'B','ALM');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(1,'ALM','PPTO');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(1,'PPTO','ADQ');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(1,'ADQ','ALM1');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(1,'ALM1','C');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(2,'B','ALM');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(2,'ALM','C');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(3,'B','PPTO');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(3,'PPTO','ADQ');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(3,'ADQ','JUR');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(3,'JUR','C');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(4,'B','PPTO');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(4,'PPTO','ADQ');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(4,'ADQ','JUR');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(4,'JUR','C');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'B','ALM');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'ALM','PPTO');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'PPTO','ADQ');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'ADQ','JUR');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'JUR','ALM1');
INSERT INTO ADQUISICIONES.ADM_WORKFLOW_ESTADOS(COD_W,ORIGEN,DESTINO)
VALUES(5,'ALM1','C');

INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'ALMACEN');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'USUARIOS');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'UNIDAD EJECUTORA');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'ITEMS');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'CAMBIO ROL');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'CAMBIO PASSWORD');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'DESPACHO');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'PROVEEDORES');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'ORDEN COMPRA');
INSERT INTO ADQUISICIONES.ADM_ENLACE(COD_ENLACE,ENLACE)
VALUES(ADQUISICIONES.SEC_COD_ENLACE.NEXTVAL,'BANDEJA');

INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(1,1);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(2,1);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(3,1);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(4,1);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(7,2);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(8,5);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(9,5);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(10,2);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(10,4);
INSERT INTO ADQUISICIONES.ADM_MENU(COD_ENLACE,COD_ROL)
VALUES(10,7);

INSERT INTO ADQUISICIONES.CUANTIA(COD_CUANTIA,CUANTIA,DEL,HASTA)
VALUES(ADQUISICIONES.SEC_COD_CUANTIA.NEXTVAL,'COMPRA MENOR',1,20000);
INSERT INTO ADQUISICIONES.CUANTIA(COD_CUANTIA,CUANTIA,DEL,HASTA)
VALUES(ADQUISICIONES.SEC_COD_CUANTIA.NEXTVAL,'ANPE',20001,200000);
INSERT INTO ADQUISICIONES.CUANTIA(COD_CUANTIA,CUANTIA,DEL,HASTA)
VALUES(ADQUISICIONES.SEC_COD_CUANTIA.NEXTVAL,'ANPE II',200001,1000000);
INSERT INTO ADQUISICIONES.CUANTIA(COD_CUANTIA,CUANTIA,DEL,HASTA)
VALUES(ADQUISICIONES.SEC_COD_CUANTIA.NEXTVAL,'LICITACIONES',1000000,40000000);

/
