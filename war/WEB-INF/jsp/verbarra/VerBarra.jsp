<%@ include file="../Superior.jsp" %>

<body OnLoad="document.forma.submit()">

<c:if test="${empty rol}">
  <form name=forma method='post' action='<c:url value="/login/login.umsa" />' ></form>
</c:if>

<c:if test="${!empty rol}">
  <!--<form name=forma method='post' action='<c:url value="/verCuerpo/FormPrincipal.umsa" />' ></form>-->
  <form name=forma method='post' action='<c:url value="/menu/MenuPrincipal.umsa" />' >      
  </form>
</c:if>


</body>