$(document).on("ready",inicia);
function inicia(){
    $("#cuerpo_transaccion").css("height","600px");
    //alert(":D");
//    $( "#fecha_publicacion" ).datepicker({
//        dateFormat: 'yy',
//        changeDay: false,
//        changeMonth: false,
//        changeYear: true,
//        showButtonPanel: true,
//        setDate: new Date(2008,9,03)
//    });
//    $( "#fecha_presentacion" ).datepicker();
    
    var Limite_paginacion=50;
    inicia_dialogs();
    controla_busquedas(Limite_paginacion);
    controla_carga_ajax();
    controla_partidas();
}
function autofitIframe(){
    var id = $("#cuerpo_transaccion");
    if (!window.opera && document.all && document.getElementById){
        id.style.height=id.contentWindow.document.body.scrollHeight;
    } else if(document.getElementById) {
        id.style.height=id.contentDocument.body.scrollHeight+"px";
    }
    //alert(":D");
}
function controla_partidas(){
    if($(document).attr('title')=="BusquedaItems"){
        //alert("o item");
        $("#select_gestiones").on("change",function(){
        ajax_genera_partidas($(this).val());
    });
    }/*
    if($(document).attr('title')=="BusquedaAdjudicados"){
        //alert("o item");
        $("#select_partidas").on("change",function(){
        ajax_genera_partidas_totales($(this).val());
    });
    }*/
}
function genera_partidas(json){
    var x=$("#select_partidas");
    x.html("<option value=''>Ninguna</option>");
    $.each(json.Partidas, function(index, item){
        x.append("<option value="+item.partida+">"+item.detalle_partida+"</option>");
    });
    //alert("yeih: "+gestion);
}
function ajax_genera_partidas(gestion){
    $.ajax({
        type: 'post',
        url: '/capri-web/GetPartidas.umsa',
        data:{gestion:gestion},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            genera_partidas(json);
        }
    });
}



function ajax_genera_partidas_totales(partidas){
    //alert(partidas);
    /*$.ajax({
        
        /*type: 'post',
        url: '/capri-web/GetPartidasTotales.umsa',
        data:{gestion:gestion},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            genera_partidas(json);
        }
    });*/
}

/******************************/
function get_min(c, l){return (c*l)+1;}
function get_max(c, l){return (c+1)*l;}
function controla_carga_ajax(){
    $(document).ajaxStart(function(){
        $('#dialog_carga').dialog("open");
    }).ajaxStop(function(){
        $('#dialog_carga').dialog("close");
    });
}
function controla_busquedas(Limite_paginacion){
    if($(document).attr('title')=="BusquedaItems"){
//        alert("o item");
        paginacion(Limite_paginacion,ajax_buscar_item);
    }
    if($(document).attr('title')=="BusquedaProveedores"){
        //alert("o proveedores");
        paginacion(Limite_paginacion,ajax_buscar_proveedores);
    }
    if($(document).attr('title')=="BusquedaTransacciones"){
//        alert("o transaccione");
        paginacion(Limite_paginacion,ajax_buscar_transacciones);
    }
    if($(document).attr('title')=="BusquedaAdjudicados"){
//        alert("o adjudicacion");
        paginacion(Limite_paginacion,ajax_buscar_adjudicados);
    }
}
function paginacion(Limite,funcion_busqueda_ajax){
    var c=0;
    var sw_paginacion;
    //busquedas_ajax(get_min(c,Limite),get_max(c,Limite));
    $("#btn-buscar").on("click",function(){
        c=0;
        //alert($("#buscar_proveedores").serialize());
        sw_paginacion=true;
        funcion_busqueda_ajax(Limite,c,sw_paginacion);
        sw_paginacion=false;
    });
    
    $(".btn_siguiente").on("click",function(){
         c++;
         funcion_busqueda_ajax(Limite,c,sw_paginacion);
         //alert(get_min(c,Limite)+"  "+get_max(c,Limite));
         
    });
    $(".btn_anterior").on("click",function(){
         c--;
         //alert("Pulsaste Ant "+c)
         funcion_busqueda_ajax(Limite,c,sw_paginacion);
         //alert(get_min(c,Limite)+"  "+get_max(c,Limite));
    });
    $("#ir_pagina").on("click",function(){
        c = $("#input_pagina").val()-1;
        funcion_busqueda_ajax(Limite,c,sw_paginacion);
        //alert(nro_pagina)
        //ajax_buscar_proveedores(get_min(nro_pagina,Limite),get_max(nro_pagina,Limite),Limite,nro_pagina,sw_paginacion);
    });
}

/*****AJAX*******/

function ajax_buscar_transacciones(Limite, c, sw_paginacion){
    var min = get_min(c,Limite);
    var max = get_max(c,Limite);
    //alert(min+" - "+max);
    /*var min=0;
    var max=0;*/
    var total_filas=0;
    $.ajax({
        type: 'post',
        url: '/capri-web/Busqueda.umsa',
        data:$("#buscar_transaccion").serialize()+"&min="+min+"&max="+max,
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            arma_resultados_transacciones(json,c,Limite,sw_paginacion,total_filas);
            //alert(":P");    
        }
    });
}
function ajax_buscar_proveedores(Limite, c, sw_paginacion){
    var min = get_min(c,Limite);
    var max = get_max(c,Limite);
    
    var total_filas=0;
    $.ajax({
        type: 'post',
        url: '/capri-web/BuscarProveedor.umsa',
        data:$("#buscar_proveedores").serialize()+"&min="+min+"&max="+max,
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            arma_resultados_proveedores(json,c,Limite,sw_paginacion,total_filas);
            
        }
    });
}
function ajax_buscar_item(Limite, c, sw_paginacion){
    var min = get_min(c,Limite);
    var max = get_max(c,Limite);
    var total_filas=0;
    $.ajax({
        type: 'post',
        url: '/capri-web/BuscarItems.umsa',
        data:$("#buscar_items").serialize()+"&min="+min+"&max="+max,
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            
        }
    });
}
function ajax_buscar_adjudicados(Limite, c, sw_paginacion){
    var min = get_min(c,Limite);
    var max = get_max(c,Limite);
    var total_filas=0;
    $.ajax({
        type: 'post',
        url: '/capri-web/BuscarAdjudicados.umsa',
        data:$("#buscar_adjudicados").serialize()+"&min="+min+"&max="+max,
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert(json.total_filas);
            arma_resultados_adjudicados(json,c,Limite,sw_paginacion,total_filas);
            
        }
    });
}
function ajax_mas_detalle(tipo_id, documento){
    //alert(tipo_id+" &&& "+documento);
    var x=0;
    $.ajax({
        type: 'post',
        url: '/capri-web/ProveedorInfo.umsa',
        data:{tipo_id:tipo_id, documento:documento},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            //alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            //alert(json.toString());
            ajax_mas_detalle_adj(documento)
            //x=1;
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //if(x==0){
            abrir_detalle_proveedor(json);
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
        }
    });
}
function ajax_mas_detalle_adj(documento){
    $.ajax({
        type: 'post',
        url: '/capri-web/AdjudicadoInfo.umsa',
        data:{documento:documento},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            
            //ajax_mas_detalle_adj(documento)
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            abrir_detalle_adjudicado(json);
            //alert(json);
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
        }
    });
}
function arma_resultados_clasificador(json, c, Limite, sw_paginacion, total_filas){
    //alert("Los Items");
    var x=$(".tabla_resultados table tbody");
    x.empty();
    var nro_paginas = Math.ceil(json.total_filas/Limite);
    //alert("es: "+nro_paginas)
    if(nro_paginas>0){
        var nro;
        $.each(json.Items, function(index, item){
            nro=((c*Limite)+index)+1;
            x.append("<tr><td>"+nro+"</td><td>"+item.codigo+"</td><td>"+item.partida+"</td><td>"+item.articulo+"</td><td>"+item.gestion+"</td><td>"+item.tipo+"</td></tr>");
        });
        /*****************/
        controla_desborde_paginacion(c+1,nro_paginas)
        /*****************/
        $("#nro_pagina").html(c+1);
        $(".menu_paginacion").show();
    }else{
        $("#nro_pagina").html(0);
        $(".menu_paginacion").hide();
        $("#dialog_vacio").dialog( "open" );
    }
    if(sw_paginacion){
        total_filas=json.total_filas;
        arma_paginacion(total_filas);
    }
}

function arma_resultados_adjudicados(json, c, Limite, sw_paginacion, total_filas){
    var x=$(".tabla_resultados table tbody");
    x.empty();
    var nro_paginas = Math.ceil(json.total_filas/Limite);
    //alert("es: "+nro_paginas)
    if(nro_paginas>0){
        var nro;
        $.each(json.Adjudicado, function(index, item){
            console.log('hola');
            nro=((c*Limite)+index)+1;
            x.append("<tr><td>"+nro+"</td><td>"+item.codigo+"</td><td>"+item.nombre+"</td><td>"+item.nombre_comercial+"</td><td>"+item.gestion+"</td><td><button data-documento ='"+item.codigo+"' \n\\n\
                         \n\
                        class='btn_detalle_adjudicado'>VER</button></td><td><button data-tipo_id='"+item.tipo_id+"' data-documento ='"+item.codigo+"' \n\\n\
                         \n\
                        class='btn-proveedor-detalle'>Informacion</button></td></tr>");
        });
        $(".btn_detalle_adjudicado").on("click",function(){
                detalle_adjudicado($(this).attr('data-documento'));
            });
        $(".btn-proveedor-detalle").on("click", function(){
            ajax_mas_detalle($(this).attr('data-tipo_id'),$(this).attr('data-documento'));
            //ajax_mas_detalle_adj($(this).attr('data-documento'));
        });
        /*****************/
        controla_desborde_paginacion(c+1,nro_paginas)
        /*****************/
        $("#nro_pagina").html(c+1);
        $(".menu_paginacion").show();
    }else{
        $("#nro_pagina").html(0);
        $(".menu_paginacion").hide();
        $("#dialog_vacio").dialog( "open" );
    }
    if(sw_paginacion){
        total_filas=json.total_filas;
        arma_paginacion(total_filas);
    }
}

function detalle_adjudicado(cod){
    $.ajax({
        type: 'post',
        url: '/capri-web/detalle_adju.umsa',
        data:{documento: cod},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //alert("Exito!!");
            var x=$("#dialog_rastrear table tbody");
            x.empty();
            if(json.Adjudicado.length>0){
                $.each(json.Adjudicado, function(index, item){
                x.append("<tr>\n\
                 <td>"+item.partida+"</td>\n\
                 <td>"+item.detalle+"</td>\n\
                 <td>"+item.gestion+"</td>\n\
                 </tr>");
            });
            $("#dialog_rastrear").dialog( "open" );
            }
            else{
               //x.append("<h3>SIN DATOS</h3>");
               $("#dialog_vacio").dialog( "open" );
            }
            
            //$("#dialog_rastrear").empty();
        }
    });
}




function controla_desborde_paginacion(pagina, totalpaginas){
    if(pagina==totalpaginas){
        $(".btn_siguiente").prop("disabled",true);
        }else{
            $(".btn_siguiente").prop("disabled",false);
        }
        if(pagina==1){
            $(".btn_anterior").prop("disabled",true);
        }else{
            $(".btn_anterior").prop("disabled",false);
        }
}
function rastrear_transaccion(cod){
    $.ajax({
        type: 'post',
        url: '/capri-web/Rastrear.umsa',
        data:{cod_transaccion: cod},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //alert("Exito!!");
            var x=$("#dialog_rastrear table tbody");
            x.empty();
            if(json.Transaccion.length>0){
                $.each(json.Transaccion, function(index, item){
                x.append("<tr>\n\
                 <td>"+item.cuce+"</td>\n\
                 <td>"+item.articulo+"</td>\n\
                 <td>"+item.estado+"</td>\n\
                 <td>"+"<a href='#' class='btn_info_adicional' data-cod_trans_detalle='"+item.cod_trans_detalle+"'><button>Más</button></a>"+"</td>\n\
                 </tr>");
            });
            $("#dialog_rastrear").dialog( "open" );
            }
            else{
               //x.append("<h3>SIN DATOS</h3>");
               $("#dialog_vacio").dialog( "open" );
            }
            
            //$("#dialog_rastrear").empty();
        }
    });
}
function archivos_transaccion(cod){
    $.ajax({
        type: 'post',
        url: '/capri-web/Archivos.umsa',
        data:{cod_transaccion: cod},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //alert("Exito!!");
            var x=$("#dialog_archivos nav ul");
            x.empty();
            if(json.Transaccion.length>0){
                $.each(json.Transaccion, function(index, item){
                    x.append("<li><a width='150px' target='_blank' href='../prueba/"+item.nombre_archivo+"'><button>"+item.nombre_archivo+"</button></a></li>");
                    //$("#dialog_rastrear").dialog( "open" );
                });
                $("#dialog_archivos").dialog( "open" );
            }
            else{
                //x.append("<h3>SIN DATOS</h3>");
                $("#dialog_vacio").dialog( "open" );
            }
            
            
        }
    });
}
function formu_transaccion(cod){
    $.ajax({
        type: 'post',
        url: '/capri-web/Formu.umsa',
        data:{cod_transaccion: cod},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            /*alert((json.Transaccion.length+(json.codigoS.length*1)));
            if((json.Transaccion.length+(json.codigoS.length*1))>0){
                
            }*/
            var x=$("#dialog_formularios nav ul");
            x.empty();
            /*$(xml).find('formulario-soliditud').each(function(){
                x.append("<li><a target='_blank' href='reporteSolicitud?&tipo_tramite=SOLICITUD%20DE%20COMPRAS&cod_transaccion="+$(this).find('codigo').text()+"&cod_estado=ALM'><button>Solicitud de Compra</button></a></li>");
            });*/
            if(json.codigoS!=null){
                //alert("Wayy");
                x.append("<li><a target='_blank' href='reporteSolicitud?&tipo_tramite=SOLICITUD%20DE%20COMPRAS&cod_transaccion="+json.codigoS+"&cod_estado=PPTO'><button>Solicitud de Compra</button></a></li>");
            }
            //alert(json.Transaccion.length);          
            $.each(json.Transaccion, function(index, item){
                x.append("<li><a target='_blank' href='reporteOrden?&cod_tramite=2&cod_estado="+item.codigoW+"&cod_trans_nro="+item.codigoO+"'><button>Orden de Compra</button></a></li>");
            });
            if(json.codigoS==null && json.Transaccion.length<=0){
                $("#dialog_vacio").dialog( "open" );
            }
            else{
                $("#dialog_formularios").dialog( "open" );
            }
            
        }
    });
}
function arma_resultados_transacciones(json, c, Limite, sw_paginacion, total_filas){
    var x=$(".tabla_resultados table tbody");
    x.empty();
    var nro_paginas = Math.ceil(json.total_filas/Limite);
    alert(json.total_filas);
    if(json.total_filas>0){
        var nro;
        $.each(json.Transacciones, function(index, item){
            nro=((c*Limite)+index)+1;
            x.append("<tr><td>"+/*nro*/index+"</td><td>"+item.cuce+"</td><td>"+item.tipo+"</td><td>"+item.sol+"</td><td>"+item.des+"</td><td>"+item.cuantia+"</td><td>1</td> <td>"+item.detalle+"</td><td>"+item.estado+"</td>\n\
                        <td><button \n\
                        data-cod_transaccion ='"+item.cod_transaccion+"' \n\
                        class='btn_rastrear_transaccion'>VER</button></td> \n\
                    <td>"+item.fecha+"</td><td></td>\n\
                    <td><button\n\
                        data-cod_transaccion ='"+item.cod_transaccion+"' \n\
                        class='btn_archivos_transaccion'>VER</button></td> \n\
                    <td><button\n\
                        data-cod_transaccion ='"+item.cod_transaccion+"' \n\
                        class='btn_formu_transaccion'>VER</button></td>\n\
                    </tr>");
            });
            $(".btn_rastrear_transaccion").on("click",function(){
                rastrear_transaccion($(this).attr('data-cod_transaccion'));
            });
            $(".btn_archivos_transaccion").on("click",function(){
               //alert(":D");
               archivos_transaccion($(this).attr('data-cod_transaccion'));
               
           });
            $(".btn_formu_transaccion").on("click",function(){
                //alert(":D");
                formu_transaccion($(this).attr('data-cod_transaccion'));
            });
            
            controla_desborde_paginacion(c+1,nro_paginas)
            $("#nro_pagina").html(c+1);
            $(".menu_paginacion").show();
    }else{
        $("#nro_pagina").html(0);
        $(".menu_paginacion").hide();
        $("#dialog_vacio").dialog( "open" );
    } 
    if(sw_paginacion){
        total_filas=json.total_filas;
        arma_paginacion(total_filas);
    }
}
function arma_resultados_proveedores(json, c, Limite, sw_paginacion, total_filas){
    //alert("Exito!!"+ json.TotalProveedores);
    var x=$(".tabla_resultados table tbody");
    x.empty();
    var nro_paginas = Math.ceil(json.total_filas/Limite);
    if(json.total_filas>0){
        var nro;
        $.each(json.Proveedores, function(index, item){
            nro=((c*Limite)+index)+1;
            x.append("<tr><td>"+nro+"</td><td>"+item.documento+"</td><td>"+item.descripcion+"</td><td>"+item.clase_beneficiario+"</td><td>"+item.nombre+"</td><td>"+item.nombre_comercial+"</td><td><button \n\
                        data-tipo_id='"+item.tipo_id+"' \n\
                        data-documento='"+item.documento+"' \n\
                     class='btn-proveedor-detalle'>VER</button></td></tr>");
        });
        $(".btn-proveedor-detalle").on("click", function(){
            ajax_mas_detalle($(this).attr('data-tipo_id'),$(this).attr('data-documento'));
        });
        controla_desborde_paginacion(c+1,nro_paginas)
        $("#nro_pagina").html(c+1);
        $(".menu_paginacion").show();
    }else{
        $("#nro_pagina").html(0);
        $(".menu_paginacion").hide();
        $("#dialog_vacio").dialog( "open" );
    }
    if(sw_paginacion){
        total_filas=json.total_filas;
        arma_paginacion(total_filas);
    }
    //autofitIframe();
}
function arma_paginacion(total_filas){
    //alert(total_filas);
    var total_paginas = Math.ceil(total_filas/50);
    /*if(total_paginas==pagina_actual){
        alert("Upsi");
        $(".btn_siguiente").hide();
    }*/
    //alert("es: "+pagina_actual);
    $("#span_total").html((total_filas*1));
    $("#span_total_paginas").html(total_paginas);
    //$("#range_paginas").attr("max", total_paginas);
    $("#input_pagina").attr("max", total_paginas);
}
/************/
function inicia_dialogs(){
    $("#dialog_vacio").dialog({
        autoOpen: false,
        width:400,
        height:'auto',
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });
    $("#dialog_detalle_proveedor").dialog({
        autoOpen: false,
        width:400,
        height:'auto',
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });
    $("#dialog_carga").dialog({
        autoOpen: false,
        width:500,
        height:'auto',
        modal: true
    });
    $("#dialog_formularios").dialog({
        position: ['0', $(window).height()/2],
        //position: ['center', 20],
        autoOpen: false,
        width:400,
        height:'auto',
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });
    $("#dialog_archivos").dialog({
        position: ['0', $(window).height()/2],
        //position: ['center', 20],
        autoOpen: false,
        width:400,
        height:'auto',
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });
    $("#dialog_mas_detalle").dialog({
        //alert(pos_dialog_y);
        position: ['0', $(window).height()/2],
        //position: ['center', 20],
        autoOpen: false,
        width:400,
        height:250,
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });
    $("#dialog_rastrear").dialog({
        //alert(pos_dialog_y);
        //position: ['0', $(window).height()/2],
        //position: ['center', 20],
        autoOpen: false,
        width:500,
        height:250,
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        },
        open: function(event, ui) {
            $(".btn_info_adicional").on("click",function(){
                $.ajax({
                    type: 'post',
                    url: '/capri-web/RastrearDetalle.umsa',
                    data:{cod_trans_detalle: $(this).attr('data-cod_trans_detalle')},
                    dataType: 'xml',
                    error: function(xhr, ajaxOptions, thrownError) {
                        alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
                    },
                    success: function(xml) {
                        var x=$("#dialog_mas_detalle table tbody");
                        x.empty();
                        $(xml).find('detalle').each(function(){
                            x.append("<tr><td>"+$(this).find('estado').text()+"</td><td>"+$(this).find('fecha').text()+"</td></tr>");
                        });
                        $("#dialog_mas_detalle").dialog( "open" );
                    }
                });
            });
        }
    });
}

function abrir_detalle_proveedor(json){
    var dialogx=$("#dialog_detalle_proveedor");
    dialogx.empty();
    dialogx.append("<h6>Información de Contacto</h3>");
    dialogx.append("<p>Lugar: "+json.dir_lugar+"</p>");
    dialogx.append("<p>Direccion: "+json.dir_direccion+"</p>");
    dialogx.append("<p>Telefono: "+json.dir_telefono+"</p>");
    dialogx.append("<p>Email: "+json.dir_email+"</p>");
    dialogx.append("<h6>Información del Representante Legal</h3>");
    dialogx.append("<p>Nombre: "+json.adh_nombre+"</p>");
    dialogx.append("<p>Tipo Documento:"+json.adh_tipo_id+"</p>");
    dialogx.append("<p>Codigo Documento: "+json.adh_documento+"</p>");
    dialogx.dialog("open");
}
function abrir_detalle_adjudicado(json){
    var dialogx=$("#dialog_detalle_proveedor");
    dialogx.empty();
    $.each(json.Adjudicado, function(index, item){
    dialogx.append("<h6><span style='color: red'>ADVERTENCIA: </span>la persona no esta registrado en el sigma</h3>");
    dialogx.append("<h6>Información de Contacto</h3>");
    //dialogx.append("<p>Lugar: "+json.dir_lugar+"</p>");
    dialogx.append("<p>Direccion: "+item.dir_direccion+"</p>");
    dialogx.append("<p>Telefono: "+item.dir_telefono+"</p>");
    //dialogx.append("<p>Email: "+json.dir_email+"</p>");
    dialogx.append("<h6>Información del Representante Legal</h3>");
    dialogx.append("<p>Nombre: "+item.adh_nombre+"</p>");
    dialogx.append("<p>Tipo Documento:"+item.adh_tipo_id+"</p>");
    dialogx.append("<p>Codigo Documento: "+item.adh_documento+"</p>");
    dialogx.dialog("open");
        });
}