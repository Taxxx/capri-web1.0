<%@ include file="../Superior.jsp" %>
<%-- 
    Document   : PedidoDetalle
    Created on : 15-nov-2010, 10:34:31
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            
            $(document).on("ready",inicia);
            function botones(){
            $("#btn-poa").on("click",function(){
                var gestion = "<c:out value="${nro_gestion}"/>";
                var destino = "<c:out value="${ue_destino}"/>";
                //alert(gestion+" --hulala-- "+destino);
                
                //alert("Changos Charangos");
                //$("#dialog_poa").dialog( "open" );

                $.ajax({
                    type: 'post',
                    url: '/capri-web/GetPOA.umsa',
                    data:{destino: destino, gestion: gestion},
                    dataType: 'json',
                    error: function(xhr, ajaxOptions, thrownError) {
                        alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
                    },
                    success: function(json) {
                        //alert("Exito");
                        var x=$("#dialog_poa table tbody");
                        x.empty();
                        $.each(json.POA, function(index, item){
                            x.append("<tr><td>"+item.OBJETO_DEL_GASTO+"</td><td>"+item.DESC_OBJETO_DEL_GASTO+"</td><td>"+item.FUENTE+"</td><td>"+item.ORGANISMO+"</td><td>"+item.MODIFICACIONES_PRESUPUESTALES+"</td><td>"+item.DEVENGADO+"</td><td>"+item.SALDO_POR_PREVENIR+"</td></tr>");
                        });
                        $("#dialog_poa").dialog( "open" );
                    }
                });
            });
            }
            function inicia(){
                botones();   
                $("#dialog_poa").dialog({
                    autoOpen: false,
                    width:700,
                    height:'auto',
                    modal: true,
                    buttons: {
                        "Ok": function() {
                            $( this ).dialog("close");
                        }
                    }
                });
                //alert("Buenisimo");
                $("#pres").on("click",function(){
                    //alert("Changos");
                    //alert($("#formu_prob").serialize());
                    if( ($("#archivo_adjunto").val()!='') && ($("#descripcion_doc").val()!='') && ($("#tipo_doc").val()!='')){
                        //alert("Vacio");
                        $("#formu_prob").submit(function(){
                             //alert($("#formu_prob").attr("enctype"));
                        
                            var x = $("#descripcion_doc").val();
                            var y = $("#tipo_doc").val();
                            //alert("El valor de x --> "+x);
                            //$("#formu_prob").attr("action","/capri-web/TransaccionConsultorAdjTerminos.umsa"+$("#formu_prob").serialize());
                           <%-- 
                           alert('<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                    <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                    <c:param name="cod_w" value="${cod_w}"/> 	      
                                    <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                    <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                    <c:param name="tipo_transaccion" value="solicitud_compras"/> 
                            </c:url>'+"&descripcion_doc="+x);
                           --%>

                            $("#formu_prob").attr("action",'<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                    <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                    <c:param name="cod_w" value="${cod_w}"/> 	      
                                    <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                    <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                    <c:param name="tipo_transaccion" value="consultores"/> 
                            </c:url>'+"&descripcion_doc="+x+"&tipo_doc="+y);

                            $("#formu_prob").attr("enctype","multipart/form-data");
                            //alert($("#formu_prob").attr("enctype"));
                            //$.post("/capri-web/TransaccionConsultorAdjTerminos.umsa", $("#formu_prob").serialize());
                        });
                    }
                    else{
                        alert("Debe Seleccionar un Archivo, Su descripcion y Ademas definir un tipo");
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
            
            function selectPersona(id) {
                window.location='<c:url value="/registrarDocente.do"/>?id_persona='+id;
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
    <body onload="ini()">
        <button id="btn-poa">POA</button>
    <form name='forma' action='<c:url value="/TransaccionConsultoresDetalle.umsa"/>' id="formu_prob" method='post'>
    <%--<form method='post' name="forma" enctype='multipart/form-data'>--%>    
    <div class="container_cuerpo">
        <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">Datos Generales ${tipo_tramite} Nro.: <c:out value="${nro_gestion}"/> -- TIPO CUANTIA :: <c:out value="${cuantia}"/></a>
            </h2>
            <div class="block" id="login-forms">
                <fieldset class="mostrar">                        
                    <p>
                        <label>Usuario solicitante: </label>
                        <input name="usuario_sol" type="text" value="<c:out value="${usuario_sol}"/>" disabled/>
                        <input name="usuario_sol" type="hidden" value="<c:out value="${usuario_sol}"/>"/>
                    </p>
                    <p>
                        <label>Detalle Solicitud: </label>
                        <input type="text" name="detalle" value="${detalle}" disabled/>
                        <input type="hidden" name="detalle" value="${detalle}" />
                    </p>
                    <p>
                        <label>Unidad Solicitante </label>
                        <input name="ue_solicitante" type="text" value="<c:out value="${ue_solicitante}"/>" disabled/>
                        <input name="ue_solicitante" type="hidden" value="<c:out value="${ue_solicitante}"/>" />
                    </p>
                    <p>
                        <label>Unidad Destino: </label>
                        <input name="ue_destino" type="text" value="<c:out value="${ue_destino}"/>" disabled/>
                        <input name="ue_destino" type="hidden" value="<c:out value="${ue_destino}"/>" />
                    </p>
                </fieldset>                        
                <input type=hidden name=cod_transaccion value="<c:out value="${cod_transaccion}"/>" >
                <input type=hidden name=tipo_tramite value="<c:out value="${tipo_tramite}"/>" >
                <input type=hidden name=cod_tramite value="<c:out value="${cod_tramite}"/>" >
                <input type=hidden name=cod_w value="<c:out value="${cod_w}"/>" >
            </div>
        </div>
        <div class="box">
            <h2>
                <a href="#" id="toggle-tables">Detalle ${tipo_tramite}</a>
            </h2>
            <div class="block" id="tables">
                <table>                    
                    <thead>
                        <tr>                            
                            <th>Tipo Item</th>
                            <th>Articulo</th>
                            <th>Unidad Medida</th>
                            <th>Cant.Pedida</th>                   
                                               
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lista" items="${listaTransaccionArticulos.pageList}" varStatus="contador">                            
                                <c:if test="${lista.tipo_item == null }">
                                    <tr class="odd">
                                    <td></td>
                                    <td><c:out value="${lista.articulo}"/></td>
                                    <td></t</td>
                                    <td></t</td>
                                    <%--<td>
                                        <a href="<c:url value="./docs/${lista.terminos_ref}" ></c:url>" style="color:green">${lista.terminos_ref}</a> 
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
                                        </c:if> --%>
                                    </tr>
                                </c:if>
                                <c:if test="${lista.tipo_item != null }">
                                    <tr>
                                        <td><c:out value="${lista.tipo_item}"/></td>
                                        <td><a href="<c:url value="/TransaccionArticuloComplemento.umsa">                                               
                                                   <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                                   <c:param name="usuario_sol" value="${usuario_sol}"/>
                                                   <c:param name="detalle" value="${detalle}"/>
                                                   <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                                   <c:param name="ue_destino" value="${ue_destino}"/>
                                                   <c:param name="articulo" value="${lista.articulo}"/>                                               
                                                   <c:param name="nro_gestion" value="${nro_gestion}"/>                                               
                                                   <c:param name="cod_trans_detalle" value="${lista.cod_trans_detalle}"/>
                                                   <c:param name="cod_transaccion" value="${cod_transaccion}"/>
                                                   <c:param name="cod_tramite" value="${cod_tramite}"/>
                                                   <c:param name="cod_w" value="${cod_w}"/>
                                                   </c:url>">
                                            <c:out value="${lista.articulo}"/></a></td>
                                        <td><c:out value="${lista.unidad_medida}"/></td>
                                        <td><c:out value="${lista.cantidad}"/> </td>                                       
                                        <%--<td><a href="<c:url value="./docs/${lista.terminos_ref}"></c:url>" >${lista.terminos_ref}</a> </td>--%>
                                        <td>
                                            <a href="<c:url value="/TransaccionArticuloMostrarElimina.umsa">
                                                   <c:param name="cod_tramite" value="${cod_tramite}"/>
                                                   <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                                   <c:param name="usuario_sol" value="${usuario_sol}"/>
                                                   <c:param name="detalle" value="${detalle}"/>
                                                   <c:param name="ue_solicitante" value="${ue_solicitante}"/>
                                                   <c:param name="ue_destino" value="${ue_destino}"/>
                                                   <c:param name="tipo_item" value="${lista.tipo_item}"/>
                                                   <c:param name="articulo" value="${lista.articulo}"/>
                                                   <c:param name="unidad_medida" value="${lista.unidad_medida}"/>
                                                   <c:param name="cantidad_pedido" value="${lista.cantidad_pedido}"/>
                                                   <c:param name="nro_gestion" value="${nro_gestion}"/>
                                                   <c:param name="cod_w" value="${cod_w}"/>                                               
                                                   <c:param name="cod_trans_detalle" value="${lista.cod_trans_detalle}"/>
                                                   <c:param name="cod_transaccion" value="${cod_transaccion}"/>                                               
                                               </c:url>" >Eliminar</a>  
                                        </td>
                                    </tr>                                    
                                </c:if>                            
                        </c:forEach> 
                        <%--<c:if test="${!empty listaTransaccionArticulos.pageList}">
                            <tr>
                                <td colspan="6"><p>                   
                                <label>Terminos Referencia: </label>
                                <input type="file" name="archivo" size="60"/>
                                <input type="submit" value="Adjuntar" 
                                onclick="document.forma.action='<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                <c:param name="cod_w" value="${cod_w}"/> 	      
                                <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                </c:url>'">
                                </p></td>
                            </tr>
                        </c:if>--%>
                         
                    </tbody>                            
                </table>
                <table>
                    <!-- -->
                    <thead>
                        <th>Terminos Ref.</th>
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
                                    <td><p>${lista.tipo_doc}</p></td>
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
                        <%--
                            <tr>
                                <td colspan="6"><p>                   
                                <label style="font-size: 1.3em;color:#103767;">Terminos Referencia: </label>
                                <input type="file" name="archivo" size="60"/>
                                <label style="font-size: 1.3em;color:#103767;" for="descripcion_doc">Descripcion:</label>
                                <input type="text" style="width: 350px;" name="descripcion_doc" id="descripcion_doc"/>
                                <input type="submit" value="Adjuntar" 
                                onclick="document.forma.action='<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                <c:param name="cod_w" value="${cod_w}"/> 	      
                                <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                <c:param name="tipo_transaccion" value="consultores"/>
                                </c:url>'"/>
                                <span style="color:red"><c:out value="${error}"/></span>
                                </p></td>
                            </tr>
                        --%>
                            <tr>
                                <td colspan="6"><p>                   
                                <label style="font-size: 1.3em;color:#103767;">Arch. Adjuntos: </label>
                                <input type="file" name="archivo" size="60" id="archivo_adjunto"/>
                                <label style="font-size: 1.3em;color:#103767;" for="descripcion_doc">Descripcion:</label>
                                <input type="text" style="width: 150px;" name="descripcion_doc" id="descripcion_doc"/>
                                <button id="pres">Adjuntar</button>
                                <label>Tipo de Documento: </label>
                                <!--
                                <select name="tipo_doc" id="tipo_doc">
                                    <option value="">Ninguno</option>
                                    <option value="FSC">Formulario Solicitud de Compra</option>
                                    <option value="CR">Cotización Referencial</option>
                                    <option value="NS">Nota de Solicitud</option>
                                    <option value="HR">Hoja de Ruta</option>
                                    <option value="C">Cotización</option>
                                    <option value="F">Factura</option>
                                    <option value="NC">Nota de Conformidad</option>
                                    <option value="NSP">Nota de Solicitud de Pago</option>
                                    <option value="O">Otro</option>
                                </select>
                                -->
                                <select name="tipo_doc" id="tipo_doc">
                                    <option value="">Ninguno</option>
                                    <c:forEach var="lista" items="${listaTipoAdj.pageList}" varStatus="contador">
                                        <option value = <c:out value="${lista.cod_adjunto}"/>><c:out value="${lista.descripcion}"/></option>
                                    </c:forEach>
                                </select>
                                </p>
                                <p><span style="color:red"><c:out value="${error}"/></span></p></td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
                
                <!-- -->
            </div>
            
        </div>
    </div>    
    <div class="grid_16">
        <div class="box">
            <h2>
                <a href="#" id="toggle-forms">Form. de Articulos</a>
            </h2>            
            
            <c:if test="${listaTransaccionArticulos == null }">
                Buscar Item : <input type="text" name="search" value="${search}" id="search"><input type="submit" name="opcion" value="Buscar" id="Buscar" onclick="return setBuscar()" >
            </c:if>
            
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="gridContent">
                        <div id="scrolltable" style=" overflow:auto;
                             padding-top:5px; padding-bottom: 15px;                                                        
                             height:100px; top:20px" >                                
                            <table id="tabla">
                                <thead>
                                    <tr>
                                    <td class="gridHeaders">TIPO ITEM</td>
                                    <td class="gridHeaders">ITEM</td>
                                    <td class="gridHeaders">UNID.MEDIDA</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty items}">
                                    <tr>
                                        <td colspan="3">No se han encontrado elementos.</td>
                                    </tr>
                                </c:if>                                        
                                <c:forEach var="item" items="${items}">                                       
                                    <tr onclick="javascript:return CantidadItem('${item.cod_item}','${item.unidad_medida}')" style="cursor:pointer"  >
                                        <td class="gridData">${item.tipo_item}</td>
                                        <td class="gridData">${item.articulo}</td>
                                        <td class="gridData">${item.unidad_medida}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>                                                          
                        </div>                        
                        <input type=submit name="BtnTerminar" value="Terminar Solicitud" onclick="document.forma.action='<c:url value="/TransaccionConsultoriasBandeja.umsa"><c:param name="cod_w" value="${cod_w}"/><c:param name="cod_tramite" value="${cod_tramite}"/> </c:url>'" >                                                                       
                    </td>
                </tr>
            </table>            
        </div>
    </div>    
    </div>
    </form>
                    
      <%-- NUEVO UPLOAD --%>
      <%--
      <div id="documentos_adjuntos" class="box">
                <table>
                    <thead>
                        <th>Terminos Ref.</th>                   
                        <th>Opciones</th>
                    </thead>
                    <tbody>
                        <c:forEach var="lista" items="${listaTransaccionArticulos.pageList}" varStatus="contador">
                        <tr>
                            <td>Prueba.pdf</td>
                            <td>Eliminar - Guardar</td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <form  method="post" action='<c:url value="/TransaccionConsultorAdjTerminos.umsa"/>' enctype="multipart/form-data">
                                    <label>Seleccione Archivo</label>
                                    <input id="path_file_adj_consultor" type="file" name="uploadFile"/>
                                    <input type="text" name="cod_tramite" value="${cod_tramite}"/>
                                    <input type="hidden" name="cod_w" value="${cod_w}"/>
                                    <input type="hidden" name="cod_transaccion" value="${cod_transaccion}"/>
                                    <input type="hidden" name="tipo_tramite" value="${tipo_tramite}"/>
                                    <input id="btn_upload_referencia" type="submit" value="Adjuntar" />
                                </form>
                            
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>--%>
   <%-- <center>
            <form method="post" action='<c:url value="/TransaccionConsultorAdjTerminos.umsa">
                                <c:param name="cod_tramite" value="${cod_tramite}"/>                            
                                <c:param name="cod_w" value="${cod_w}"/> 	      
                                <c:param name="cod_transaccion" value="${cod_transaccion}"/> 
                                <c:param name="tipo_tramite" value="${tipo_tramite}"/>
                                </c:url>' enctype="multipart/form-data">
                    Select file to upload: 
                    <input type="file" name="uploadFile" /> 
                    <br/><br/> 
                    <input type="submit" value="Upload" />
            </form>
    </center> --%>          
<%-- ============================= --%>


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
    <div id="dialog_poa" title="POA UNIDAD">
        <table>
            <thead>
                <th>PARTIDA</th>
                <th>DESCRIPCCION</th>
                <th>FTE</th>
                <th>ORG</th>
                <!--
                <th>PPTO_INICIAL</th>
                <th>TRASP</th>
                -->
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
    </body>
</html>
