CREATE TABLE adquisiciones.gestion
(
  cod_gestion number(2) primary key,
  gestion number(4)
) tablespace adq_tablespace;

create sequence adquisiciones.sec_cod_gestion increment by 1 start
with 1 maxvalue 1.0e28 minvalue 1 nocache cycle noorder;