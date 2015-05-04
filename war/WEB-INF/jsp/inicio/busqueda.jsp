<%-- 
    Document   : busqueda
    Created on : 20-03-2014, 02:40:44 PM
    Author     : UMSA-JES
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BusquedaTransacciones</title>
        <script src="js/Buscadores.js"></script>
        <script>



        </script>
    </head>
    <body>
        <div class="caja_busqueda">
            <%--<form class="form_busqueda" action='<c:url value="/Busqueda.umsa"/>' method='post'>--%>
            <form id="buscar_transaccion" class="form_busqueda">

                <label>Tipo:</label>
                <!--
                <select name="tipo_transaccion">
                    <option value="">Ninguno</option>
                    <option value=1>SOLICITUD DE MATERIALES Y/O ACTIVOS</option>
                    <option value=2>PEDIDO DE MATERIALES Y/O ACTIVOS</option>
                    <option value=3>CONSULTORIAS</option>
                    <option value=4>OBRAS</option>
                </select>
                -->
                <select name="tipo_transaccion">
                    <option value="">Ninguna</option>
                    <c:forEach var="lista" items="${tipos.pageList}" varStatus="contador">
                        <option value = <c:out value="${lista.cod_w}"/>><c:out value="${lista.detalle_w}"/></option>
                    </c:forEach>
                </select>
                <label>Modalidad:</label>
                <!--
                <select name="tipo_cuantia">
                    <option value="">Ninguna</option>
                    <option value=1>Compra Menor</option>
                    <option value=2>Anpe1</option>
                    <option value=3>Anpe2</option>
                    <option value=4>Licitaciones</option>
                </select>
                -->
                <select name="tipo_cuantia">
                    <option value="">Ninguna</option>
                    <c:forEach var="lista" items="${cuantias.pageList}" varStatus="contador">
                        <option value = <c:out value="${lista.cod_cuantia}"/>><c:out value="${lista.cuantia}"/></option>
                    </c:forEach>
                </select>
                <label>Detalle:</label>
                <input name="detalle" type="text"/>
                <label>Fecha de publicacion Capricornio:</label>
                <input name="fecha_publicacion" id="fecha_publicacion" type="text" placeholder="Ingrese gestión" value="<c:out value="${gestion}"/>"/>
                <label>Fecha de presentacion de Propuestas:</label>
                <input name="fecha_presentacion" id="fecha_presentacion" type="text" placeholder="dd/mm/aaaa"/>
                <label>CUCE-UMSA</label>
                <input name="cuce" type="text"/>
                <div id="tipo_busqueda">
                    <label>Ver:</label>
                    <input type="radio" name="ver" value="0"><span>Sólo Vigentes</span>
                    <input type="radio"  checked="true" name="ver" value="1"><span>Todos</span>
                </div>
                <label>Ordenar Por:</label>
                <select name="orden">
                    <option value="">Ninguno</option>
                    <option value=1>Unidad</option>
                    <option value=2>Fecha de Inicio</option>
                    <option value=3>Fecha de Presentación</option>
                    <option value=4>CUCE-UMSA</option>
                    <option value=5>Modalidad</option>
                </select>
                <!--<input class="btn_form" type="submit" name="" value="Buscar">-->
                <input id="btn-buscar" class="btn_form" type="button" value="Buscar"/>
                <input class="btn_form" type="reset" name="" value="Limpiar">
            </form>


            <div id="info_busqueda">
                <p>Numero de Resultadosx: <span id="span_total">0</span> Registros Encontrados</p> 
                <p>Numero de paginas: <span id="span_total_paginas">0</span></p>
                <p><span>Ir a Pagina: </span><input id="input_pagina" class="paginas" type="number" min="1" max="1" size="5"/><button id="ir_pagina">Ir a pagina</button></p>
                <p><h4>Pagina #: <span id="nro_pagina">0</span></h4></p>

            </div>
        </div>
        <div class="menu_paginacion" hidden>
            <input class="btn_anterior" type="button" value="Anterior"/>
            <input class="btn_siguiente" type="button" value="Siguiente"/>
        </div>
        <div class="tabla_resultados">

            <table>
                <thead>
                    <tr>
                        <th>Nro.</th>
                        <th>CUCE UMSA</th>
                        <th>Tipo</th>
                        <th>Unidad Sol.</th>
                        <th>Destino</th>
                        <th>Modalidad</th>
                        <th>Nro. Convocatoria</th>
                        <th>Detalle</th>
                        <th>Estado</th>
                        <th>Rastrear</th>
                        <th>Fecha Publicación</th>
                        <th>Fecha Presentación</th>
                        <th>Archivos</th>
                        <th>Formularios</th>
                    </tr>
                </thead>

                <tbody>
                    hola
                   
                </tbody>
                <p>Hla</p>
            </table>
            <div class="menu_paginacion" hidden>
                <input class="btn_anterior" type="button" value="Anterior"/>
                <input class="btn_siguiente" type="button" value="Siguiente"/>
            </div>
        </div>
        <div id="dialog_rastrear" title="Resultados">
            <table>
                <thead>
                    <tr>
                        <th>CUCE</th>
                        <th>ARTICULO</th>

                        <th>ESTADO</th>
                        <!--<th>Fecha</th>-->
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="dialog_vacio" title="Mensaje">
            <h3>Datos no Encontrados</h3>
        </div>
        <div id="dialog_archivos" title="Resultados Archivos">
            <nav><ul></ul></nav>
        </div>
        <div id="dialog_formularios" title="Resultados Formularios">
            <nav><ul></ul></nav>
        </div>
        <div id="dialog_mas_detalle" title="Seguimiento">
            <table>
                <thead>
                    <tr>
                        <th>Estado</th>
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="dialog_carga" title="Espere Por Favor"><img src="/capri-web/imagenes/cargando.gif" alt="Cargando....." height="150" width="auto"></div>
    </body>
</html>