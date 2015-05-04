<%-- 
    Document   : proceso
    Created on : 25-04-2014, 01:58:12 PM
    Author     : UMSA-JES
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/main_style.css"/>
        <script>
            function autofitIframe(id){
                if (!window.opera && document.all && document.getElementById){
                    id.style.height=id.contentWindow.document.body.scrollHeight;
                } else if(document.getElementById) {
                    id.style.height=id.contentDocument.body.scrollHeight+"px";
                }
            }
        </script>
    </head>
    <body>
        <!--
        <nav id="prueba_nav">
            <ul>
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
            </ul>
        </nav>
        -->
        <%--<c:if test="${!empty rol}">--%>
        <div id="menu_operaciones">
           

            <nav>
                <!--<h3 id="btn_transacciones">Transacciones</h3>-->
                <ul>
                    <li>
                        <a href="<c:url value="/TransaccionMateriales.umsa">
                           <c:param name="cod_tramite" value="1"/>
                           <c:param name="cod_w" value="1"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_solicitud"></div>
                            <!--<img id="img_solicitud"></img>
                            <span id="img_solicitud">sdsd</span>-->
                           Solicitud</a>
                    </li>
                    <li>
                        <a href="<c:url value="/TransaccionPedidos.umsa">
                           <c:param name="cod_tramite" value="4"/>
                           <c:param name="cod_w" value="2"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_pedidos"></div>
                           Pedido de materiales</a>
                    </li>
                    <%--li>
                        <a href="<c:url value="/TransaccionConsultoriasBandeja.umsa">
                           <c:param name="cod_tramite" value="1"/>
                           <c:param name="cod_w" value="3"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_consultor"></div>
                           Consultores</a>   
                    </li>
                    <li>
                        <a href="<c:url value="/TransaccionObrasBandeja.umsa">
                           <c:param name="cod_tramite" value="1"/>
                           <c:param name="cod_w" value="4"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_obras"></div>
                           Obras</a>   
                    </li--%>
                    <li>
                        <a href="<c:url value="/TransaccionListaSolicitudes.umsa">
                           <c:param name="cod_tramite" value="1"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_lista_solicitud"></div>
                           Lista Sol./Ordenes de Compra</a>   
                    </li>                                                                                                    
                    <li>
                        <a href="<c:url value="/TransaccionPedidosLista.umsa">
                           <c:param name="cod_tramite" value="4"/>
                           </c:url>" target="cuerpo_transaccionx" >
                            <div id="img_lista_pedidos"></div>
                           Lista de Pedido de Materiales</a>   
                    </li>                                                      
                </ul>

            </nav>
        </div>
        <%--</c:if>--%> 
        <iframe name="cuerpo_transaccionx" id="cuerpo_transaccionx" src="/capri-web/TransaccionMateriales.umsa?cod_tramite=1&cod_w=1" >
            
        </iframe>
    </body>
</html>
