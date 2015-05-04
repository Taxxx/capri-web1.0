create or replace view view_transaccion as 
select (t2.nro||'-'||t1.gestion) as nro_gestion,to_char(t1.fecha,'DD-MM-YYYY') as fecha_creacion,to_char(t2.fecha_envio,'DD-MM-YYYY') as fecha_envio,t1.de as unidad_sol,t1.a as unidad_des,t1.usuario_sol,t3.unidad_medida,t3.cantidad_pedido,t3.tipo_item,t3.articulo,t4.detalle_solicitud
from adquisiciones.transaccion t1
inner join (select t1.cod_transaccion,t1.nro,t2.estado,t2.fecha_envio from adquisiciones.transaccion_nro t1 inner join adquisiciones.transaccion_fecha_envio t2 on t1.cod_transaccion=t2.cod_transaccion
        where t2.estado='ALM') t2 on t1.cod_transaccion=t2.cod_transaccion
inner join (select t1.cod_transaccion,t1.cod_trans_detalle,t1.unidad_medida,t1.cantidad_pedido,t3.articulo,t4.detalle as tipo_item 
        from adquisiciones.transaccion_detalle t1         
        inner join adquisiciones.item t3 on t1.cod_item=t3.cod_item
       inner join adquisiciones.tipo_item t4 on t3.cod_tipo_item=t4.cod_tipo_item) t3 on t3.cod_transaccion=t1.cod_transaccion
inner join adquisiciones.transaccion_det_complemento t4 on t3.cod_trans_detalle=t4.cod_trans_detalle
where t1.cod_transaccion=32 order by t3.tipo_item,t3.articulo