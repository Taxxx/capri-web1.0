<%@ include file="../Superior.jsp" %>
<!--<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="">-->
<!DOCTYPE html>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
   
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster" />
    <script>
        //$("#mensaje-login_bad").dialog( "open" );
        function autofitIframe(id){
            if (!window.opera && document.all && document.getElementById){
                id.style.height=id.contentWindow.document.body.scrollHeight;
            } else if(document.getElementById) {
                id.style.height=id.contentDocument.body.scrollHeight+"px";
            }
        }
        <%--
        $(document).on("ready",inicia);
        function inicia(){
            alert(<c:out value="${mensaje}"/>);
        }
        --%>
        
    </script>
    
    </head>
   
<!-- <div class="container_16"></div> -->
<body>
    <div id="margen">
    <div id="header">
        <div id="header_top">
            <!--<div id="header_left"><img src="/capri-web/imagenes/logo.png" alt="UMSA COMPRA" height="auto" width="100%"/></div>-->
            <div id="header_left"><img src="/capri-web/imagenes/UmsaCompra.png" alt="UMSA COMPRA" height="100px" width="190px"/></div>
            <div id="header_center">
                <img class="img_logoX" height="70px" width="auto" src="/capri-web/imagenes/logo-ok.png"/>
                <!--
                <hgroup>
                    <h1>CAPRICORNIO UMSA</h1>
                    <h1>Sistema de Adquisiciones de la Universidad Mayor de San Andrés</h1>
                </hgroup>
                -->
                
            </div>
            <section id="header_right">
                <c:if test="${empty cliente}">
                    <button id="btn_inicia_sesion" class="btn_inicio">Iniciar Sesión</button>
                    <button id="btn_registro" class="btn_inicio">¿Quiere Registrarse?</button>
                </c:if>
                <div id="barra_info">
                    <%--<p><c:out value="${mensaje}"/></p>--%>
                    <c:if test="${!empty mensaje}">
                        <script>
                            /*alert("Hay caramba");
                            //$("#mensaje-login_bad").dialog( "open" );
                            $("#mensaje-registro").dialog( "open" );
                            alert("Hay caramba2");*/
                            $(document).on("ready",inicia);
                            function inicia(){
                                $("#mensaje-login_bad").dialog( "open" );
                            }
                        </script>
                    </c:if>
                    <c:if test="${!empty cliente}">
                        <h5><c:out value="${cliente}"/> - Rol: <span><c:out value="${rol}"/></span></h5>
                        <a href='<c:url value="/logout.umsa" />' target="_top" class="desconectar" width="89">Cerrar Sesi&oacute;n</a>
                    </c:if>
                </div>
                <%--
                <c:if test="${empty cliente}">
                    <section id="capri_login">
                        <form id="capri_form_login" name='forma' action='<c:url value="/buscarUsuario.umsa"/>' method='post'>
                            <label for="apodo">Usuario:</label>
                            <input type="text" name="apodo" id="apodo" required autofocus/>
                            <label for="clave">Contraseña:</label>
                            <input type="password" name="clave" id="clave" required/>
                            <input type="submit" value="Iniciar Sesión"/>
                            <input id="btn_registro" type="button" value="¿Quiere Registrarse?"/>
                        </form>
                    </section>
                </c:if>
                --%>
                <%--
                <div id="barra_info">
                    <p><c:out value="${mensaje}"/></p>
                    <c:if test="${!empty cliente}">
                        <h5>Bienvenido <c:out value="${cliente}"/> Su Rol Actual es: <c:out value="${rol}"/></h5>
                        <a href='<c:url value="/logout.umsa" />' target="_top" class="desconectar" width="89">Cerrar Sesi&oacute;n</a>
                    </c:if>
                </div>
                --%>
            </section>
            </div>
            
            <div id="mensaje-registro" title="¿Quiere Registrarse?">
                <p>
                    <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
                    Si desea consultar informacion al sistema, se le solicita hacer llegar una solicitud al DTIC-UMSA.
                </p>
            </div>
            <div id="mensaje-login_bad" title="Error">
                <p>
                    <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
                    Usuario o Contraseña incorrecta, también puede ser que su acceso haya caducado.
                </p>
            </div>
    </div>
    <%--
    <div id="capri_menu_inicio">
        <nav>
            <ul>
                
                <li>
                    <a href="<c:url value="/Busqueda_view.umsa?tipo=1"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Inicio</button></a>
                </li>
                <li>
                    <a href="<c:url value="/Busqueda_view.umsa?tipo=2"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Busqueda</button></a>
                </li>
                <li>
                    <a id="link_buscar_proveedores" href="<c:url value="/Busqueda_view.umsa?tipo=3"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Proveedores</button></a>
                </li>
                <li>
                    <a href="<c:url value="/Busqueda_view.umsa?tipo=4"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Normativa</button></a>
                </li>
                <li>
                    <a id="link_buscar_clasificador" href="<c:url value="/Busqueda_view.umsa?tipo=5"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Clasificadores</button></a>
                </li>
                <li>
                    <a href="<c:url value="/Busqueda_view.umsa?tipo=6"></c:url>" target="cuerpo_transaccion" ><button><span style="float:left; " class="ui-icon ui-icon-triangle-1-e"></span>Procedimientos</button></a>
                </li>
                <c:if test="${!empty rol}">
                    <li>
                        <a href="<c:url value="/Busqueda_view.umsa?tipo=7"></c:url>" target="cuerpo_transaccion" ><button id="btn_especial"><span style="float:left;" class="ui-icon ui-icon-triangle-1-e"></span>PROCESOS</button></a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
    --%>
    <nav id="menu2">
        <ul class="fancyNav">
            <li id="inicio"><a href="<c:url value="/Busqueda_view.umsa?tipo=1"></c:url>" target="cuerpo_transaccion">INICIO</a></li>
            <li id="busqueda"><a href="<c:url value="/BusquedaT.umsa"></c:url>" target="cuerpo_transaccion">Busqueda</a></li>
            <li id="adjudicados"><a href="<c:url value="/adjudicados.umsa"></c:url>" target="cuerpo_transaccion">Adjudicados</a></li>
            <li id="proveedores"><a href="<c:url value="/Proveedores.umsa"></c:url>" target="cuerpo_transaccion">Proveedores</a></li>
            <li id="normativa"><a href="<c:url value="/Normativa.umsa"></c:url>" target="cuerpo_transaccion">Normativa</a></li>
            <li id="clasificadores"><a href="<c:url value="/Clasificadores.umsa"></c:url>" target="cuerpo_transaccion">Clasificadores</a></li>
            <li id="procedimientos"><a href="<c:url value="/Busqueda_view.umsa?tipo=6"></c:url>" target="cuerpo_transaccion">Procedimientos</a></li>
            <c:if test="${!empty rol}">
                <li id="procesos"><a href="<c:url value="/Busqueda_view.umsa?tipo=7"></c:url>" target="cuerpo_transaccion">Procesos</a></li>
            </c:if>
            
        </ul>
    </nav>
                 
    
    <!--<iframe name="cuerpo_transaccion" id="cuerpo_transaccion" onload="autofitIframe(this);">-->
    <iframe  name="cuerpo_transaccion" id="cuerpo_transaccion"  src="/capri-web/Busqueda_view.umsa?tipo=1">
        
    </iframe>   
    <div id="dialog_iniciar_sesion" title="Login">
        <!--
        <form>
            <label>Ingrese Nombre de Usuario:</label>
            <input name="" type="text"/>
            <label>Ingrese su Contraseña: </label>
            <input name="" type="password"/>
        </form>
        -->
        <section id="capri_login">
            <form id="capri_form_login" name='forma' action='<c:url value="/buscarUsuario.umsa"/>' method='post'>
                <label for="apodo">Usuario:</label>
                <input type="text" name="apodo" id="apodo" required autofocus/>
                <label for="clave">Contraseña:</label>
                <input type="password" name="clave" id="clave" required/>
                <label for="gestion">Gestión:</label>
                <input type="text" placeholder="Ingrese Gestión" id="gestion" name="gestion" value="2015" required="true"/>
                <input type="submit" value="Iniciar Sesión"/>
                <!--<input id="btn_registro" type="button" value="¿Quiere Registrarse?"/>-->
            </form>
        </section>
    </div>
    </div>
    <!--<div class="clear"></div>-->
    </body>
</html>