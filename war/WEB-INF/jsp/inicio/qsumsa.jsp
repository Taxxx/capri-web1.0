<%-- 
    Document   : qsumsa
    Created on : 25-07-2014, 07:07:40 PM
    Author     : UMSA-JES
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <hgroup>
            <h1>¿Quienes Somos?</h1>
        </hgroup>
        
        <div class="tabla_resultados">
            <table>
                <thead>
                    <tr>
                        <th>APERTURA</th>
                        <th>DETALLE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="lista" items="${prog_apert.pageList}" varStatus="contador">
                        
                        <tr>
                            
                            <td>${lista.apertura}</td>
                            <td>${lista.detalle}</td>
                            <%--
                            <td>${lista.cuce}</td>
                            <td>${lista.fecha}</td>
                            
                            <td>${lista.detalle}</td>
                            <td>${lista.usuario_sol}</td>
                            <td><button data-cuce="${lista.cuce}" class="btn_detalle_env">Ver</button></td>
                            --%>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
