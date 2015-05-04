<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoBandeja
    Created on : 08-nov-2010, 14:39:09
    Author     : julian
--%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="">
    <head>
        <script src="js/Operaciones.js"></script>
        <script>
            
             
        </script>
    </head>
    <body>
       <div class="container_cuerpo">
       <div class="grid_16">
            <ul class="nav main">
                <p class="nota"> ${mensaje}                
                </p>
                <li>                                        
                    <a href="<c:url value="/TransaccionObras.umsa">
                                                    <c:param name="tipo_tramite" value="${tipo_tramite}"/>                                                    
                                                    <c:param name="cod_tramite" value="${cod_tramite}"/>                                                    
                                                    <c:param name="cod_w" value="${cod_w}"/>
                                                </c:url>" target="cuerpo_transaccionx" >
                                               NUEVO(A) ${tipo_tramite}</a> 
                </li>
            </ul>                
        </div>        
        <div class="grid_16">
            <div class="box">
                <h2>
                    <a href="#" id="toggle-tables">BANDEJA ${tipo_tramite}</a>
                </h2>
                <div class="block" id="tables">                    
                    <table>
                        <thead>
                            <!--<tr>
                                <th colspan="6" class="table-head">Pedido de Materiales</th>
                            </tr>-->
                            <tr>
                                <th>CUCE UMSA.</th>
                                <th>SOLICITANTE</th>
                                <th>DETALLE</th>
                                <th>OBSERVACION</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lista" items="${ListBandeja.pageList}" varStatus="contador">
                                <c:if test="${(contador.count mod 2) == 0}">
                                    <tr class="odd">
                                </c:if>                                    
                                    <td><a href="<c:url value="/TransaccionObrasAbre.umsa"> 
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
                                               <c:param name="cod_transaccion" value="${lista.cod_transaccion}"/>
                                                </c:url>"
                                           onclick="javascript:return confirm('¿Desea AVANZAR esta transaccion ?')"  style="color:green">Avanzar</a>
                                        <a href="<c:url value="/TransaccionElimina.umsa">
                                               <c:param name="cod_tramite" value="${cod_tramite}"/> 
                                               <c:param name="cod_w" value="${lista.cod_w}"/>
                                               <c:param name="cod_transaccion" value="${lista.cod_transaccion}"/> 
                                               <c:param name="tipo_tramite" value="${tipo_tramite}"/> 
                                           </c:url>"
                                               onclick="javascript:return confirm('¿Desea ELIMINAR la solcitud ${lista.nro_gestion}?')" style="color:red">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>
                </div>
                <div class="box">
                    <div class="tabla_resultados">
                        <h2>Bandeja Enviados</h2>
                        <table>
                            <thead>
                                <!--<tr>
                                    <th colspan="6" class="table-head">Pedido de Materiales</th>
                                </tr>-->
                                <tr>
                                    <th>CUCE</th>
                                    <th>Fecha</th>
                                    <th>Detalle</th>
                                    <th>Solicitante</th>
                                    <th>ESTADO</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="lista" items="${listaEnviados.pageList}" varStatus="contador">
                                    <tr>
                                        <td>${lista.cuce}</td>
                                        <td>${lista.fecha}</td>
                                        <td>${lista.detalle}</td>
                                        <td>${lista.usuario_sol}</td>
                                        <td><button data-cod_transaccion="${lista.cod_transaccion}" class="btn_detalle_env">Ver</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="dialog_detalle_env" title="Detalle">
                    <table>
                        <thead>
                            <th>CUCE</th>
                            <th>ARTICULO</th>
                            <th>DETALLE</th>
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
