<%-- 
    Document   : GOrdenCompra
    Created on : 21-10-2014, 09:29:05 AM
    Author     : UMSA-JES
--%>
<%--@ include file="../Superior.jsp" --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Generar Orden de Compra</title>
 
    <!-- CSS de Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="css/estilo_generador.css">
    <link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.10.4.custom.min.css">
    <!-- librerías opcionales que activan el soporte de HTML5 para IE8 -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="cuerpo_genera">
    <h1>Generación de Orden de Compra</h1>
    <table class="table table-striped">
    	<thead>
            <tr>
                <th>CANTIDAD</th>
    		<th>UNIDAD MEDIDA</th>
    		<th>TIPO ITEM</th>
    		
    		<th>ARTICULO</th>
    		<th>PRECIO UNITARIO</th>
    		<th>SUB TOTAL</th>
    	    </tr>
    	</thead>
    	<tbody>
            
            
                <c:forEach var="lista" items="${listaTransaccionArticulos.pageList}" varStatus="contador">
                    <c:if test="${lista.cod_complemento != 0 }">
                        <tr>
                            <td colspan="3"></td>
                            <td><c:out value="${lista.articulo}"/></td>
                            <td colspan="2"></td>
                        </tr>
                    </c:if>
                    <c:if test="${lista.cod_complemento == 0 }">
                        <tr class="success">
                            <td class="cantidad-item"><c:out value="${lista.cantidad}"/></td>
                            <td><c:out value="${lista.unidad_medida}"/></td>
                            <td><%--c:out value="${lista.tipo_item}"/--%></td>
                            <td><c:out value="${lista.articulo}"/></td>
                            <td><input data-cod_trans_detalle="${lista.cod_trans_detalle}" class="In-PrecioU" type="text" placeholder="Ingrese el Precio" value="${lista.precio_unit}"/></td>
                            <td class="subtotal-item"><c:out value="${lista.cantidad*lista.precio_unit}"/></td>
                        </tr>
                    </c:if>
                </c:forEach>
                        
    		<tr class="danger">
    			<td colspan="5">Total:</td>
    			<td id="total-item">0.0</td>
    		</tr>
    	</tbody>
    </table>
    
    <div class="datos-proveedor">
    	<form id="setProveedor" action='<c:url value="/setProveedor.umsa"/>' method='post'>
                <label>Nombre:</label>
                <input type="text" name="nombre" id="nombre_proveedor" value="<c:out value="${nombre}"/>">
    		<label>Casa Comercial:</label>
    		<input type="text" name="casa_comercial" id="casa_comercial_proveedor" value="<c:out value="${casa_comercial}"/>">
    		<label>Dirección:</label>
                <input type="text" name="direccion" id="direccion_proveedor" value="<c:out value="${direccion}"/>">
    		<label>Telefono - Fax:</label>
                <input type="text" name="telefono" id="tel_fax" value="<c:out value="${telefono}"/>">
    		<label>Nit:</label>
    		<input type="text" name="nit" id="nit_proveedor" value="<c:out value="${nit}"/>">
                <input hidden type="text" name="cod_transaccion" value="<c:out value="${cod_transaccion}"/>">
                <input type="submit" value="ACTUALIZAR DATOS PROVEEDOR">
                
            
    	</form>
        <button id="btn_buscar_proveedor">BUSCAR</button>
        <!--button id="btn_guardaProveedor">Guardar</button-->
    </div>
    <div class="dialog_buscaProveedor" title="Busca Proveedor Sigma">
        <div>
            <form id="form_busquedaProveedores" action='<c:url value="/GetProveedores.umsa"/>' method='post'>
                <label for="nit">NIT:</label>
                <input type="text" name="nit" id="nit"/>
                <label for="nombre">NOMBRE:</label>
                <input type="text" name="nombre" id="nombre">
                <label for="nombre_comercial">NOMBRE COMERCIAL:</label>
                <input type="text" name="nombre_comercial" id="nombre_comercial"/>
                <input type="text" name="cod_transaccion" hidden value="<c:out value="${cod_transaccion}"/>">
                <input type="text" name="cod_trans_nro" hidden value="<c:out value="${cod_trans_nro}"/>">
                <input type="submit" value="BUSCAR"/>
                <input type="reset"/>
            </form>
            <!--button>BUSCAR</button-->
        </div>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>DOCUMENTO</th>
                    <th>TIPO</th>
                    <th>CLASE</th>
                    <th>NOMBRE</th>
                    <th>CASA COMERCIAL</th>
                    <th>INFORMACIÓN</th>
                    <th>OPCIONES</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
                <div class="dialog_buscaDetalleProveedor" title="Detalle Proveedor">
                     
                </div>
    
    <div class="opciones_genera">
    	<!--button>Guardar</button-->
       <a target="_blank" href="reporteOrdenDescargo2?&amp;cod_tramite=1&amp;cod_estado=PPTO&amp;cod_transaccion=<c:out value="${cod_transaccion}"/>"><button>Orden de Compra</button></a>
       <!--a target="_blank" href="reporteOrdenBorrador?&amp;cod_tramite=1&amp;cod_estado=PPTO&amp;cod_transaccion=<c:out value="${cod_transaccion}"/>"><button>Orden de Compra Borrador</button></a-->
    </div>
    <div id="dialog_carga" title="Espere Por Favor"><img src="/capri-web/imagenes/cargando.gif" alt="Cargando....." height="150" width="auto"></div>
 
    <!-- Librería jQuery requerida por los plugins de JavaScript -->
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
 
    <!-- Todos los plugins JavaScript de Bootstrap (también puedes
         incluir archivos JavaScript individuales de los únicos
         plugins que utilices) -->
    <script src="js/Generador.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>