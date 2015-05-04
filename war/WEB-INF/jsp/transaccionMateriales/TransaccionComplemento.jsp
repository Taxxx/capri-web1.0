<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoDetalle
    Created on : 15-nov-2010, 10:34:31
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page </title>
    </head>
    <body>
        <form name='forma' method='post'>
    <div class="container_cuerpo">
        <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">Datos Generales del Pedido de Materiales Nro.: <c:out value="${nro_gestion}"/> </a>
            </h2>
            <div class="block" id="login-forms">
                    <fieldset class="mostrar">
                        <p>
                            <label>Tipo pedido: </label>
                            <input name="tipo_tramite" type="text" value="<c:out value="${tipo_tramite}"/>" disabled/>
                            <input name="tipo_tramite" type="hidden" value="<c:out value="${tipo_tramite}"/>"/>
                        </p>
                        <p>
                            <label>Usuario solicitante: </label>
                            <input name="usuario_sol" type="text" value="<c:out value="${usuario_sol}"/>" disabled/>
                            <input name="usuario_sol" type="hidden" value="<c:out value="${usuario_sol}"/>"/>
                        </p>
                        <p>
                            <label>Detalle Solicitud: </label>
                            <input type="text" name="detalle" value="${detalle}" disabled/>
                            <input type="hidden" name="detalle" value="${detalle}" />
                        </p>
                        <p>
                            <label>Unidad Solicitante </label>
                            <input name="ue_solicitante" type="text" value="<c:out value="${ue_solicitante}"/>" disabled/>
                            <input name="ue_solicitante" type="hidden" value="<c:out value="${ue_solicitante}"/>" />
                        </p>
                        <p>
                            <label>Unidad Destino: </label>
                            <input name="ue_destino" type="text" value="<c:out value="${ue_destino}"/>" disabled/>
                            <input name="ue_destino" type="hidden" value="<c:out value="${ue_destino}"/>" />
                        </p>
                    </fieldset>                        
                    <input type=hidden name=cod_transaccion value="<c:out value="${cod_transaccion}"/>" >
                    <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >                    
            </div>
        </div>
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables"><c:out value="${articulo}"/></a>
                            <input name="articulo" type="hidden" value="<c:out value="${articulo}"/>" />
            </h2>
            <div class="block" id="tables">
                <table>
                    <thead>
                        <tr>                            
                            <th>Detalle complementario</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lista" items="${listaComplemento.pageList}" varStatus="contador">
                            <c:if test="${(contador.count mod 2) == 0}">
                                <tr class="odd">
                            </c:if>
                                <td><c:out value="${lista.detalle}"/></td>
                                <td>
                                    <a href="<c:url value="/TransaccionEliminaComplemento.umsa">                                           
                                           <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                           <c:param name="usuario_sol" value="${usuario_sol}"/>
                                           <c:param name="detalle" value="${detalle}"/>
                                           <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                           <c:param name="ue_destino" value="${ue_destino}"/>
                                           <c:param name="articulo" value="${articulo}"/>                                           
                                           <c:param name="nro_gestion" value="${nro_gestion}"/> 
                                           <c:param name="cod_complemento" value="${lista.cod_complemento}"/>
                                           <c:param name="cod_trans_detalle" value="${cod_trans_detalle}"/>
                                           <c:param name="cod_transaccion" value="${cod_transaccion}"/>
                                           <c:param name="cod_tramite" value="${cod_tramite}"/>
                                           <c:param name="cod_w" value="${cod_w}"/>
                                       </c:url>"
                                       onclick="javascript:return confirm('¿Esta seguro(a) que desea eliminar el registro?')">Eliminar</a>
                                       
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>                
            </div>
        </div>
    </div>    
    <div class="grid_7">
        <div class="box">
            <h2>
                <a href="#" id="toggle-forms">Form. Complementario del articulo</a>
            </h2>
            <div class="block" id="forms">
                
                    <fieldset>                        
                        <p>
                            <label>Detalle complementario: </label>
                            <input name="complemento" type="text" value="<c:out value="${complemento}"/>" />
                            <input name="cod_trans_detalle" type="hidden" value="<c:out value="${cod_trans_detalle}"/>" />
                        </p>
                        <input name="cod_transaccion" type="hidden" value="<c:out value="${cod_transaccion}"/>" />
                        <input name="articulo" type="hidden" value="<c:out value="${articulo}"/>" />
                        <input name="nro_gestion" type="hidden" value="<c:out value="${nro_gestion}"/>" />
                        <input type=hidden name=cod_w value="<c:out value="${cod_w}"/>" >
                        
                        <input type="submit" name="accion" value='Adicionar Complemento' onclick="document.forma.action='<c:url value="/TransaccionGuardaComplemento.umsa"/>'" >
                        <input name="tipo_item" type="hidden" value="--" />                        
                        <input type="submit" name="accion" value='Terminar Complemento' onclick="document.forma.action='<c:url value="/TransaccionItemsAbre.umsa"/>'" >
                        <%--
                        <c:set var="str" value="${tipo_pedido}" />
                        <jsp:useBean id="str" type="java.lang.String" />
                        <c:if test='<%="Insumos".equals(str)%>'>
                            <input type="submit" name="accion" value='Terminar Complemento' onclick="document.forma.action='<c:url value="/PedidoDetalleInsumos.umsa"/>'" >
                        </c:if>

                        <c:if test='<%="Activos".equals(str)%>'>
                            <input type="submit" name="accion" value='Terminar Complemento' onclick="document.forma.action='<c:url value="/PedidoDetalleActivos.umsa"/>'" >
                        </c:if>

                        <c:if test='<%="Servicios".equals(str)%>'>
                            <input type="submit" name="accion" value='Terminar Complemento' onclick="document.forma.action='<c:url value="/PedidoDetalleServicios.umsa"/>'" >
                        </c:if>--%>
                    </fieldset>                
            </div>
        </div>
    </div>    
    </div>
    </form>

    </body>
</html>
