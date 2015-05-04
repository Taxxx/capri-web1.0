CREATE OR REPLACE FUNCTION ADQUISICIONES.transaccion_nro_items(v_cod_trans_detalle in number,v_estado in varchar2) 
RETURN number
IS 
  nro number(2); 
BEGIN
    nro:=0;
    select count(cod_trans_detalle) into nro from adquisiciones.transaccion_envio_fecha 
    where cod_trans_detalle=v_cod_trans_detalle and estado=v_estado;    
    RETURN nro; 
END;
/
