<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoSolicitud
    Created on : 11-nov-2010, 9:49:54
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            #nat {
                color: purple;
                background-color: #d8da3d 
            }
        </style>
        <script type="text/javascript">
            /*$("#botonciloo").on("click",function(){
                alert("Changos :D");
            }*/
           
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
                if(f.cod_cuantia.value == '--') {
                    alert('Debe elejir el tipo de cuantia'); return false;
                }
                if(f.cod_w.value == '0') {
                    alert('Debe elejir el tipo de proceso'); return false;
                }
                
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
            <!--<button id="botonprueba">hola</button>-->
            <div class="block" id="forms">
                
                <c:if test="${cod_w == 1 || cod_w == 2}">
                    <form action='<c:url value="/TransaccionGuarda.umsa"/>' method='post' name="frmInicio" onsubmit="return setRegistrar(this)">
                </c:if>  
                <c:if test="${cod_w == 3}">
                    <form action='<c:url value="/TransaccionConsultoresGenera.umsa"/>' method='post' name="frmInicio" onsubmit="return setRegistrar(this)">
                </c:if>
                <c:if test="${cod_w == 4}">
                    <form action='<c:url value="/TransaccionObrasGenera.umsa"/>' method='post' name="frmInicio" onsubmit="return setRegistrar(this)">
                </c:if>
                    <fieldset>
                        <legend>${tipo_tramite}</legend>                        
                        <p>
                            <label>Nombre persona creadora de la solicitud: </label>
                            <input type="text" name="user_maker" value="" required placeholder="Ingrese su nombre completo"/>
                        </p>
                        <p>
                            <label>Nombre Jefe U. Solicitante: </label>
                            <input type="text" name="usuario_sol" value="" required placeholder="Ingrese el nombre del Jefe o Jefa de su respectiva unidad"/>
                        </p>                        
                        <p>
                            <label>Objeto de la Contratación: </label>
                            <input type="text" name="detalle" value="" required placeholder="Detalle general del proceso que esta creando">
                        </p>
                        <p>
                            <label>Unidad Solicitante: </label>
                            <select name="ue_solicitante" required>
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
                            <input type="text" name="ue_destino" value="" placeholder="Ingrese descripcion de la Unidad Destino" required/>
                        </p>
                        <c:if test="${cod_w != 2}">
                            <p>
                                <label>Tipo Cuantia: </label>
                                <select id="cod_cuantia" name="cod_cuantia" required>
                                    <option value="--" selected>-- Elija Cuantia --
                                      <c:forEach var="lista" items="${listaCuantia.pageList}">
                                        <option value="<c:out value="${lista.cod_cuantia}"/>">
                                            <c:out value="${lista.cuantia}"/>
                                        </option>
                                      </c:forEach>
                                </select>
                            </p>
                           
                            <p>
                                <label>Tipo Proceso </label>
                                <select id="cod_w" name="cod_w" required>
                                    <option value="0"> -- Escoja un tipo de Proceso --</option>
                                    <option value="1">SOLICITUD DE MATERIALES Y/O ACTIVOS</option>
                                    <option value="3">CONSULTORIAS</option> 
                                    <option value="4">OBRAS</option>
                                    <option value="6">SERVICIOS</option>
                                    <option value="7">BIENES</option>
                                </select>
                            </p>
                            <p>
                                <label>Tipo Solicitud: </label>
                                <select name="solicitud">
                                    <option value="1">NORMAL</option>                                  
                                    <option value="0">DESCARGO</option>                                  
                                </select>
                            </p>
                        </c:if>
                        
                        <input name="tipo_tramite" type="hidden" value="<c:out value="${tipo_tramite}"/>"/>
                        <input name="cod_tramite" type="hidden" value="<c:out value="${cod_tramite}"/>"/>
                        <%--input name="cod_w" type="hidden" value="<c:out value="${cod_w}"/>"/--%>
                        <input type="submit" value="Guardar"/>
                        
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
