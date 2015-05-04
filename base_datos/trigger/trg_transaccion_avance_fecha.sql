CREATE OR REPLACE TRIGGER ADQUISICIONES.TRG_TRANSACCION_AVANCE_FECHA
BEFORE UPDATE
ON ADQUISICIONES.TRANSACCION_DETALLE 
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
DECLARE
   
BEGIN
   IF (:NEW.estado != :OLD.estado)
   THEN
      IF (adquisiciones.transaccion_nro_items (:NEW.cod_trans_detalle,:NEW.estado) = 0)
      THEN
         INSERT INTO adquisiciones.transaccion_envio_fecha
              VALUES (:NEW.cod_trans_detalle, :NEW.estado, SYSDATE);
      ELSE
         UPDATE adquisiciones.transaccion_envio_fecha
            SET fecha_envio = SYSDATE
          WHERE cod_trans_detalle = :NEW.cod_trans_detalle
                AND estado = :NEW.estado;
      END IF;
   END IF;
END trg_transaccion_avance_fecha;
/