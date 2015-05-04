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
        <title>JSP Page</title>
        <script type="text/javascript">
            function setRegistrar(f) {
                if(f.articulo.value == '--') {
                    alert('Debe elijir un articulo'); return false;
                }
                if(isNaN(parseInt(f.cantidad.value)))  {
                    alert('La cantidad debe ser un numero mayor a cero'); return false;
                }
                
                f.guardar.disabled = true;
                f.guardar.value = 'Enviando los datos...';
                /*si los datos no son correctos */
                return true;
            }            
        </script>
    </head>
    <body>
        <form name='forma' action='<c:url value="/TransaccionDetalleItems.umsa"/>' method='post'  onsubmit="return setRegistrar(this)">
    <div class="container_cuerpo">
        <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">Datos Generales ${tipo_tramite} Nro.: <c:out value="${nro_gestion}"/> </a>
            </h2>
            <div class="block" id="login-forms">
                    <fieldset class="mostrar">                        
                        <p>
                            <label>Usuario solicitante: </label>
                            <input name="usuario_sol" type="text" value="<c:out value="${usuario_sol}"/>" disabled/>
                            <input name="usuario_sol" type="hidden" value="<c:out value="${usuario_sol}"/>"/>
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
                    <input type=hidden name=tipo_tramite value="<c:out value="${tipo_tramite}"/>" >
                    <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >
            </div>
        </div>
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">Detalle ${tipo_tramite}</a>
            </h2>
            <div class="block" id="tables">
                <table>
                    <thead>
                        <tr>                            
                            <th>Tipo Item</th>
                            <th>Articulo</th>
                            <th>Unidad Medida</th>
                            <th>Cant.Pedida</th>                            
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lista" items="${listaTransaccionArticulos.pageList}" varStatus="contador">
                            <tr>                                    
                                <c:if test="${lista.tipo_item == null }">
                                    <tr class="odd">
                                    <td></td>
                                    <td><c:out value="${lista.articulo}"/></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </c:if>
                                <c:if test="${lista.tipo_item != null }">
                                    <tr>
                                    <td><c:out value="${lista.tipo_item}"/></td>
                                    <td><a href="<c:url value="/TransaccionArticuloComplemento.umsa">                                               
                                               <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                               <c:param name="usuario_sol" value="${usuario_sol}"/>
                                               <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                               <c:param name="ue_destino" value="${ue_destino}"/>
                                               <c:param name="articulo" value="${lista.articulo}"/>                                               
                                               <c:param name="nro_gestion" value="${nro_gestion}"/>
                                               <c:param name="cod_trans_detalle" value="${lista.cod_trans_detalle}"/>
                                               <c:param name="cod_transaccion" value="${cod_transaccion}"/>
                                               <c:param name="cod_tramite" value="${cod_tramite}"/>
                                               </c:url>">
                                        <c:out value="${lista.articulo}"/></a></td>
                                    <td><c:out value="${lista.unidad_medida}"/></td>
                                    <td><c:out value="${lista.cantidad_pedido}"/> </td>                                    
                                    <td>
                                        <a href="<c:url value="/TransaccionArticuloMostrarElimina.umsa">
                                               <c:param name="cod_tramite" value="${cod_tramite}"/>
                                               <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                               <c:param name="usuario_sol" value="${usuario_sol}"/>
                                               <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                               <c:param name="ue_destino" value="${ue_destino}"/>
                                               <c:param name="tipo_item" value="${lista.tipo_item}"/>
                                               <c:param name="articulo" value="${lista.articulo}"/>
                                               <c:param name="unidad_medida" value="${lista.unidad_medida}"/>
                                               <c:param name="cantidad_pedido" value="${lista.cantidad_pedido}"/>
                                               <c:param name="nro_gestion" value="${nro_gestion}"/>
                                               
                                               <c:param name="cod_trans_detalle" value="${lista.cod_trans_detalle}"/>
                                               <c:param name="cod_transaccion" value="${cod_transaccion}"/>
                                           </c:url>" >Eliminar</a>                                        
                                    </td>
                                </c:if>
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
                <a href="#" id="toggle-forms">Form. de Articulos</a>
            </h2>
            <div class="block" id="forms">
                
                    <fieldset>
                        <legend>Pedido de Materiales</legend>
                        <p>
                            <label>Tipo de Item: </label>
                            <select name="tipo_item" onchange="javascript:document.forma.submit();">
                                <option value="--">-- Elija Tipo de Item --
                                  <c:forEach var="lista" items="${listaItemTipo.pageList}">
                                    <option value="<c:out value="${lista.tipo_item}"/>"
                                        <c:if test="${lista.tipo_item== tipo_item}"> selected </c:if> >
                                    <c:out value="${lista.tipo_item}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label>Grupo: </label>
                            <select name="grupo" onchange="javascript:document.forma.submit();">
                                <option value="--">-- Elija Grupo --
                                  <c:forEach var="lista" items="${listaItemGrupo.pageList}">
                                    <option value="<c:out value="${lista.grupo}"/>"
                                        <c:if test="${lista.grupo == grupo}"> selected </c:if> >
                                    <c:out value="${lista.grupo}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label>Rubro: </label>
                            <select name="rubro" onchange="javascript:document.forma.submit();">
                                <option value="--">-- Elija Rubro --
                                  <c:forEach var="lista" items="${listaItemRubro.pageList}">
                                    <option value="<c:out value="${lista.rubro}"/>"
                                        <c:if test="${lista.rubro == rubro}"> selected </c:if> >
                                    <c:out value="${lista.rubro}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label>Articulo: </label>
                            <select name="articulo" onchange="javascript:document.forma.submit();">
                                <option value="--">-- Elija Articulo --
                                  <c:forEach var="lista" items="${listaItemArticulo.pageList}">
                                    <option value="<c:out value="${lista.articulo}"/>"
                                        <c:if test="${lista.articulo == articulo}"> selected </c:if> >
                                    <c:out value="${lista.articulo}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label>Unidad Medida: </label>
                            <input name="" type="text" value="<c:out value="${unidad_medida}"/>" disabled/>
                            <input name="unidad_medida" type="hidden" value="<c:out value="${unidad_medida}"/>" />
                            <input name="nro_gestion" type="hidden" value="<c:out value="${nro_gestion}"/>" />
                        </p>
                        <p>
                            <label>Cantidad: </label>
                            <%--<input type="text" name="cantidad" value="" onblur='validar(this,"9")'/>--%>
                            <input type="text" name="cantidad" value="" />
                        </p>                          
                        <input type=submit name=boton value='Guardar Articulo' onclick="document.forma.action='<c:url value="/TransaccionArticuloGuarda.umsa"/>'" >
                        <input type=submit name=boton value='Terminar' onclick="document.forma.action='<c:url value="/TransaccionMateriales.umsa"/>'" >
                    </fieldset>                
            </div>
        </div>
    </div>    
    </div>
    </form>

    </body>
</html>
