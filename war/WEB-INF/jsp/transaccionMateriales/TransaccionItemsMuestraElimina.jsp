<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoInsumosMuestraElimina
    Created on : 22-nov-2010, 16:11:19
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name='forma' action='<c:url value="/TransaccionArticuloElimina.umsa"/>' method='post'>
    <div class="container_cuerpo">
        <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">DATOS GENERALES ${tipo_tramite} Nro.: <c:out value="${nro_gestion}"/> </a>
            </h2>
            <div class="block" id="login-forms">
                    <fieldset class="mostrar">
                        <p>
                            <label>Tipo tramite: </label>
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
            </div>
        </div>
        </div>
    <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-forms">Datos a Eliminar</a>
            </h2>
            <div class="block" id="login-forms">
                    <fieldset class="mostrar">
                        <p>
                            <label>Tipo Item: </label>
                            <input name="tipo_item" type="text" value="<c:out value="${tipo_item}"/>" disabled/>
                        </p>
                        <p>
                        <p>
                            <label>Articulo: </label>
                            <input name="articulo" type="text" value="<c:out value="${articulo}"/>" disabled/>
                        </p>
                        <p>
                            <label>Unidad Medida: </label>
                            <input name="unidad_medida" type="text" value="<c:out value="${unidad_medida}"/>" disabled/>
                        </p>
                        <p>
                            <label>Cantidada </label>
                            <input name="cantidad_pedido" type="text" value="<c:out value="${cantidad_pedido}"/>" disabled/>
                        </p>
                    </fieldset>
                    <input type=hidden name=cod_transaccion value="<c:out value="${cod_transaccion}"/>" >
                    <input type=hidden name=cod_trans_detalle value="<c:out value="${cod_trans_detalle}"/>" >
                    <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >
                    <input type=hidden name=cod_w value="<c:out value="${cod_w}"/>" >
                    <input type=hidden name=nro_gestion value="<c:out value="${nro_gestion}"/>" >

                    <input type=submit name="boton" value='Elimina Articulo' onclick="javascript:return confirm('¿Esta seguro(a) que desea eliminar el registro?')">                                        
            </div>
        </div>
    </div>
    </div>
    </form>

    </body>
</html>
