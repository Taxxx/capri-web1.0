<%-- 
    Document   : proveedores
    Created on : 20-03-2014, 04:29:33 PM
    Author     : UMSA-JES
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BusquedaProveedores</title>
        <script></script>
        <script src="js/Buscadores.js"></script>
    </head>
    <body>
        <div class="caja_busqueda">
            <form id="buscar_proveedores" class="form_busqueda" method='post'>
                <label>Tipo de Documento:</label>
                 <select name="tipo_id">
                    <option value="">Ninguna</option>
                    <!--<option value="C">C</option>
                    <option value="I">I</option>
                    <option value="N">N</option>
                    <option value="O">O</option>
                    <option value="P">P</option>
                    <option value="R">R</option>
                    <option value="T">T</option>
                    <option value="U">U</option>-->
                    <c:forEach var="lista" items="${tipos_doc.pageList}" varStatus="contador">
                        <option value="<c:out value="${lista.tipo_id}"/>"><c:out value="${lista.sigla}"/></option>
                    </c:forEach>
                </select>
                <label>Tipo Persona:</label>
                <select name="clase_beneficiario">
                    <option value="">Ninguna</option>
                    <!--<option value="J">J</option>
                    <option value="N">N</option>
                    <option value="U">U</option>-->
                    <c:forEach var="lista" items="${clases_beneficiario.pageList}" varStatus="contador">
                        <option value="<c:out value="${lista.clase_beneficiario}"/>"><c:out value="${lista.descripcion}"/></option>
                    </c:forEach>
                </select>
                <label>Nombre:</label>
                <input type="text" name="nombre"/>
                <label>Nombre Comercial:</label>
                <input type="text" name="nombre_comercial"/>
                <label>Codigo Documento:</label>
                <input type="text" name="documento"/>
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
        <div class="tabla_resultados">
            <div class="menu_paginacion" hidden>
                <input class="btn_anterior" type="button" value="Anterior"/>
                <input class="btn_siguiente" type="button" value="Siguiente"/>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Codigo Documento</th>
                        <th>Tipo de Documento</th>
                        <th>Clase Persona</th>
                        <th>Nombre</th>
                        <th>Nombre Comercial</th>
                        <th>Información</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
            <div class="menu_paginacion" hidden>
                <input class="btn_anterior" type="button" value="Anterior"/>
                <input class="btn_siguiente" type="button" value="Siguiente"/>
            </div>
        </div>
        <div id="dialog_vacio" title="Mensaje">
            <h3>Datos no Encontrados</h3>
        </div>
        <div id="dialog_detalle_proveedor" title="Detalle"></div>
        <div id="dialog_carga" title="Espere Por Favor"><img src="/capri-web/imagenes/cargando.gif" alt="Cargando....." height="150" width="auto"></div>
    </body>
</html>