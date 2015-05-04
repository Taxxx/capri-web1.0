<%-- 
    Document   : adjudicados
    Created on : 12-03-2015, 03:40:31 PM
    Author     : alex
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BusquedaAdjudicados</title>
        <script></script>
        <script src="js/Buscadores.js"></script>
    </head>
    <body>
        <div class="caja_busqueda">
            <form id="buscar_adjudicados" class="form_busqueda">
                <label>Codigo de Documento:</label>
                <input type="text" name="codigo"/>
                <label>Nombre o Nombre comercial:</label>
                <input type="text" name="nombre"/>
                <div>
                    <label>Gestion:</label>
                    <select id="select_gestiones" name="gestion">
                        <option value="">Escoja una Gestión</option>
                        <c:forEach var="lista" items="${gestiones.pageList}" varStatus="contador">
                            <option><c:out value="${lista.gestion}"/></option>
                        </c:forEach>
                    </select>
                    
                    <label>Partida: </label>
                    <!--<input type="text" name="partida"/>-->
                    <select id="select_partidas" name="partida">
                        <option value="">Ninguna</option>
                        <c:forEach var="lista" items="${partidas.pageList}" varStatus="contador">
                            <option value="<c:out value="${lista.partida}"/>"><c:out value="${lista.detalle_partida}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <label>Item:</label>
                <input type="text" name="articulo"/>
                
                <!--<label>Gestion:</label>
                <input type="text" name="gestion"/>-->
                
                <!--<label>Tipo Item:</label>
                <select name="tipo">
                    <option value="">Ninguno</option>
                    <!--<option value="1">Materiales</option>
                    <option value="2">Activos</option>
                    <option value="3">Servicios</option>
                    <option value="4">Consultorias</option>
                    <option value="5">Obras</option>-->
                    
                <input id="btn-buscar" class="btn_form" type="button" value="Buscar"/>
                <input class="btn_form" type="reset" value="Limpiar"/>
            </form>
            <div id="info_busqueda">
                <p>Numero de Resultados: <span id="span_total">0</span> Registros Encontrados</p> 
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
                        <th>No</th>
                        <th>Codigo de Documento</th>
                        <th>Nombre</th>
                        <th>Nombre Comercial</th>
                        <th>Gestion</th>
                        <th>Articulos Adjudicados</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
            <div class="menu_paginacion" hidden>
                <input class="btn_anterior" type="button" value="Anterior"/>
                <input class="btn_siguiente" type="button" value="Siguiente"/>
            </div>
            <div>
                
                <%--<c:out value="${saludo}" />
                <c:if test="${gestiones.pageList == null }">
                    <h5>Holaaaaa</h5>
                </c:if>
                <c:if test="${gestiones.pageList != null }">
                    <h5>Holaaaaa2</h5>
                </c:if>--%>
            </div>
        </div>
            
        <div id="dialog_rastrear" title="Resultados">
            <table>
                <thead>
                    <tr>
                        <th>PARTIDA</th>
                        <th>DETALLE</th>
                        
                        <th>GESTION</th>
                        <!--<th>Fecha</th>-->
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="dialog_vacio" title="Mensaje">
            <h3>Datos no Encontrados</h3>
        </div>
        <div id="dialog_carga" title="Espere Por Favor"><img src="/capri-web/imagenes/cargando.gif" alt="Cargando....." height="150" width="auto"></div>
    </body>
</html>
