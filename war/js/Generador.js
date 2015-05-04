$(document).on("ready",inicia);
function inicia(){
        //SubTotal();
        Controla_PrecioU();
        GuardaProveedor();
        Total();
        Dialogs();
        CargaProveedores();
        setProveedor();
        controla_carga_ajax();
}
function controla_carga_ajax(){
    $(document).ajaxStart(function(){
        $('#dialog_carga').dialog("open");
    }).ajaxStop(function(){
        $('#dialog_carga').dialog("close");
    });
}
function ajax_adjudicaProveedor(){
    $(".btn_AdjProveedor").on("click",function(){
        //alert(":P :P");
        $.ajax({
            type: 'post',
            url: '/capri-web/AdjProveedor.umsa',
            data:{documento:$(this).attr('data-documento'), 
                tipo_id:$(this).attr('data-tipo_id'), 
                clase_beneficiario:$(this).attr('data-clase_beneficiario'), 
                nombre:$(this).attr('data-nombre'), 
                nombre_comercial:$(this).attr('data-nombre_comercial'),
                cod_transaccion:$(this).attr('data-cod_transaccion'),
                cod_trans_nro:$(this).attr('data-cod_trans_nro'),
                
                adh_nombre:$(this).attr('data-adh_nombre'),
                dir_lugar:$(this).attr('data-dir_lugar'),
                dir_direccion:$(this).attr('data-dir_direccion'),
                dir_telefono:$(this).attr('data-dir_telefono')
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            },
            success: function(json) {
                alert("Se adjudico la Compra con exito");
                $('.dialog_buscaProveedor').dialog("close");
                
                $.each(json.DetPROVEEDOR, function(index, item){
                    $('#nombre_proveedor').val(item.nombre);
                    $('#casa_comercial_proveedor').val(item.casa_comercial);
                    $('#direccion_proveedor').val(item.dir_direccion);
                    $('#tel_fax').val(item.dir_telefono);
                    $('#nit_proveedor').val(item.documento);
                });
                //$('.dialog_buscaDetalleProveedor').dialog("open");
            }
        });
    });
}
function ajax_detalleProveedor(){
    $(".btn_DetProveedor").on("click",function(){
        $.ajax({
            type: 'post',
            url: '/capri-web/GetDetalleProveedores.umsa',
            data:{documento:$(this).attr('data-documento'), tipo_id:$(this).attr('data-tipo_id')},
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            },
            success: function(json) {
                //alert("Exito");
                var x = $('.dialog_buscaDetalleProveedor');
                x.empty();
                $.each(json.DetPROVEEDOR, function(index, item){
                    x.append("<p>"+item.dir_direccion+"</p>");
                    x.append("<p>"+item.adh_nombre+"</p>");
                    x.append("<p>"+item.dir_email+"</p>");
                    x.append("<p>"+item.dir_telefono+"</p>");
                });
                $('.dialog_buscaDetalleProveedor').dialog("open");
            }
        });
    });
}
function CargaProveedores(){
    
    $( "#form_busquedaProveedores" ).submit(function( event ) {
            //alert( "Handler for .submit() called." );
            event.preventDefault();
            var $form = $( this ),
            //term = $form.find( "input[name='s']" ).val(),
            url = $form.attr( "action" );
            
            var posting = $.post( url, $(this).serialize());
            var x=$(".dialog_buscaProveedor table tbody");
            x.empty();
            posting.done(function( json ) {
//                var content = $( data ).find( "#content" );
//                $( "#result" ).empty().append( content );
                    $.each(json.PROVEEDORES, function(index, item){
                        //alert(item.nombre);
                        x.append("<tr><td>"+item.documento+"</td><td>"+item.descripcion+"</td><td>"+item.clase_beneficiario+"</td><td>"+item.nombre+"</td><td>"+item.nombre_comercial+"</td><td><button data-documento='"+item.documento+"' data-tipo_id='"+item.tipo_id+"' class='btn_DetProveedor'>VER\n\
                            </button></td><td><button class='btn_AdjProveedor' data-documento='"+item.documento+"' data-tipo_id='"+item.tipo_id+"' data-clase_beneficiario='"+item.clase_beneficiario+"' data-nombre='"+item.nombre+"' data-nombre_comercial='"+item.nombre_comercial+"' \n\
                            data-cod_transaccion='"+item.cod_transaccion+"' data-cod_trans_nro='"+item.cod_trans_nro+"' data-adh_nombre='"+item.adh_nombre+"' data-dir_lugar='"+item.dir_lugar+"' data-dir_direccion='"+item.dir_direccion+"' data-dir_telefono='"+item.dir_telefono+"'>Adjudicar</button></td></tr>");
                    });
                    ajax_detalleProveedor();
                    ajax_adjudicaProveedor();
//                alert(json.PROVEEDORES);
            });
        });
    
}
function setProveedor(){
    
    $( "#setProveedor" ).submit(function( event ) {
            //alert( "Handler for .submit() called." );
            event.preventDefault();
            var $form = $( this ),
            //term = $form.find( "input[name='s']" ).val(),
            url = $form.attr( "action" );
            
            var posting = $.post( url, $(this).serialize());
//            var x=$(".dialog_buscaProveedor table tbody");
//            x.empty();
            posting.done(function() {
                alert("Datos Actualizados");
            });
        });
    
}
function Dialogs(){
    $(".dialog_buscaProveedor").dialog({
        autoOpen: false,
        width:800,
        height:400,
        modal: true,
        beforeClose: function( event, ui ) {
            //alert("Jaja y me rioo..");
            $(this).find("tbody").empty();
        }
    });
    $(".dialog_buscaDetalleProveedor").dialog({
        autoOpen: false,
        width:400,
        height:400,
        modal: true
    });
    $("#dialog_carga").dialog({
        autoOpen: false,
        width:500,
        height:'auto',
        modal: true
    });
}
function GuardaProveedor(){
    $("#btn_buscar_proveedor").on("click",function(){
       $('.dialog_buscaProveedor').dialog("open"); 
    });
    $("#btn_guardaProveedor").on("click",function(){
        alert($(".datos-proveedor form").serialize());
    });
}
function Controla_PrecioU(){
    $(".In-PrecioU").on("blur",SubTotal);
}
function SubTotal(){
	//alert($(this).val());
	//alert($(this).parent().find('.cantidad-item').html());
        //alert("wujuuuuu --> "+$(this).attr('data-cod_trans_detalle'));
        var cod_trans_detalle = $(this).attr('data-cod_trans_detalle');
	var cantidad = parseInt($(this).parent().siblings('.cantidad-item').html());
	var precio = parseFloat($(this).val());
	$(this).parent().siblings('.subtotal-item').html(parseFloat(cantidad*precio));
	Total();
        
        $.ajax({
            type: 'post',
            url: '/capri-web/DGeneraOrdenCompra.umsa',
            data:{cod_trans_detalle:cod_trans_detalle, precio:precio},
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            },
            success: function() {
                //alert("Exito");
            }
        });
	//alert(cantidad*precio);
	//alert($(".cantidad-item").html());
}
function Total(){
	//alert("Try TOTAL --> "+$('.subtotal-item').toJSON());
	var Total = 0;
	$('.subtotal-item').each(function(index,item){
		Total = Total+parseFloat($(item).text());
	});
	//alert("TOTAL "+ Total);
	$("#total-item").html(Total);
}
function msj(){
	alert("Good Boy :P");
}