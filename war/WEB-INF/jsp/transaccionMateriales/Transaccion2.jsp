<%-- 
    Document   : Transaccion2
    Created on : 19-08-2014, 09:14:24 AM
    Author     : UMSA-JES
--%>
<%@ include file="../Superior.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
           $(document).on("ready",inicia);
           function inicia(){
              archivos_adjuntos();
              iniciaTabla();
           }
           function archivos_adjuntos(){
              
                $("#btn_archivo_adjunto").on("click",function(){
                   //alert("Hola :P");
                   if( ($("#archivo_adjunto").val()!=='') && ($("#descripcion_doc").val()!=='') && ($("#tipo_doc").val()!=='')){
                       //alert("Hola :P 2");
                       var x = $("#descripcion_doc").val();
                       var y = $("#tipo_doc").val();
                       $("#formu_prob").attr("action",'<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                <c:param name="cod_w" value="${cod_w}"/> 	      
                                <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                <c:param name="tipo_transaccion" value="solicitud_compras"/> 
                        </c:url>'+"&descripcion_doc="+x+"&tipo_doc="+y);
                       $("#formu_prob").submit();
                            
                   }
                   else
                   {
                       alert("Debe introducir información completa, concerniente al archivo adjunto");
                   }
                      
                });
           }
           function setBuscar(){
                var texto = document.getElementById('search');
                if (texto.value.length <2){
                    alert('Debes ingresar algun dato para buscar.');
                    return false;
                }
                else { 
                    document.getElementById('Buscar').value = 'Buscando...';
                    return true;

                }
            }

            function setRegistrar(f) {
                if(isNaN(parseInt(f.cantidad.value))|| parseInt(f.cantidad.value)==0)  {
                    alert('La cantidad debe ser un numero mayor a cero'); return false;
                }
                
                f.guardar.disabled = true;
                f.guardar.value = 'Enviando los datos...';
                /*si los datos no son correctos */
                return true;
            }
            /*=====================================================
             * ABRE UN POPUP INTERNO EN EL FORMULARIO PRINCIPAL
             =======================================================*/
            function openAddForm(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }
            
            var popup = null;
            var id_ia=null;
            function CantidadItem(id,unidad) {
                //alert(id+" "+unidad);
                id_ia=id;
                popup = openAddForm('popup','Colocar Cantidad',250, 30);
                document.getElementById('cod_item').value = id_ia;
                document.getElementById('unidad_medida').value = unidad;                
                return true;
            }
            /*====================================================================
             * COLOCA COLOR A LA FILA QUE SE ELIJE EN UNA TABLA
             ===================================================================*/
            function ini() {
              tab=document.getElementById('tabla');
              for (i=0; ele=tab.getElementsByTagName('td')[i]; i++) {	
                  if (i>2) {
                    ele.onmouseover = function() {iluminar(this,true)}
                    ele.onmouseout = function() {iluminar(this,false)} 
                  }
              }
            }

            function iluminar(obj,valor) {
              fila = obj.parentNode;
                for (i=0; ele = fila.getElementsByTagName('td')[i]; i++)                   
                  ele.style.background = (valor) ? '#c5d6e7' : 'white';              
            }
            
            
            
        </script>
    </head>
    <body>
        <header>
            
            <div class="box">
                <h2><a href="#" id="toggle-forms">Gestion: ${nro_gestion} -- Tipo Cuantia: ${cuantia}</a><!--button id="btn-poa" data-nro_gestion="${nro_gestion}" data-ue_solicitante="${ue_solicitante}">POA - Unidad Solicitante</button--><button id="btn-poa_padre" data-nro_gestion="${nro_gestion}" data-ue_solicitante="${ue_padre}">POA - UNIDAD</button></h2>
            </div>
            <div class="box" id="detalle_header">
                <div>
                    <p><span class="verde">Usuario Solicitante:</span> <span class="celeste"><input type="text" value="${usuario_sol}"/></span></p>
                    <p><span class="verde">Detalle Solicitud:</span> <span class="celeste"><input type="text" value="${detalle}"/></span></p>
                </div>
                <div>
                    <p><span class="verde">Unidad Solicitante:</span> <span class="celeste"><input type="text" value="${ue_solicitante}"/></span></p>
                    <p><span class="verde">Unidad Destino:</span> <span class="celeste"><input type="text" value="${ue_destino}"/></span></p>
                </div>
            </div>
        </header>
        
            <%--c:out value="${cod_w}"/--%>    
            <%--c:if test="${cod_w != 3 and cod_w != 4}"--%>
                <div id="cuerpo_detalle_solicitud" class="div_tabla_items">
                <table class="table table-striped" id="mainTable">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Cantidad</th>
                            <th>Unidad Medida</th>
                            <th>Articulo</th>
                            <th>Partida</th>
                            <th>Precio U.</th>
                            <th>Subtotal</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lista" items="${listaTransaccionArticulos.pageList}" varStatus="contador">
                            <tr data-cod_trans_detalle="<c:out value="${lista.cod_trans_detalle}"/>">
                                <td>${ listaTransaccionArticulos.getNrOfElements()-(contador.index)} </td>
                                <td class="cantidad"><input required type="number" min="1" max="9999999" size="5" value="<c:out value="${lista.cantidad}"/>"/></td>
                                <td class="unidad_medida">
                                    <select name="unidad_medida">
                                        
                                        <c:forEach var="lista2" items="${ListaUM.pageList}">
                                            <c:if test="${lista.cod_unidad_medida==lista2.cod_unidad_medida}">
                                                <option selected value="<c:out value="${lista2.cod_unidad_medida}"/>">
                                                    <c:out value="${lista2.detalle}"/>
                                                </option>
                                            </c:if>
                                            <c:if test="${lista.cod_unidad_medida!=lista2.cod_unidad_medida}">
                                                <option value="<c:out value="${lista2.cod_unidad_medida}"/>">
                                                    <c:out value="${lista2.detalle}"/>
                                                </option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="item">
                                    <input id="Input<c:out value="${lista.cod_trans_detalle}"/>" list="List<c:out value="${lista.cod_trans_detalle}"/>" required type="text" maxlength="55" size="65" value="<c:out value="${lista.detalle}"/>"/>
                                    <datalist class="dataList" id="List<c:out value="${lista.cod_trans_detalle}"/>">
                                        <!--option label="changos" value="changos"/-->
                                    </datalist>
                                </td>
                                <td id="Part<c:out value="${lista.cod_trans_detalle}"/>"><c:out value="${lista.partida}"/></td>
                                <td class="precio"><input size="10" type="text" placeholder="Ingrese precio" value="<c:out value="${lista.precio_unit}"/>"/></td>
                                <td class="subtotal"> -- </td>
                                <td><a class="delArticulo" href="#"><img class="img_peque" src="imagenes/minus.png"/></a><a class="detailItem" href="#"><img class="img_peque" src="imagenes/detail.png"/></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
                <div class="opciones_genera">
                    <c:if test="${cod_w != 3 and cod_w != 4}">
                        <a id="addArticulo" href="#" data-cod_transaccion="${cod_transaccion}" data-gestion="2015" ><img class="img_mediana" src="imagenes/plus.png" alt="add"/></a>
                        <a id="vistaPrevia" href="<c:url value="/reporteSolicitudBorrador"/>?&tipo_tramite=${1}&cod_transaccion=${cod_transaccion}&cod_estado=PPTO " target="_blank"><img class="img_mediana" src="imagenes/vista_previa.png" alt="Vista Previa"/></a>
                    </c:if>
                    <label class="label_total">TOTAL: <span id="total">0</span> Bs</label> 
                </div>
            <%--/c:if--%>
            <%--c:if test="${cod_w == 3 or cod_w == 4}">
                hola
            </c:if--%>
                
                
                <!--label>Superhéroe favorito</label>
                <input list="superheroes" name="list" />
                <datalist id="superheroes">
                    <option label="Iron Man" value="Iron Man">
                    <option label="Iron Girl" value="Iron Girl">
                    <option label="The Hulk" value="The Hulk">
                </datalist-->
                
                <!--input list="items" id="item">

                <datalist id="items">
                  <option value="A item"  data-xyz = "1" >
                  <option value="aa item" data-xyz = "2" >
                  <option value="C item"  data-xyz = "3" >
                  <option value="D item"  data-xyz = "4" >
                  <option value="E item"  data-xyz = "5" >
                </datalist--> 

                <!--input type="button" id="button" value="Get xyz" /-->

            
        
        <div id="cuerpo_archivos_adjuntos" class="div_tabla_adjuntos">
            <table>
                <thead>
                    <th>Archivos Adjuntos.</th>
                    <th>Descripción</th>
                    <th>Tipo</th>
                    <th>Opciones</th>
                </thead>
                <tbody>
                    <c:forEach var="lista" items="${listaDocs.pageList}" varStatus="contador">
                        <c:if test="${lista.tipo_item == null }">
                            <tr class="odd">
                                <td>
                                    <a target="_blank" href="<c:url value="../prueba/${lista.terminos_ref}" ></c:url>" style="color:green">${lista.terminos_ref}</a> 
                                </td>
                                <td>
                                    <p>${lista.descripcion}</p>
                                </td>
                                <td>
                                    <p>${lista.tipo_doc}</p>
                                </td>
                                <c:if test="${lista.cod_docs != null }">
                                    <td>
                                        <a href="<c:url value="/TransaccionEliminaDoc.umsa">
                                           <c:param name="cod_tramite" value="${cod_tramite}"/>
                                           <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                           <c:param name="usuario_sol" value="${usuario_sol}"/>
                                           <c:param name="detalle" value="${detalle}"/>
                                           <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                           <c:param name="ue_destino" value="${ue_destino}"/>  
                                           <c:param name="nro_gestion" value="${nro_gestion}"/>
                                           <c:param name="cod_w" value="${cod_w}"/>
                                           <c:param name="cod_docs" value="${lista.cod_docs}"/>
                                           <c:param name="cod_transaccion" value="${cod_transaccion}"/>                                               
                                        </c:url>" onclick="javascript:return confirm('¿Esta seguro(a) que desea eliminar el registro?')" style="color:green">Eliminar</a>  
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${!empty listaTransaccionArticulos.pageList}">
                            <tr>
                                <td colspan="6">
                                   
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
                
                
            <div>
               
                <p><span style="color:red"><c:out value="${error}"/></span></p>
                
                
                <table>
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>Detalle</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="listaAdjuntos" items="${listaAdjuntos.pageList}" varStatus="contador">
                                     
                            <form action='/capri-web/TransaccionConsultorAdjTerminos.umsa?cod_tramite=${cod_tramite}&cod_w=${cod_w}&cod_transaccion=${cod_transaccion}&tipo_tramite=${tipo_tramite}&tipo_doc=${listaAdjuntos.cod_adjunto}&tipo_transaccion="solicitud_compras"' method="POST" enctype="multipart/form-data">
                                <tr>
                                    <td>
                                        <label><c:out value="${listaAdjuntos.descripcion}"/></label>
                                        <!--select>
                                            <option>Terminos de Referencia</option>
                                            <option>Formulario c-1</option>
                                        </select-->
                                    </td>
                                    <td><input type="file" name="archivo" size="60" id="archivo_adjunto"/></td>
                                    <td> <input type="submit" value="ADD"/></td>
                                </tr>
                            </form>

                               
                        </c:forEach>
                    </tbody>
                
                    
                </table>
            </div>
                
            <%--div class="box">
                
                <h2>
                    <a href="#" id="toggle-forms">Form. de Articulos</a>
                </h2>   
                
                <form id="formu_prob" name='forma' action='<c:url value="/TransaccionDetalleItems.umsa"/>' method='post' enctype="">
                    <input name="usuario_sol" type="hidden" value="<c:out value="${usuario_sol}"/>"/>
                    <input type="hidden" name="detalle" value="${detalle}" />
                    <input name="ue_solicitante" type="hidden" value="<c:out value="${ue_solicitante}"/>" />
                    <input name="ue_destino" type="hidden" value="<c:out value="${ue_destino}"/>" />
                    <input type=hidden name="cod_transaccion" value="<c:out value="${cod_transaccion}"/>" >
                    <input type=hidden name=tipo_tramite value="<c:out value="${tipo_tramite}"/>" >
                    <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >
                    <input type=hidden name=cod_w value="<c:out value="${cod_w}"/>" >
                    <input type=hidden name=nro_gestion value="<c:out value="${nro_gestion}"/>" >
                    <input type=hidden name=cuantia value="<c:out value="${cuantia}"/>" >
                    
                     Buscar Item : <input type="text" name="search" value="${search}" id="search">
                     <input type="submit" name="opcion" value="Buscar" id="Buscar" onclick="return setBuscar()" >
                </form>
                
                <button id="busqueda_avanzada_items">Busqueda Avanzada</button>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="gridContent">
                            <div id="scrolltable" style=" overflow:auto; padding-top:5px; padding-bottom: 15px; height:100px; top:20px" >
                                <table id="tabla">
                                    <thead>
                                        <tr>
                                            <td class="gridHeaders">PARTIDA</td>
                                            <td class="gridHeaders">TIPO ITEM</td>
                                            <td class="gridHeaders">ITEM</td>
                                            <td class="gridHeaders">UNID.MEDIDA</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${empty items}">
                                            <tr>
                                                <td colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>                                        
                                        <c:forEach var="item" items="${items}">                                       
                                            <tr onclick="javascript:return CantidadItem('${item.cod_item}','${item.unidad_medida}')" style="cursor:pointer"  >
                                                <td class="gridData">${item.partida}</td>
                                                <td class="gridData">${item.tipo_item}</td>
                                                <td class="gridData">${item.articulo}</td>
                                                <td class="gridData">${item.unidad_medida}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>                                                          
                            </div>
                            
                            <c:if test="${cod_tramite==1}">                            
                                <!--input type="button" value="Terminar" onclick="history.back()" style="font-family: Verdana; font-size: 10 pt"-->
                                <a href="/capri-web/TransaccionMateriales.umsa?cod_tramite=1&cod_w=${cod_w}"><button>Terminar</button></a>
                            </c:if>
                            <c:if test="${cod_tramite==4}">                            
                                <!--input type="button" value="Terminar" onclick="history.back()" style="font-family: Verdana; font-size: 10 pt"-->
                                <a href="/capri-web/TransaccionMateriales.umsa?cod_tramite=1&cod_w=${cod_w}"><button>Terminar</button></a>
                            </c:if>
                        </td>
                    </tr>
                </table>            
            </div--%>
                <c:if test="${cod_tramite==1}">                            
                                <!--input type="button" value="Terminar" onclick="history.back()" style="font-family: Verdana; font-size: 10 pt"-->
                                <a href="/capri-web/TransaccionMateriales.umsa?cod_tramite=1&cod_w=${cod_w}"><button>Terminar</button></a>
                            </c:if>
                            <c:if test="${cod_tramite==4}">                            
                                <!--input type="button" value="Terminar" onclick="history.back()" style="font-family: Verdana; font-size: 10 pt"-->
                                <a href="/capri-web/TransaccionPedidos.umsa?cod_tramite=4&cod_w=${cod_w}"><button>Terminar</button></a>
                            </c:if>
            <div id="dialog_poa" title="PRESUPUESTO">
                <table>
                    <thead>
                        <th>PARTIDA</th>
                        <th>DESCRIPCCION</th>
                        <th>FTE</th>
                        <th>ORG</th>
                        <th>PRESUPUESTO</th>
                        <th>EJECUTADO</th>
                        <th>SALDO</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="sample_popup"     id="popup" style="display: none;">
                <div class="menu_form_body">
                    <form action='<c:url value="/TransaccionArticuloGuarda.umsa"/>' method='post' name='forma1' onsubmit="return setRegistrar(this)">
                        <table>
                            <tr>
                                <td>
                                    Cantidad :
                                </td>
                                <td>
                                    <input class="field" type="text" onfocus="select();" name="cantidad"/>
                                    <input type=hidden id="cod_item" name="cod_item">
                                    <input type=hidden id="unidad_medida" name="unidad_medida">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" >
                                    <center>                                                                
                                        <input type=hidden name=tipo_tramite value="<c:out value="${tipo_tramite}"/>" >
                                        <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >
                                        <input type=hidden name=usuario_sol  value="<c:out value="${usuario_sol}"/>"/>
                                        <input type=hidden name=detalle  value="<c:out value="${detalle}"/>"/>
                                        <input type=hidden name=ue_solicitante value="<c:out value="${ue_solicitante}"/>" />
                                        <input type=hidden name=ue_destino value="<c:out value="${ue_destino}"/>" />
                                        <input type=hidden name=nro_gestion value="<c:out value="${nro_gestion}"/>" />
                                        <input type=hidden name=cod_transaccion value="<c:out value="${cod_transaccion}"/>" >
                                        <input type=hidden name=cod_w value="<c:out value="${cod_w}"/>" >
                                    <input type=submit value="Aceptar" >
                                    </center>                             
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="buscador_itemsAvanzado" class="ui-dialog-content ui-widget-content" title="Busqueda Avanzada de Articulos">
                <div id="header_buscadorAvanzado">
                    <div id="busca_partida">
                        <select name="thelist" id="combo_partidas">
                            <option value="0">Escoja una Partida</option>
                            <c:forEach var="lista" items="${lista_partidas.pageList}" varStatus="contador">
                                 <option value = <c:out value="${lista.partida}"/>><c:out value="${lista.detalle_partida}"/></option>
                             </c:forEach>
                        </select>
                        
                    </div>
                    <div id="descripcion_partida" >
                        <h3>Descripción de la Partida</h3>
                        <p>
                            Aqui va la descripción de la partida
                            
                        </p>
                    </div>
                </div>
		
                <div id="cuerpo_buscadorAvanzado">
                    <table>
                        <thead>
                            <tr>
                                <td>ITEM</td>
                                <td>DETALLE</td>
                                <td>UNIDAD DE MEDIDA</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="dialog_carga" title="Espere Por Favor"><img src="/capri-web/imagenes/cargando.gif" alt="Cargando....." height="150" width="auto"></div>
            <div id="dialog_no-item" title="¿Qué hago si no encuentro el item?"><img src="/capri-web/imagenes/no-item.jpg" height="370" width="auto"/></div>
            <div id="dialog_detalle_item" title="Detalle adicional">
                 <!--textarea rows="4" cols="50">At w3schools.com you will learn how to make a website.</textarea-->
                 <table>
                     <thead>
                         <tr>
                             <th>Complemento</th>
                             <th>Opciones</th>
                         </tr>
                     </thead>
                     <tbody>
                         
                     </tbody>
                 </table>
                 <!--a id="addDetalle" href="#" ><img class="img_peque" src="imagenes/plus.png" alt="addDetalle"/></a-->
            </div>
            <div id="dialog_unidad_medida" title="Unidad de Medida">
                <input id="val_unidad_medida" data-cod_unidad_medida="" type="text" size="40" maxlength="40" placeholder="Ingrese descripción unidad de medida"/>
            </div>
            <script src="js/tablaDinamica.js"></script>
    </body>
</html>
