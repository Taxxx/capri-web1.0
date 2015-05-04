<%@ include file="../Superior.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head><title>UMSA</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<link href="../listas.css" rel="stylesheet" type="text/css" />
<link href="../navmenu.css" rel="stylesheet" type="text/css" />

</head>
<body>
    <div id="navsite">           
        Materia : <c:out value="${sigla}"/>
        <ul class="n1">                               
                <c:forEach var="lista" items="${lRoles.pageList}">                                                        
                   <li><a href="<c:url value="${lista.ruta_enlace}">  <c:param name="enlace" value="${lista.nombre_enlace}"/>  </c:url>" target="cuerpo"><c:out value="${lista.nombre_enlace}"/></a></li>
                </c:forEach>                                           
        </ul>                             
    </div>     
</body>
</html>