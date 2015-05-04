<%@ include file="../Superior.jsp" %>

<html>
    <body>
    <form action="">
        <div class="container_3">
    <div class="grid_4">
        <div class="box menu">
            <h2>
                <a href="#" id="toggle-section-menu">Section Menu</a>
            </h2>
            <div class="block" id="section-menu">
                <ul class="section menu">                    
                    <li>
                        <a class="menuitem">Transacciones</a>
                        <ul class="submenu">
                            <li>                                                                
                                <a href="<c:url value="/TransaccionMateriales.umsa">
                                                    <c:param name="cod_tramite" value="1"/>
                                                    <c:param name="cod_w" value="1"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/cart.png"/> Solicitud de Compras</a>
                            </li>
                            <li>
                                <a href="<c:url value="/TransaccionPedidos.umsa">
                                                    <c:param name="cod_tramite" value="4"/>
                                                    <c:param name="cod_w" value="2"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/lorry.png"/> Pedido de materiales</a>
                            </li> 
                            <li>
                                <a href="<c:url value="/TransaccionConsultoriasBandeja.umsa">
                                                    <c:param name="cod_tramite" value="1"/>
                                                    <c:param name="cod_w" value="3"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/consultor.png"/> Consultores</a>   
                            </li>
                            <li>
                                <a href="<c:url value="/TransaccionObrasBandeja.umsa">
                                                    <c:param name="cod_tramite" value="1"/>
                                                    <c:param name="cod_w" value="4"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/house.png"/> Obras</a>   
                            </li>
                            <li>
                                <a href="<c:url value="/TransaccionListaSolicitudes.umsa">
                                                    <c:param name="cod_tramite" value="1"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/ordenes.png"/> Lista Sol./Ordenes de Compra</a>   
                            </li>                                                                                                    
                            <li>
                                <a href="<c:url value="/TransaccionPedidosLista.umsa">
                                                    <c:param name="cod_tramite" value="4"/>
                                                </c:url>" target="cuerpo" >
                                         <img src="../img/pedidos.gif"/> Lista de Pedido de Materiales</a>   
                            </li>                                                      
                        </ul>
                    </li>  
                    <li>
                        <a class="menuitem">Sistema</a>
                        <ul class="submenu">
                            <li>
                                <a><img src="../img/key.png"/>&nbsp;&nbsp;Cambiar password</a>
                            </li>                            
                        </ul>
                    </li>
                </ul>
            </div>
        </div>        
    </div>
        </div>
    </form>
</body>
</html>