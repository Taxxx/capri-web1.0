CREATE OR REPLACE TYPE ADQUISICIONES.TEST_OBJ_TYPE IS OBJECT
(
nro_gestion varchar2(20),
fecha_creacion date,
fecha_envio date ,
unidad_sol varchar2(300),
unidad_des varchar2(300),
usuario_sol varchar2(200),
unidad_medida varchar2(20),
cantidad_pedido number,
tipo_item varchar2(50),
articulo varchar2(200),
detalle_solicitud varchar2(200),
hoja_ruta varchar2(20),
cbte varchar2(20),
casa_comercial varchar2(150),
direccion varchar2(300),
telefono varchar2(20),
nit varchar2(20),
precio_unit number(12,2),
nro_orden_compra number(12),
fec_orden_compra date,
factura varchar2(20),
fecha_fact date,
memo varchar2(20),
nro_transaccion number(5)
)
/

CREATE OR REPLACE TYPE adquisiciones.TEST_TABTYPE AS TABLE OF TEST_OBJ_TYPE
/

CREATE OR REPLACE FUNCTION ADQUISICIONES.transaccion_reportes(c_estado in varchar2,c_cod_transaccion in number,c_cod_rol in number,c_cod_tramite in number)
RETURN TEST_TABTYPE
AS
    V_Test_Tabtype Test_TabType;
BEGIN 
 if (c_cod_rol=5) then  
        SELECT adquisiciones.TEST_OBJ_TYPE(a.nro_gestion,a.fecha_creacion,a.fecha_envio,a.unidad_sol ,a.unidad_des ,a.usuario_sol ,a.unidad_medida ,a.cantidad_pedido ,a.tipo_item ,a.articulo ,a.detalle_solicitud,a.hoja_ruta,a.cbte,a.casa_comercial,a.direccion,a.telefono,a.nit,a.precio_unit,a.nro_orden_compra,a.fec_orden_compra,a.factura,a.fecha_fact,a.memo,a.nro_transaccion )
        BULK COLLECT INTO V_Test_TabType
        FROM
            (select distinct (t2.nro||'-'||t1.gestion) as nro_gestion,t1.fecha as fecha_creacion,t2.fecha_envio, t1.de as unidad_sol,t1.a as unidad_des,t1.usuario_sol,t3.unidad_medida,t3.cantidad_pedido,t3.tipo_item,t3.articulo,(CASE WHEN t4.detalle_adqui is not null THEN T4.DETALLE_ADQUI ELSE t4.DETALLE_SOLICITUD  END ) as detalle_solicitud,t4.cod_complemento,t1.hoja_ruta,t1.cbte,t1.casa_comercial,t1.direccion,t1.telefono,t1.nit,t3.precio_unit,t5.nro as nro_orden_compra,t6.fecha_envio as fec_orden_compra,t1.factura,t1.fecha_fact,t1.memo,t1.nro_transaccion
                from adquisiciones.transaccion t1
                inner join (select t1.cod_transaccion,t1.nro,t2.estado,t2.fecha_envio from adquisiciones.transaccion_nro t1 inner join adquisiciones.transaccion_fecha_envio t2 on t1.cod_transaccion=t2.cod_transaccion
                        where t2.estado=c_estado and t1.cod_tramite=c_cod_tramite) t2 on t1.cod_transaccion=t2.cod_transaccion
                inner join (select t1.cod_transaccion,t1.cod_trans_detalle,t1.unidad_medida,t1.cantidad_pedido,t3.articulo,t4.detalle as tipo_item,t1.precio_unit  
                        from adquisiciones.transaccion_detalle t1                         
                        inner join adquisiciones.item t3 on t1.cod_item=t3.cod_item
                        inner join adquisiciones.tipo_item t4 on t3.cod_tipo_item=t4.cod_tipo_item) t3 on t3.cod_transaccion=t1.cod_transaccion
                left join adquisiciones.transaccion_det_complemento t4 on t3.cod_trans_detalle=t4.cod_trans_detalle
                left  join adquisiciones.transaccion_nro t5 on (t5.cod_transaccion=t1.cod_transaccion and t5.cod_tramite=2)
                left  join adquisiciones.transaccion_fecha_envio t6 on (t6.cod_transaccion=t1.cod_transaccion and t6.estado='ALM1')
                where t1.cod_transaccion=c_cod_transaccion order by t3.tipo_item,t3.articulo,t4.cod_complemento
            ) A;              
elsif ( c_cod_rol=2 or (c_cod_rol=3 and  c_estado='C')) then  
        SELECT adquisiciones.TEST_OBJ_TYPE(a.nro_gestion,a.fecha_creacion,a.fecha_envio,a.unidad_sol ,a.unidad_des ,a.usuario_sol ,a.unidad_medida ,a.cantidad_pedido ,a.tipo_item ,a.articulo ,a.detalle_solicitud,a.hoja_ruta,a.cbte,a.casa_comercial,a.direccion,a.telefono,a.nit,a.precio_unit,a.nro_orden_compra,a.fec_orden_compra,a.factura,a.fecha_fact,a.memo,a.nro_transaccion )
        BULK COLLECT INTO V_Test_TabType
        FROM
            (select distinct (t2.nro||'-'||t1.gestion) as nro_gestion,t1.fecha as fecha_creacion,t2.fecha_envio, t1.de as unidad_sol,t1.a as unidad_des,t1.usuario_sol,t3.unidad_medida,t3.cantidad_pedido,t3.tipo_item,t3.articulo, (CASE WHEN t4.detalle_alm is not null THEN T4.DETALLE_ALM ELSE (CASE WHEN t4.detalle_adqui is not null then t4.detalle_adqui else t4.detalle_solicitud END)   END )  as detalle_solicitud,t4.cod_complemento,t1.hoja_ruta,t1.cbte,t1.casa_comercial,t1.direccion,t1.telefono,t1.nit,t3.precio_unit,t5.nro as nro_orden_compra,t6.fecha_envio as fec_orden_compra,t1.factura,t1.fecha_fact,t1.memo,t1.nro_transaccion
                from adquisiciones.transaccion t1
                inner join (select t1.cod_transaccion,t1.nro,t2.estado,t2.fecha_envio from adquisiciones.transaccion_nro t1 inner join adquisiciones.transaccion_fecha_envio t2 on t1.cod_transaccion=t2.cod_transaccion
                        where t2.estado=c_estado and t1.cod_tramite=c_cod_tramite) t2 on t1.cod_transaccion=t2.cod_transaccion
                inner join (select t1.cod_transaccion,t1.cod_trans_detalle,t1.unidad_medida,t1.cantidad_pedido,t3.articulo,t4.detalle as tipo_item,t1.precio_unit  
                        from adquisiciones.transaccion_detalle t1                        
                        inner join adquisiciones.item t3 on t1.cod_item=t3.cod_item
                        inner join adquisiciones.tipo_item t4 on t3.cod_tipo_item=t4.cod_tipo_item) t3 on t3.cod_transaccion=t1.cod_transaccion
                left join adquisiciones.transaccion_det_complemento t4 on t3.cod_trans_detalle=t4.cod_trans_detalle
                left  join adquisiciones.transaccion_nro t5 on (t5.cod_transaccion=t1.cod_transaccion and t5.cod_tramite=2)
                left  join adquisiciones.transaccion_fecha_envio t6 on (t6.cod_transaccion=t1.cod_transaccion and t6.estado='ALM1')
                where t1.cod_transaccion=c_cod_transaccion order by t3.tipo_item,t3.articulo,t4.cod_complemento
            ) A;  
else
        SELECT adquisiciones.TEST_OBJ_TYPE(a.nro_gestion,a.fecha_creacion,a.fecha_envio,a.unidad_sol ,a.unidad_des ,a.usuario_sol ,a.unidad_medida ,a.cantidad_pedido ,a.tipo_item ,a.articulo ,a.detalle_solicitud,a.hoja_ruta,a.cbte,a.casa_comercial,a.direccion,a.telefono,a.nit,a.precio_unit,a.nro_orden_compra,a.fec_orden_compra,a.factura,a.fecha_fact,a.memo,a.nro_transaccion )
        BULK COLLECT INTO V_Test_TabType
        FROM
            (select distinct (t2.nro||'-'||t1.gestion) as nro_gestion,t1.fecha as fecha_creacion,t2.fecha_envio, t1.de as unidad_sol,t1.a as unidad_des,t1.usuario_sol,t3.unidad_medida,t3.cantidad_pedido,t3.tipo_item,t3.articulo,t4.detalle_solicitud,t4.cod_complemento,t1.hoja_ruta,t1.cbte,t1.casa_comercial,t1.direccion,t1.telefono,t1.nit,t3.precio_unit,t5.nro as nro_orden_compra,t6.fecha_envio as fec_orden_compra,t1.factura,t1.fecha_fact,t1.memo,t1.nro_transaccion
                from adquisiciones.transaccion t1
                inner join (select t1.cod_transaccion,t1.nro,t2.estado,t2.fecha_envio from adquisiciones.transaccion_nro t1 inner join adquisiciones.transaccion_fecha_envio t2 on t1.cod_transaccion=t2.cod_transaccion
                        where t2.estado=c_estado and t1.cod_tramite=c_cod_tramite ) t2 on t1.cod_transaccion=t2.cod_transaccion
                inner join (select t1.cod_transaccion,t1.cod_trans_detalle,t1.unidad_medida,t1.cantidad_pedido,t3.articulo,t4.detalle as tipo_item,t1.precio_unit 
                        from adquisiciones.transaccion_detalle t1                         
                        inner join adquisiciones.item t3 on t1.cod_item=t3.cod_item
                        inner join adquisiciones.tipo_item t4 on t3.cod_tipo_item=t4.cod_tipo_item) t3 on t3.cod_transaccion=t1.cod_transaccion
                left join adquisiciones.transaccion_det_complemento t4 on t3.cod_trans_detalle=t4.cod_trans_detalle
                left  join adquisiciones.transaccion_nro t5 on (t5.cod_transaccion=t1.cod_transaccion and t5.cod_tramite=2)
                left  join adquisiciones.transaccion_fecha_envio t6 on (t6.cod_transaccion=t1.cod_transaccion and t6.estado='ALM1')
                where t1.cod_transaccion=c_cod_transaccion order by t3.tipo_item,t3.articulo,t4.cod_complemento
            ) A;  
end if;     
    RETURN V_Test_TabType;

    EXCEPTION
    WHEN OTHERS THEN
        v_Test_TabType.DELETE;
    RETURN v_Test_TabType;
END;
/