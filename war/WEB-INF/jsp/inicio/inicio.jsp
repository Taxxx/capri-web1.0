<%-- 
    Document   : inicio
    Created on : 20-03-2014, 04:28:58 PM
    Author     : UMSA-JES
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/main_style.css"/>
        <link rel="stylesheet" href="css/jquery.bxslider.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.bxslider.min.js"></script>
        <script src="js/inicio.js"></script>
    </head>
    <body>
        <header>
            <div id="slider_inicio">
                <ul class="bxslider">
                    <li><img class="slider_img" src="/capri-web/sliderimg/banner.jpg"/></li>
                    <li><img class="slider_img" src="/capri-web/sliderimg/slideruno.png"/></li>
                    <li><img class="slider_img" src="/capri-web/sliderimg/sliderdos.png"/></li>
                    <!--<li><img class="slider_img" src="/capri-web/sliderimg/article.png"/></li>-->
                </ul>
            </div>
        </header>
        <div id="cuerpo_presentacion">
            <section id="columna_izq" class="columnas_inicio">
                <article>
                    <h3>Procedimientos</h3>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/inicio_proc.png"/>
                        Ponemos a su disposición la información, que le permitirán comprender los procedimientos de Compra de la Universidad.<p><a class="links" href="/capri-web/Busqueda_view.umsa?tipo=6">>> CLICK AQUÍ</a></p>
                    </p>
                </article>
                <article>
                    <h3>Centros UMSA-COMPRA</h3>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/acreditacion.jpg"/>
                        Verificamos que quienes intervienen en las adquisiciones, cuenten con los conocimientos y habilidades para ello.<p><a class="links" href="/capri-web/Busqueda_view.umsa?tipo=8">>> CLICK AQUÍ</a></p>
                    </p>
                </article>
            </section>
            <section id="columna_centro" class="columnas_inicio">
                <h3>Compradores</h3>
                <article>
                    <h4><a href="<c:url value="/Busqueda_view.umsa?tipo=4"></c:url>">Normativa</a></h4>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/normativa.jpg"/>
                        Ponemos a su disposición la información, que le permitirán comprender la normativa de Compra de la Universidad.
                    </p>
                </article>
                <article>
                    <h4><a href="<c:url value="/Qsomos.umsa"></c:url>">¿Quienes somos?</a></h4>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/capacitacion.jpg"/>
                        Apoyamos a nuestros usuarios para aprovechar las oportunidades de negocios con la Universidad.
                    </p>
                </article>
            </section>
            <section id="columna_derecha" class="columnas_inicio">
                <h3>Proveedores</h3>
                <article>
                    <h4><a href="<c:url value="/Busqueda_view.umsa?tipo=9"></c:url>">¿Cómo venderle a la UMSA?</a></h4>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/como_ofertar.jpg"/>
                        Infórmese cómo puede conocer y participar de las oportunidades de negocios con la Universidad.
                    </p>
                </article>
                <article>
                    <h4><a href="#">¿Qué compra la Universidad?</a></h4>
                    <p>
                        <img class="img_logo" width="15%" src="/capri-web/imagenes/que_compran.jpg"/>
                        Vea en .... el detalle de sus compras según rubros, unidades y tipos de cuantias.
                    </p>
                </article>
            </section>
        </div>
        <footer>
            <div class="div_footer">
                <img class="img_logo" width="30%" src="/capri-web/imagenes/dtic_logo.png"/>
                <!--<span>LA PAZ - BOLIVIA (2014)</span>-->
            </div>
            <div class="div_footer">
                <h5>División de Adquisiciones U.M.S.A.</h5>
                <p><span>Av. 6 de Agosto No. 2170 Piso 8</span></p>
                <p><span>Teléfono: 2442899</span></p>
                <p><span>E-mail: adquisiciones@umsa.bo</span></p>
            </div>
            <!--
            <div class="div_footer">
                <img class="img_logoX" height="70px" width="auto" src="/capri-web/imagenes/logo-ok.png"/>
            </div>
            -->
        </footer>
    </body>
</html>
