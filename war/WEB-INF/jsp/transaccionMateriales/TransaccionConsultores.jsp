<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoSolicitud
    Created on : 11-nov-2010, 9:49:54
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
                if(f.usuario_sol.value.length == 0) {
                    alert('EL nombre del solicitante no puede estar vacio'); return false;
                }
                if(f.ue_solicitante.value == '--') {
                    alert('Debe elijir la unidad solicitante'); return false;
                }
                if(f.ue_destino.value == '--') {
                    alert('Debe elejir la unidad de destino'); return false;
                }
                if(f.contrato.value == '--') {
                    alert('Debe elejir el tipo de contrato'); return false;
                }
                /*f.guardar.disabled = true;
                f.guardar.value = 'Enviando los datos...';*/
                /*si los datos no son correctos */
                return true;
            }            
        </script>
    </head>
    <body>
    <div class="container_cuerpo">
        <div class="box">
            <h2>
                    <a href="#" id="toggle-forms">Forms</a>
            </h2>
            <div class="block" id="forms">
                <form action='<c:url value="/TransaccionConsultoresGenera.umsa"/>' method='post' name="frmInicio" onsubmit="return setRegistrar(this)">
                <%--<form method='post' name="frmInicio" enctype='multipart/form-data' onsubmit="return setRegistrar(this)">--%>
                    <fieldset>
                        <legend>${tipo_tramite}</legend>                        
                        <p>
                            <label>Nombre Solicitante: </label>
                            <input type="text" name="usuario_sol" value="" />
                        </p>                        
                        <p>
                            <label>Detalle Solicitud: </label>
                            <input type="text" name="detalle" value="" />
                        </p>                        
                        <p>
                            <label>Unidad Solicitante: </label>
                            <select name="ue_solicitante">
                                <option value="--" selected>-- Elija Unidad Solicitante --
                                  <c:forEach var="lista" items="${listaUE.pageList}">
                                    <option value="<c:out value="${lista.apert_prog}"/>">
                                    <c:out value="${lista.apert_prog}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label>Unidad Destino: </label>
                            <select name="ue_destino">
                                <option value="--" selected>-- Elija Unidad Solicitante --
                                  <c:forEach var="lista" items="${listaUE.pageList}">
                                    <option value="<c:out value="${lista.apert_prog}"/>">
                                    <c:out value="${lista.apert_prog}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>  
                        <p>
                            <label>Tipo Contrato: </label>
                            <select name="cuantia">
                                <option value="--" selected>-- Elija Un Contrato --
                                  <c:forEach var="lista" items="${listaCuantia.pageList}">
                                    <option value="<c:out value="${lista.cod_cuantia}"/>">
                                    <c:out value="${lista.cuantia}"/>
                                    </option>
                                  </c:forEach>
                            </select>
                        </p>  
                        <p>
                            <label>Tipo Solicitud: </label>
                            <select name="solicitud">                                                                  
                                    <option value="1">NORMAL</option>                                  
                                    <option value="0">DESCARGO</option>                                  
                            </select>
                        </p> 
                        <input name="tipo_tramite" type="hidden" value="<c:out value="${tipo_tramite}"/>"/>                        
                        <input name="cod_w" type="hidden" value="<c:out value="${cod_w}"/>"/>
                        <input name="cod_tramite" type="hidden" value="<c:out value="${cod_tramite}"/>"/>
                        <input type="submit" value="Guardar"/>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
