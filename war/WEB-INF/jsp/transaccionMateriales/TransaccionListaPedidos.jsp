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
                                <th>PEDIDO DE MATERIAL</th>
                                <th>NOTA DE CONORMIDAD</th>
                                <th>SOLICITANTE</th>
                                <th>DETALLE</th>                                
                            </tr>
                        </thead>
                        Para imprimir el reporte, debe hacer click sobre el numero de transacci&oacute;n
                        <tbody>
                            <c:forEach var="lista" items="${ListPedidos.pageList}" varStatus="contador">
                                <c:if test="${(contador.count mod 2) == 0}">
                                    <tr class="odd">
                                </c:if>     
                                    <td><a href="<c:url value="/reportePedido"/>?&cod_tramite=${lista.cod_tramite}&cod_trans_nro=${lista.cod_trans_nro}" style="color:dodgerblue"  target="_blank">${lista.nro_pedido}</a></td>
                                    <%--td><a href="<c:url value="/reporteNotaConformidad"/>?&cod_tramite=${lista.cod_tramite}&cod_trans_nro=${lista.cod_trans_nro}">Imprimir</a></td--%>
                                    <td><button class="btn_nc" data-cod_trans_nro="<c:url value="${lista.cod_trans_nro}"/>">Generar</button></td>
                                    <!--td><a id="btn_notaConformidad" href="#">Imprimir</a></td-->
                                    <td><c:out value="${lista.usuario_sol}"/></td>
                                    <td><c:out value="${lista.detalle}"/> </td>                                    
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>
                </div>
                <div id="dialog_notaConformidad" title="NOTA DE CONFORMIDAD">
                    <form>
                        <input id="nc_ciudad" type="text" name="ciudad" placeholder="Ingrese La Ciudad"/>
                        <input id="nc_fecha" type="text" name="fecha"/>
                        <input id="nc_hora" type="text" name="hora" placeholder="Ingrese La Hora"/>
                        <input id="nc_lugar" type="text" name="lugar" placeholder="Ingrese El Lugar"/>
                        
                        <!--input type="text" name="proveedor" placeholder="Ingrese El Proveedor"/>
                        <input type="text" name="monto" placeholder="Ingrese el monto"/>
                        <input type="text" name="rpa" placeholder="RPA"/>
                        <input type="text" name="cargo_rpa" placeholder="Cargo RPA"/-->
                    </form>
                </div>
                <!--button id="btn_nc">Aja!</button-->
            </div>          
        </div>
       </div>
    </body>
</html>
