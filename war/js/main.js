$(document).on("ready", inicia);
function inicia() {
    controla_partidas();
//    controla_carga_ajax();
    botones();
    dialogs();
    restricciones();
    $( "#tabs" ).tabs();
//    ajax_genera_partidas(2014);
//    ajax_genera_items_all();
    // mensaje_registro();
    //$("#dialog_reportes form").css("color","red");
    
    
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

function genera_partidas(json){
    var x=$("#select_partidas");
    x.html("<option value=''>Ninguna</option>");
    $.each(json.Partidas, function(index, item){
        x.append("<option value="+item.partida+">"+item.detalle_partida+"</option>");
    });
    //alert("yeih: "+gestion);
}

function ajax_genera_items_all(){
    //alert("hola!!!");
    $.ajax({
        type: 'post',
        url: '/capri-web/GetItems2.umsa',
        //data:{gestion:gestion},
        dataType: 'json',
        async: false,
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            genera_items_all(json);
        }
    });
}

function genera_items_all(json){
    var x=$("#select_item");
    x.html("<option value=''>Ninguna</option>");
    $.each(json.Items, function(index, item){
        x.append("<option value="+item.cod_item+">"+item.articulo+"</option>");
    });
    //alert("yeih: "+gestion);
}

function restricciones() {
    $(".btn-avanza").on("click", function(event) {
        var boton = $(this);
        event.preventDefault();
        //alert("Hola Mamá!!!!");
        //$("#dialog_avanza").data("boton",$(this));
        $("#dialog_avanza").html("<span>&iquest;Esta seguro de avanzar el tramite?</span>");
        $("#dialog_avanza").dialog({
            autoOpen: false,
            width: 500,
            height: 'auto',
            modal: true,
            buttons: {
                
                'SI': function() {

                    ajax_restriccion(boton, boton.data("cod_transaccion"),
                            boton.data("cod_estado"),
                            boton.data("cod_tramite"),
                            boton.data("tipo_tramite"),
                            boton.data("cuce"),
                            boton.data("cod_w")
                            );
                    $(this).dialog('close');

//                    $(this).dialog('close');
//                    alert($("#dialog_notaConformidad form").serialize());
//                    ajax_update_nc($(this).data('cod_trans_nro'));
                },
                'NO': function() {
                    //alert($(this).data('cod_trans_nro'));
                    $(this).dialog('close');
                }
            }
        });
        $("#dialog_avanza").dialog("open");
//       ajax_restriccion($(this),$(this).data("cod_transaccion"),
//                        $(this).data("cod_estado"),
//                        $(this).data("cod_tramite"),
//                        $(this).data("tipo_tramite"),
//                        $(this).data("cuce"),
//                        $(this).data("cod_w")
//       );

        //return confirm('¿Desea AVANZAR esta transaccion ?');
    });
}

function ajax_restriccion(boton, cod_transaccion, cod_estado, cod_tramite, tipo_tramite, cuce, cod_w) {
    var sw = false;
    $.ajax({
        type: 'post',
        url: '/capri-web/RestriccionAvanza.umsa',
        data: {cod_transaccion: cod_transaccion},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
        },
        success: function(json) {
            //alert("Exito!!!"+json.Items);
            var alertas = json.Items.split(",");
            $.each(alertas, function(index, item) {
                if (item === "0") {
                    if (index === 0) {
                        //alert("Restriccion Cantidad "+index+" :/");
                        $("#dialog_restriccion_cantidad").html("<span style='color:red'>TODOS LOS ITEMS DEBEN TENER UNA CANTIDAD MAYOR A 0</span>");
                        $("#dialog_restriccion_cantidad").dialog("open");
                        sw = true;
                    }

                    if (index === 1) {
//                        alert("Restriccion Precio "+index+" :/");
                        $("#dialog_restriccion_precio").html("<span style='color:red'>TODOS LOS ITEMS DEBEN TENER UN PRECIO MAYOR A 0</span>");
                        $("#dialog_restriccion_precio").dialog("open");
                        sw = true;
                    }

                    if (index === 2) {
//                        alert("Restriccion Unidad Medida "+index+" :/");ç
                        $("#dialog_restriccion_unidad_medida").html("<span style='color:red'>TODOS LOS ITEMS DEBEN TENER UNIDAD DE MEDIDA</span>");
                        $("#dialog_restriccion_unidad_medida").dialog("open");
                        sw = true;
                    }

                    if (index === 3) {
//                        alert("Restriccion Detalle "+index+" :/");
                        $("#dialog_restriccion_detalle").html("<span style='color:red'>TODOS LOS ITEMS DEBEN TENER UN DETALLE</span>");
                        $("#dialog_restriccion_detalle").dialog("open");
                        sw = true;
                    }

                    if (index === 4) {
//                        alert("Restriccion Detalle "+index+" :/");
                        $("#dialog_restriccion_size").html("<span style='color:red'>EL FORMULARIO DEBE TENER AL MENOS UN ITEM</span>");
                        $("#dialog_restriccion_size").dialog("open");
                        sw = true;
                    }
                }
            });
//            if(sw)
//                return;
//            else
//                ajax_avanza_tramite();

            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
        },
        complete: function() {
            if (sw)
                return;
            else
                ajax_avanza_tramite(boton, cod_transaccion, cod_estado, cod_tramite, tipo_tramite, cuce, cod_w);
        }
    });
}

function ajax_avanza_tramite(boton, cod_transaccion, cod_estado, cod_tramite, tipo_tramite, cuce, cod_w) {
 
    //alert("Wujuuuu");
    $.ajax({
        type: 'get',
        url: '/capri-web/TransaccionSolicitudAvanza.umsa',
        async: false,
        data: {
            cod_estado: cod_estado,
            cod_tramite: cod_tramite,
            tipo_tramite: tipo_tramite,
            cod_w: cod_w,
            cuce: cuce,
            cod_transaccion: cod_transaccion
        },
//        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
        },
        success: function(json) {
           
            boton.parents(":eq(1)").remove();
            //alert("Exito :D");
        }
    });
}
function controla_carga_ajax() {
    $(document).ajaxStart(function() {
        $('#dialog_carga').dialog("open");
    }).ajaxStop(function() {
        $('#dialog_carga').dialog("close");
    });
}
function controla_partidas() {
    $("#combo_partidas").on("change", function() {
        //ajax_genera_partidas($(this).val());
        //alert("Que bien :P");
        ajax_genera_items($(this).val());
    });
}
function ajax_genera_items(partida) {
    //alert("");
    $.ajax({
        type: 'post',
        url: '/capri-web/GetItems.umsa',
        data: {partida: partida},
        dataType: 'json',
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            genera_lista_items(json);
        }
    });
}
function genera_lista_items(json) {
    //alert("Chango!!!! "+json);
    $("#descripcion_partida p").html(json.Descripcion);
    var x = $("#cuerpo_buscadorAvanzado table tbody");
    x.empty();
    $.each(json.Items, function(index, item) {
        //alert("'"+item.unidad_medida+"'" );
        //var cod_item = "'"+item.cod_item+"'";
        //var unidad_medida = "'"+item.unidad_medida+"'";
        //x.append("<tr onclick='javascript:return CantidadItem("+cod_item+","+unidad_medida+")'  style='cursor:pointer' ><td>"+item.articulo+"</td><td>"+item.detalle+"</td><td>"+item.unidad_medida+"</td></tr>");
        x.append("<tr class='link_item' data-cod_item=" + item.cod_item + " data-unidad_medida=" + item.unidad_medida + " style='cursor:pointer' ><td>" + item.articulo + "</td><td>" + item.detalle + "</td><td>" + item.unidad_medida + "</td></tr>");

        //onclick="javascript:return CantidadItem('${item.cod_item}','${item.unidad_medida}')" style="cursor:pointer"
    });
    $(".link_item").on("click", function() {
        //alert("hola :P");
        //var gestion = $(this).attr('data-nro_gestion');
        $("#buscador_itemsAvanzado").dialog("close");
        CantidadItem($(this).attr('data-cod_item'), $(this).attr('data-unidad_medida'));
    });
}
function ajax_genera_nota_conformidad(data) {
    //alert("");
    $.ajax({
        type: 'get',
        url: '/capri-web/reporteNotaConformidad',
        data: data,
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
        },
        success: function() {
            //arma_resultados_clasificador();
            //alert("yeihhh");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
//            genera_lista_items(json);
            alert("Oky Doky");
        }
    });
}
function dialogs() {
    $("#nc_fecha").datepicker({
        dateFormat: 'dd-mm-yy',
    });
//    $("#dialog_notaConformidad").dialog({
//        autoOpen: false,
//        width:500,
//        height:'auto',
//        modal: true
//    });
    $("#dialog_notaConformidad").dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            'Salir': function() {
                //alert($(this).data('cod_trans_nro'));
                $(this).dialog('close');
            },
            'Guardar': function() {
//                $(this).dialog('close');
                //alert($("#dialog_notaConformidad form").serialize());
                ajax_update_nc($(this).data('cod_trans_nro'));
            }
        },
        open: function(event, ui) {
            //alert("uuuhhh"+$("#dialog_notaConformidad").data("cod_trans_nro"));
            ajax_carga_nc($(this).data('cod_trans_nro'));
            //alert($("#btn-reporteNota").html());
            $("#btn-reporteNota").attr("href", "/capri-web/reporteNotaConformidad?&cod_tramite=4&cod_trans_nro=" + $(this).data("cod_trans_nro"));
        }


    })
            .parent()
            .find('.ui-dialog-buttonset')
            .prepend('<a id="btn-reporteNota" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" target="_blank">Imprimir</a>');

    function ajax_update_nc(cod_trans_nro) {
        $.ajax({
            type: 'post',
            url: '/capri-web/UpdateNotaConformidad.umsa',
            data: $("#dialog_notaConformidad form").serialize() + "&cod_trans_nro=" + cod_trans_nro,
//            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
            },
            success: function() {
                alert("Se guardaron los datos exitosamente");
                //alert(json.ciudad);
//                $("#nc_ciudad").val(json.ciudad);
//                $("#nc_fecha").val(json.fecha_nc);
//                $("#nc_hora").val(json.hora_nc);
//                $("#nc_lugar").val(json.lugar);
                //arma_resultados_clasificador();
                //alert("yeihhh");
                //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
                //genera_lista_items(json);
                //alert("Oky Doky");
            }
        });
    }
    function ajax_carga_nc(cod_trans_nro) {
//        alert("Wujuuu :P");

        $.ajax({
            type: 'get',
            url: '/capri-web/GetNotaConformidad.umsa',
            data: {cod_trans_nro: cod_trans_nro},
            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
            },
            success: function(json) {
                //alert(json.ciudad);
                $("#nc_ciudad").val(json.ciudad);
                $("#nc_fecha").val(json.fecha_nc);
                $("#nc_hora").val(json.hora_nc);
                $("#nc_lugar").val(json.lugar);
                //arma_resultados_clasificador();
                //alert("yeihhh");
                //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
                //genera_lista_items(json);
                //alert("Oky Doky");
            }
        });
    }
//    function arma_nc(json){
//        
//    }
    $('#myCustomLink').click(function() {
        alert('my custom message');
    });

//    $("#dialog_avanza").dialog({
//        
//
//    });
    $("#dialog_restriccion_size").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true,
        buttons: {
            'OK': function(){
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_restriccion_cantidad").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true,
        buttons: {
            'OK': function(){
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_restriccion_precio").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true,
        buttons: {
            'OK': function(){
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_restriccion_unidad_medida").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true,
        buttons: {
            'OK': function(){
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_restriccion_detalle").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true,
        buttons: {
            'OK': function(){
                $(this).dialog("close");
            }
        }
    });

    $("#dialog_carga").dialog({
        autoOpen: false,
        width: 500,
        height: 'auto',
        modal: true
    });
    $("#dialog_poa").dialog({
        autoOpen: false,
        width: 700,
        height: 300,
        modal: true,
        buttons: {
            "Ok": function() {
                $(this).dialog("close");
            }
        }
    });
    $("#mensaje-registro").dialog({
        modal: true,
        autoOpen: false,
        height: 200,
        width: 450,
        buttons: {
            Ok: function() {
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_iniciar_sesion").dialog({
        modal: true,
        autoOpen: false,
        height: 200,
        width: 450,
        buttons: {
            Ok: function() {
                $(this).dialog("close");
            }
        }
    });
    $("#mensaje-login_bad").dialog({
        modal: true,
        autoOpen: false,
        height: 200,
        width: 450,
        buttons: {
            Ok: function() {
                $(this).dialog("close");
            }
        }
    });
    $('#buscador_itemsAvanzado').dialog({
        width: 605,
        height: 400,
        modal: true,
        autoOpen: false,
        resizable: false,
        buttons: {
            "¿Qué hago si no encuentro el item?": function() {
                $("#dialog_no-item").dialog("open");
            },
            "Salir": function() {
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_no-item").dialog({
        modal: true,
        autoOpen: false,
        height: 450,
        width: 680,
        buttons: {
            Ok: function() {
                $(this).dialog("close");
            }
        }
    });
    $("#dialog_reportes").dialog({
        modal: true,
        autoOpen: false,
        height: 250,
        width: 450,
        buttons: {
            
            'Ver Reporte': function(){
                alert("hola palusa");
            },
            Ok: function() {
                $(this).dialog("close");
            }
        },
        open: function(){
            //alert("hola payaso!!!");
            ajax_genera_partidas(2014);
            ajax_genera_items_all();
        }
    });
}
function botones() {
    //estilo_botones();
    $("#btn_registro").on("click", mensaje_registro);
    $("#btn_inicia_sesion").on("click", dialog_inicia_sesion);

    $("#busqueda_avanzada_items").on("click", dialog_busqueda_avanzada_items);
    //$(".btn-operacion").on("click",seleciona_boton);
    $("#btn-poa").button();
    $("#btn-poa_padre").button();
    $("#busqueda_avanzada_items").button();
    $("#btn_archivo_adjunto").button();
    $("#btn-poa").on("click", poa);
    $("#btn-poa_padre").on("click", poa_padre);
    $(".btn_nc").on("click", function() {
        dialog_nota_conformidad($(this).attr('data-cod_trans_nro'))
    });
    $("#btn_reportes").on("click", function() {
        $("#dialog_reportes").dialog("open");
    });
    
}
function dialog_nota_conformidad(cod_trans_nro) {
    $("#dialog_notaConformidad").data('cod_trans_nro', cod_trans_nro);
    $("#dialog_notaConformidad").dialog("open");
}
function dialog_inicia_sesion() {
    $("#dialog_iniciar_sesion").dialog("open");
}
function dialog_busqueda_avanzada_items() {
    $("#buscador_itemsAvanzado").dialog("open");
}
function mensaje_registro() {
    //alert("Yeahh");
    $("#mensaje-registro").dialog("open");
    //alert("Yeahh");
}
function seleciona_boton() {
    //$(this).css("color","red");
}
function estilo_botones() {
    $("#btn_crear_nuevo").button({
        icons: {
            primary: "ui-icon-circle-plus"
        }
    });
    $("#menu_bandeja_seleccion").buttonset();
}
function prueba() {
    alert("quiubo :P");
}
function poa() {

    var gestion = $(this).attr('data-nro_gestion');
    var destino = $(this).attr('data-ue_solicitante');
    var aux = destino.substring(0, 14);
    //alert(destino +" <-> "+ aux );
    if (aux != "01001030000001")
    {
        //alert(gestion);
        //alert(gestion+" --hulala-- "+destino);

        //alert("Changos Charangos");
        //$("#dialog_poa").dialog( "open" );

        $.ajax({
            type: 'post',
            url: '/capri-web/GetPOA.umsa',
            data: {destino: destino, gestion: gestion},
            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
            },
            success: function(json) {
                //alert("Exito2");
                var total_presupuesto = 0;
                var total_ejecutado = 0;
                var total_saldo = 0;

                var x = $("#dialog_poa table tbody");
                x.empty();
                $.each(json.POA, function(index, item) {
                    x.append("<tr><td>" + item.OBJETO_DEL_GASTO + "</td><td>" + item.DESC_OBJETO_DEL_GASTO + "</td><td>" + item.FUENTE + "</td><td>" + item.ORGANISMO + "</td><td>" + item.MODIFICACIONES_PRESUPUESTALES + "</td><td>" + item.DEVENGADO + "</td><td>" + item.SALDO_POR_PREVENIR + "</td></tr>");
                    total_presupuesto += parseFloat(item.MODIFICACIONES_PRESUPUESTALES);
                    total_ejecutado += parseFloat(item.DEVENGADO);
                    total_saldo += parseFloat(item.SALDO_POR_PREVENIR);
                });
                //alert("Total presupuesto --> "+total_presupuesto);
                //alert("Total ejecutado --> "+total_ejecutado);
                //alert("Total saldo --> "+total_saldo);
                x.append("<tr style='color: red;'><td colspan = 4>Totales</td><td>" + total_presupuesto.toFixed(2) + "</td><td>" + total_ejecutado.toFixed(2) + "</td><td>" + total_saldo.toFixed(2) + "</td></tr>");

                $("#dialog_poa").dialog("open");
            }
        });

    } else {
        alert("Por favor para verificar el programa institucional\n solicite información al  Depto. de Presupuesto");
    }
}
function poa_padre() {

    var gestion = $(this).attr('data-nro_gestion');
    var destino = $(this).attr('data-ue_solicitante');
    var aux = destino;
//    alert(destino +" <-> "+ aux );
    if (aux != "01001030000001")
    {
        //alert(gestion);
        //alert(gestion+" --hulala-- "+destino);

        //alert("Changos Charangos");
        //$("#dialog_poa").dialog( "open" );

        $.ajax({
            type: 'post',
            url: '/capri-web/GetPOA.umsa',
            data: {destino: destino, gestion: gestion},
            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: " + xhr + " " + ajaxOptions + " " + thrownError);
            },
            success: function(json) {
                //alert("Exito2");
                var total_presupuesto = 0;
                var total_ejecutado = 0;
                var total_saldo = 0;

                var x = $("#dialog_poa table tbody");
                x.empty();
                $.each(json.POA, function(index, item) {
                    x.append("<tr><td>" + item.OBJETO_DEL_GASTO + "</td><td>" + item.DESC_OBJETO_DEL_GASTO + "</td><td>" + item.FUENTE + "</td><td>" + item.ORGANISMO + "</td><td>" + item.MODIFICACIONES_PRESUPUESTALES + "</td><td>" + item.DEVENGADO + "</td><td>" + item.SALDO_POR_PREVENIR + "</td></tr>");
                    total_presupuesto += parseFloat(item.MODIFICACIONES_PRESUPUESTALES);
                    total_ejecutado += parseFloat(item.DEVENGADO);
                    total_saldo += parseFloat(item.SALDO_POR_PREVENIR);
                });
                //alert("Total presupuesto --> "+total_presupuesto);
                //alert("Total ejecutado --> "+total_ejecutado);
                //alert("Total saldo --> "+total_saldo);
                x.append("<tr style='color: red;'><td colspan = 4>Totales</td><td>" + total_presupuesto.toFixed(2) + "</td><td>" + total_ejecutado.toFixed(2) + "</td><td>" + total_saldo.toFixed(2) + "</td></tr>");

                $("#dialog_poa").dialog("open");
            }
        });

    } else {
        alert("Por favor para verificar el programa institucional\n solicite información al  Depto. de Presupuesto");
    }
}