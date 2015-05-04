<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoBandeja
    Created on : 08-nov-2010, 14:39:09
    Author     : julian
--%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="">
    <body>
       <div class="container_cuerpo">     
        <div class="grid_16">
            <div class="box">
                <h2>
                    <a href="#" id="toggle-tables">BANDEJA ${tipo_tramite}</a>
                </h2>
                <div class="block" id="tables">
                    <table>
                        <thead>
                            <tr>
                                <th>Borrador</th>
                                <th>SOL. COMPRA</th>                                
                                <th>O. COMPRA</th>
                                <th>SOLICITANTE</th>
                                <th>DETALLE</th>                                
                            </tr>
                        </thead>
                        Para imprimir el reporte, debe hacer click sobre el numero de transacci&oacute;n
                        <tbody>
                            <c:forEach var="lista" items="${ListSolicitudes.pageList}" varStatus="contador">
                                <c:if test="${(contador.count mod 2) == 0}">
                                    <tr class="odd">
                                </c:if>
                                        <td><a href="<c:url value="/reporteSolicitudBorrador"/>?&tipo_tramite=${tipo_tramite}&cod_transaccion=${lista.cod_transaccion}&cod_estado=PPTO " target="_blank">Ver Borrador</a></a></td>
                                    <td>
                                        <c:set var="str" value="${lista.cod_estado}" />
                                        <jsp:useBean id="str" type="java.lang.String" />
                                        <c:if test='<%=!("B".equals(str) || "X".equals(str) || "A".equals(str) || "ALM".equals(str))%>'>                                            
                                             <a href="<c:url value="/reporteSolicitud"/>?&tipo_tramite=${tipo_tramite}&cod_transaccion=${lista.cod_transaccion}&cod_estado=PPTO " style="color:green"  target="_blank">${lista.nro}</a>
                                        </c:if>
                                        <c:if test='<%=("B".equals(str) || "X".equals(str) || "A".equals(str) || "ALM".equals(str))%>'>
                                            <c:out value="${lista.nro}"/>
                                        </c:if>        
                                    </td>
                                    
                                    <c:if test="${lista.nro != null }">
                                        <c:if test="${lista.tipo_sol == 1 }">
                                            <td><a href="<c:url value="/reporteOrden"/>?&cod_tramite=2&cod_estado=ALM1&cod_trans_nro=${lista.cod_trans_nro}" style="color:dodgerblue"  target="_blank">${lista.nro_orden}</a></td>
                                        </c:if>
                                        <c:if test="${lista.tipo_sol == 0 }">
                                            <td><a href="<c:url value="/reporteOrdenDescargo"/>?&cod_tramite=2&cod_estado=ALM1&cod_trans_nro=${lista.cod_trans_nro}" style="color:dodgerblue"  target="_blank">${lista.nro_orden}</a></td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${lista.nro == null }">
                                        <td><a href="<c:url value="/reporteOrden"/>?&cod_tramite=2&cod_estado=JUR&cod_trans_nro=${lista.cod_trans_nro}" style="color:dodgerblue"  target="_blank">${lista.nro_orden}</a></td>
                                    </c:if>
                                    <td><c:out value="${lista.usuario_sol}"/></td>
                                    <td><c:out value="${lista.detalle}"/> </td>                                 
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>
                </div>
            </div>          
        </div>
       </div>
    </body>
</html>
