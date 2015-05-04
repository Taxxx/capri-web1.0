CREATE OR REPLACE TYPE adquisiciones.type_item IS OBJECT
(
cod_item number(5),
unidad_medida varchar2(30),
tipo_item varchar2(50),
articulo varchar2(200)
)
/
CREATE OR REPLACE TYPE adquisiciones.type_tipo_items AS TABLE OF type_item
/

CREATE OR REPLACE FUNCTION ADQUISICIONES.items_busca(v_item in varchar2)
RETURN type_tipo_items
AS
    v_type_tipo_items type_tipo_items;
    v_param varchar2(100);
BEGIN 
    v_param:=upper(v_item);
    v_param:='%'||v_param||'%';
    SELECT adquisiciones.type_item(a.cod_item,a.unidad_medida,a.tipo_item, a.articulo )
    BULK COLLECT INTO v_type_tipo_items
    FROM
        (select t2.cod_item,t1.unidad_medida,t3.detalle as tipo_item, t1.articulo 
         from adquisiciones.item t1 
         inner join adquisiciones.tipo_item t3 on t1.cod_tipo_item=t3.cod_tipo_item
         where UPPER(t1.articulo) like v_param 
        ) A;  
    RETURN v_type_tipo_items;

    EXCEPTION
    WHEN OTHERS THEN
        v_type_tipo_items.DELETE;
    RETURN v_type_tipo_items;
END;
/

SELECT cod_item,unidad_medida,tipo_item, articulo
                FROM TABLE(ADQUISICIONES.items_busca('escrit')) ;