<%@ include file="../Superior.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="">
<body>
    <div class="container_3">
    <div class="grid_4">
        <div class="box">
            <h2>
                <a href="#" id="toggle-login-forms">Login y Passw.</a>
            </h2>
            <div class="block" id="login-forms">
                <form name='forma' action='<c:url value="/buscarUsuario.umsa"/>' method='post'>
                    <fieldset class="login">
                        <legend>Login</legend>
                        <p class="notice">Introduzca sus datos .</p>
                        <p>
                            <label>Usuario: </label>
                            <input type="password" name="apodo" />
                        </p>
                        <p>
                            <label>Password: </label>
                            <input type="password" name="clave" />
                        </p>
                        <input class="login button" type="submit" value="Login" />
                        <!--<font color="red"> <c:out value="${mensaje}"/> </font>-->
                        <p class="error"><c:out value="${mensaje}"/></p>
                    </fieldset>                        
                </form>
                <form action="">
                    <fieldset>
                        <legend>Registro</legend>
                        <p>Si desea consultar informacion al sistema, se le insinua hacer llegar una solicitud al DTIC-UMSA.</p>
                    </fieldset>
                </form>
            </div>
        </div>  
    </div>
    </div>
</body>
</html>