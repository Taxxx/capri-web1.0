<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoBandeja
    Created on : 08-nov-2010, 14:39:09
    Author     : julian
--%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="">
    <head>
        <script src="js/Operaciones.js"></script>
        
    </head>
    <body>
       <div class="container_cuerpo">
       <div class="grid_16">
            <ul class="nav main">
                <p class="nota"> ${mensaje}                
                </p>
                <li>
                    <%--<a href="<c:url value="/TransaccionSolicitud.umsa"/>" target="cuerpo"> NUEVO(A) ${tipo_tramite}</a>--%>
                    
                    <a href="<c:url value="/TransaccionSolicitud.umsa">
                        <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                        <c:param name="cod_tramite" value="${cod_tramite}"/>
                        <c:param name="cod_w" value="${cod_w}"/>
                    </c:url>" target="cuerpo_transaccionx" >
                   NUEVO(A) ${tipo_tramite}</a> 
                </li>
                <!--
                <li>
                    <a id="btn-poa">POA</a>
                </li>
                -->
            </ul>                
        </div>        
        <div class="grid_16">
            <div class="box">
                <div id="tabs">
            <ul>
                <li><a href="#tabs-1">Borrador</a></li>
                <li><a href="#tabs-2">Enviados</a></li>
                
            </ul>
            <div id="tabs-1">
               <h2>
                    <a href="#" id="toggle-tables">BANDEJA ${tipo_tramite}</a>
                </h2>
                <div class="tabla_resultados" id="tables">                    
                    <table>
                        <thead>
                            <!--<tr>
                                <th colspan="6" class="table-head">Pedido de Materiales</th>
                            </tr>-->
                            <tr>
                                <th>CUCE UMSA</th>
                                <th>SOLICITANTE</th>
                                <th>DETALLE</th>
                                <th>OBSERVACION</th>
                                <th>OPCIONES</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lista" items="${ListBandeja.pageList}" varStatus="contador">
                                <c:if test="${(contador.count mod 2) == 0}">
                                    <tr class="odd">
                                </c:if>                                    
                                    <td><a href="<c:url value="/TransaccionItemsAbre.umsa"> 
                                                    <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                                    <c:param name="nro" value="${lista.nro}"/>                                                    
                                                    <c:param name="cod_tramite" value="${cod_tramite}"/>
                                                    <c:param name="cod_w" value="${lista.cod_w}"/>
                                                    <c:param name="cod_transaccion" value="${lista.cod_transaccion}"/>
                                                </c:url>">
                                               <c:out value="${lista.cuce}"/></a></td>                                    
                                    <td><c:out value="${lista.usuario_sol}"/></td>
                                    <td><c:out value="${lista.detalle}"/> </td>
                                    <td><c:out value="${lista.obs}"/></td>
                                    <td><a href="<c:url value="/TransaccionSolicitudAvanza.umsa">
                                               <c:param name="cod_estado" value="${lista.cod_estado}"/> 
                                               <c:param name="cod_tramite" value="${cod_tramite}"/> 
                                               <c:param name="tipo_tramite" value="${tipo_tramite}"/> 
                                               <c:param name="cod_w" value="${lista.cod_w}"/>
                                               <c:param name="cuce" value="${lista.cuce}"/>
                                               <c:param name="cod_transaccion" value="${lista.cod_transaccion}"/></c:url>"
                                           onclick="javascript:return confirm('¿Desea AVANZAR esta transaccion ?')"  style="color:green">Avanzar</a>
                                        <a href="<c:url value="/TransaccionElimina.umsa">
                                               <c:param name="cod_tramite" value="${cod_tramite}"/> 
                                               <c:param name="cod_w" value="${lista.cod_w}"/>
                                               <c:param name="cod_transaccion" value="${lista.cod_transaccion}"/> </c:url>"
                                               onclick="javascript:return confirm('¿Desea ELIMINAR la solcitud ${lista.nro_gestion}?')" style="color:red">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="tabs-2">
                <h2>Bandeja Enviados</h2>
                <div class="box" >
                    <div class="tabla_resultados" id="tables">
                        
                        <table>
                            <thead>
                                <!--<tr>
                                    <th colspan="6" class="table-head">Pedido de Materiales</th>
                                </tr>-->
                                <tr>
                                    <th>SC</th>
                                    <!--th>OC</th>
                                    <th>PM</th-->
                                    <th>Fecha</th>
                                    <th>Detalle</th>
                                    <th>Solicitante</th>
                                    <th>ESTADO</th>
                                    <th>DESCARGO</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <c:forEach var="lista" items="${listaEnviados.pageList}" varStatus="contador">
                                    <tr>
                                        <td>${lista.nro_sol_comp} <%--a href="<c:url value="/reporteSolicitud"/>?cod_transaccion=${lista.cod_transaccion}&cod_estado=PPTO " style="color:green"  target="_blank">Imprimir</a--%></td>
                                        <!--td></td>
                                        <td></td-->
                                        <td>${lista.fecha}</td>
                                        <td>${lista.detalle}</td>
                                        <td>${lista.usuario_sol}</td>
                                        <td><button data-cod_transaccion="${lista.cod_transaccion}" class="btn_detalle_env">Ver</button></td>
                                        <c:if test="${lista.tipo_sol == 0}">
                                            <td><a href="<c:url value="/GeneraOrdenCompra.umsa"/>?&cod_transaccion=${lista.cod_transaccion}&cod_trans_nro=${lista.cod_trans_nro}" target="_blank"><button>Genera Orden C.</button></a></td>
                                        </c:if>
                                        <c:if test="${lista.tipo_sol != 0}">
                                            <td>Proceso Normal</td>
                                        </c:if>
                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
                
                ///
                
                <div id="dialog_detalle_env" title="Detalle">
                    <table>
                        <thead>
                            <th>CUCE</th>
                            <th>ARTICULO</th>
                            
                            <th>ESTADO</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>2</td>
                                <td>3</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                
            </div>          
        </div>
        
       </div>
        
    </body>
</html>