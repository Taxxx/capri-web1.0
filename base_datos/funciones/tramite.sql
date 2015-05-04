CREATE OR REPLACE PROCEDURE ADQUISICIONES.transaccion_reportes(tgestion in number,tfecha in varchar2,tunidad_sol in varchar2, tunidad_des in varchar2,tusuario_sol in varchar2,tusr_reg in number,tingreso_material in varchar2,cod_tran out number)
as
begin
    insert into adquisiciones.transaccion(cod_transaccion,GESTION,FECHA,DE,A,USUARIO_SOL,USUARIO_REG,INGRESO_MATERIAL)
    values (ADQUISICIONES.SEC_cod_transaccion.NEXTVAL,tgestion,tfecha,tunidad_sol,tunidad_des,tusuario_sol,tusr_reg,tingreso_material);        
    SELECT ADQUISICIONES.SEC_cod_transaccion.CURRVAL  into cod_tran FROM DUAL;
    exception
        when others then
            cod_tran:= -1;    
end;

