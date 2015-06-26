var cod_unidad_medida;
function iniciaTabla(){
	addArticulo(getUnidadMedida());
	delArticulo();
        Controla_Cambios();
        Dialog();
        Detalle();
        addDetalle();
//        Subtotal();
        Total();
        
//        getSelectData();
        

}

function Total(){
//    alert("Entro");
    var sum=0;
    var subtotal = 0;
    $("#mainTable tbody tr").each(function(){
        subtotal = $(this).find(".cantidad input").val()*$(this).find(".precio input").val();
//        alert("ah Ok -> "+subtotal);
        $(this).find(".subtotal").html(subtotal);
        sum = sum+subtotal;
       
//        $(this).css("background","red");
//        alert("ups");
    });
    $("#total").html(sum);
}
function getSelectData(){
//    $("#superheroes").on("change",function(){
//        alert("Hola payasos!!");
//    });
//    alert("1");
//    $("#superheroes").css("background","red");
//    $("#superheroes option").each(function() {
//        if($(this).is(':selected')){
//        // Your code here with the selected value
//        alert($(this).val());
//    }
////     alert("2");
//});
    $("#button").click(function() {
        var val = $('#item').val()
        var xyz = $('#items option').filter(function(){
            return this.value == val;
        }).data('xyz');
        /* if value doesn't match an option, xyz will be undefined*/
        var msg = xyz ? 'xyz=' + xyz : 'No Match';
        alert(msg)

    });
    
  
}
function addDetalle(){
    $("#addDetalle").on("click",function(){
        //$(this).parents(":eq(1)").css("background","red");
        //alert("upsi "+$(this).parents(":eq(1)").data("cod_trans_detalle"));
        //alert("upsi -> "+$("#dialog_detalle_item").data("cod_trans_detalle"));
        ajax_addDetallex($("#dialog_detalle_item").data("cod_trans_detalle"));
    });
}
function ajax_addDetallex(cod_trans_detalle){
    
    $.ajax({
        type: 'post',
        url: '/capri-web/addDetalle.umsa',
        data:{cod_trans_detalle:cod_trans_detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("se adiciono con exito :D"+json.cod_trans_detalle);
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
            $("#dialog_detalle_item table tbody").append("<tr data-cod_complemento = '"+json.cod_complemento+"'><td class = 'updateDetalle' ><input maxlength='75' size='50'/></td>\n\
                            <td><a href='#' class='delDetalle'><img class='img_peque' src='imagenes/minus.png'/></a></td></tr>");
            
//            x.append("<tr data-cod_trans_detalle = '"+item.cod_trans_detalle+"'><td class = 'updateDetalle'><input value='"+item.detalle_solicitud+"' maxlength='50' size='50'/></td>\n\
//                            <td><a href='#'><img class='img_peque' src='imagenes/minus.png'/></a></td></tr>");
            //activaEditable();
            //Controla_Cambios();
            delDetalle();
            DetalleControlaCambios();
        }
    });
    
    
    //alert("de veras!!!");
}
function Detalle(){
    
    $(".detailItem").on("click",function(){
        var cod_trans_detalle = $(this).parents(":eq(1)").attr('data-cod_trans_detalle');
        
        //alert(":P -> "+$(this).parents(":eq(1)").attr('data-cod_trans_detalle'));
        ajax_loadDetalle(cod_trans_detalle);
        //alert("hola");
        //alert($(this).parents(":eq(1)").attr('data-cod_trans_detalle'));
        //$("#dialog_detalle_item").data("cod_trans_detalle",$(this).parents(":eq(1)").attr('data-cod_trans_detalle'));
        //$("#dialog_detalle_item").dialog("open");
    });
}
function Dialog(){
     $("#dialog_detalle_item").dialog({
        autoOpen: false,
        width:500,
        height:300,
        modal: true,
        buttons: {
        "Salir": function() {
            //alert($("#dialog_detalle_item textarea").val());
            //ajax_addDetalle();
            //alert($(this).parents(":eq(1)").attr('data-cod_trans_detalle'));
            //alert($(this).data("cod_trans_detalle")+" -- el detalle es --> "+$(this).find("textarea").val());
            //ajax_addDetalle($(this).data("cod_trans_detalle"),$(this).find("textarea").val());
            $(this).dialog( "close" );
        },
        "Adicionar": function(){
            //<a id="addDetalle" href="#" ><img class="img_peque" src="imagenes/plus.png" alt="addDetalle"/></a>
            ajax_addDetallex($("#dialog_detalle_item").data("cod_trans_detalle"));
        }
      }
    });
    
    $("#dialog_unidad_medida").dialog({
        autoOpen: false,
        width:300,
        height:150,
        modal: true,
        buttons: {
        "Salir": function() {
            //alert($("#dialog_detalle_item textarea").val());
            //ajax_addDetalle();
            //alert($(this).parents(":eq(1)").attr('data-cod_trans_detalle'));
            //alert($(this).data("cod_trans_detalle")+" -- el detalle es --> "+$(this).find("textarea").val());
            //ajax_addDetalle($(this).data("cod_trans_detalle"),$(this).find("textarea").val());
            $(this).dialog( "close" );
        },
        "Adicionar": function(){
            cod_unidad_medida=ajax_add_unidad_medida($(this).find("input").val());
            //alert("Ohh yeahhh -> "+cod_unidad_medida);
//            alert(ajax_add_unidad_medida($(this).find("input").val()));
            //<a id="addDetalle" href="#" ><img class="img_peque" src="imagenes/plus.png" alt="addDetalle"/></a>
            //ajax_addDetallex($("#dialog_detalle_item").data("cod_trans_detalle"));
        }
      }
    });
    
}
function ajax_add_unidad_medida(unidad_medida){
//    alert(unidad_medida);
    var cod_unidad_medida = null;
    $.ajax({
        type: 'post',
        async: false,
        url: '/capri-web/addUnidadMedida.umsa',
        data:{unidad_medida:unidad_medida},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            cod_unidad_medida = json.cod_unidad_medida
            //alert(json.cod_unidad_medida);
            //arma_resultados_clasificador();
            //alert("se actualizo con exito :D");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
        }
//        ,
//        complete: function(){
//            alert("siiiii -> "+cod_unidad_medida);
//            return cod_unidad_medida;
//        }
    });
    return cod_unidad_medida;
}
function Controla_Cambios(){
	$(".cantidad input").on("change",ajax_updateItem);
        $(".unidad_medida select").on("change",ajax_updateItem);
//        $(".item input").focus();
        $(".item input").on("keyup",buscador_item);
        $(".item input").on("change",ajax_updateItem);
        $(".precio input").on("change",ajax_updateItem);
        
}
function buscador_item(){
    //alert($(this).val());
    var articulo = $(this).val();
    var fila =$(this).parents(":eq(1)");
    var cod_trans_detalle = fila.attr('data-cod_trans_detalle');
    if(articulo.length>3){
        $.ajax({
        type: 'post',
        url: '/capri-web/buscaItemReal.umsa',
        async: false,
        data:{articulo:articulo},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json){
            var options = "";
            $.each(json.ListaItemReal, function(index, item){
                options = options+"<option data-cod_item='"+item.cod_item+"' data-partida='"+item.partida+"' label='"+item.articulo+" - "+item.partida+"' value='"+item.articulo+"'>";                       
            });
            $("#List"+cod_trans_detalle).html(options);
            //alert(":D!!!!");
            //get_value(cod_trans_detalle);
            
        },
        complete: function(){
            //alert("Yeih!!! :D");
            //get_value(cod_trans_detalle);
        }
    });
    //get_value(cod_trans_detalle);
    }
}
function get_value(cod_trans_detalle){
//    $("#button").click(function(){
        var val = $('#Input'+cod_trans_detalle).val();
        var xyz = $('#List'+cod_trans_detalle+' option').filter(function(){
            return this.value == val;
        });
        
        var partida = xyz.data('partida');
        var cod_item = xyz.data('cod_item');
        /* if value doesn't match an option, xyz will be undefined*/
        //var msg = xyz ? 'x=' + xyz : 'No Match';
        //alert("El cod_item: "+cod_item+" -- partida: "+partida);
        $("#Part"+cod_trans_detalle).html(partida);
        
//    });
}
function msj(){
    alert("Hola mundillo!!!!");
}
function DetalleControlaCambios(){
    $(".updateDetalle input").on("change",ajax_updateDetalle);
}
function ajax_updateDetalle(){
    //msj();
    //alert(":P "+$(this).val());
    var fila =$(this).parents(":eq(1)");
    var cod_trans_detalle = $("#dialog_detalle_item").data("cod_trans_detalle");
    var cod_complemento = fila.attr('data-cod_complemento');
    var detalle = fila.find("td input").val();
    
    //alert(cod_trans_detalle+" -- "+cod_complemento+" -- "+detalle);
    
    $.ajax({
        type: 'post',
        url: '/capri-web/updateDetalle.umsa',
        data:{cod_trans_detalle:cod_trans_detalle,cod_complemento:cod_complemento,detalle:detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function() {
            //arma_resultados_clasificador();
            //alert("se actualizo con exito :D");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
        }
    });
}
function msj(){
    alert(":P");
}


function getTipoItem(){
    var sel = '<select id="" name="">\n\
                         <option value="0" selected>Seleccione</option>';
    $.ajax({
        type: 'post',
        url: '/capri-web/GetTipoItem.umsa',
        async: false,
        //data:{documento:$(this).attr('data-documento'), tipo_id:$(this).attr('data-tipo_id')},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //alert("Exito");
            //var x = $('.dialog_buscaDetalleProveedor');
            //x.empty();
            
            $.each(json.List_TipoItem, function(index, item){
                //alert("hola "+item.cod_tipo_item );
                sel = sel + '<option value="'+item.cod_tipo_item+'">'+item.detalle+'</option>';
            });
            sel = sel + "</select>";
            //alert(sel);
            //return sel;
        },
        complete: function(){
            //alert("Termino");
            return sel;
        }
    });
    
    //alert(sel);
    return sel;
    
}
function getUnidadMedida(){
    var sel = '<select name="unidad_medida">';
    $.ajax({
        type: 'post',
        url: '/capri-web/GetUnidadMedida.umsa',
        async: false,
        //data:{documento:$(this).attr('data-documento'), tipo_id:$(this).attr('data-tipo_id')},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //alert("Exito");
            //var x = $('.dialog_buscaDetalleProveedor');
            //x.empty();
            
            $.each(json.List_UnidadMedida, function(index, item){
                //alert("hola "+item.cod_tipo_item );
                if(item.cod_unidad_medida==0){
                    sel = sel + '<option value="'+item.cod_unidad_medida+'" selected>'+item.detalle+'</option>';
                }else{
                    sel = sel + '<option value="'+item.cod_unidad_medida+'">'+item.detalle+'</option>';
                }
                
            });
            sel = sel + "</select>";
            //alert(sel);
            //return sel;
        },
        complete: function(){
            //alert("Termino");
            return sel;
        }
    });
    
    //alert(sel);
    return sel;
    
}
function activaEditable(){
	//$('#mainTable').editableTableWidget().numericInputExample().find('td:first').focus();
	//$('#mainTable').editableTableWidget();
  	//$('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
  	delArticulo();
  	//dupliArticulo();
  	//addArticulo();
}
function ajax_addDetalle(cod_trans_detalle,detalle){
//    alert(":P "+cod_transaccion+" - "+gestion);
    $.ajax({
        type: 'post',
        url: '/capri-web/addDetalle.umsa',
        data:{cod_trans_detalle:cod_trans_detalle,detalle:detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            alert("exito!! "+json.detalle);
        }
    });
}
function ajax_loadDetalle(cod_trans_detalle){
//    alert(":P "+cod_transaccion+" - "+gestion);
    
    $.ajax({
        type: 'post',
        url: '/capri-web/loadDetalle.umsa',
        data:{cod_trans_detalle:cod_trans_detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json){
            var x=$("#dialog_detalle_item table tbody");
            x.empty();
            $.each(json.ListaDetalle, function(index, item){
                x.append("<tr data-cod_complemento = '"+item.cod_complemento+"'><td class = 'updateDetalle'><input value='"+item.detalle_solicitud+"' maxlength='75' size='50'/></td>\n\
                            <td><a href='#' class='delDetalle'><img class='img_peque' src='imagenes/minus.png'/></a></td></tr>");                          
            });
            DetalleControlaCambios();
            delDetalle();
            $("#dialog_detalle_item").data("cod_trans_detalle",cod_trans_detalle)
            $("#dialog_detalle_item").dialog("open");
            //alert("exito!! "+json.detalle);
        }
    });
}

function ajax_addItem(cod_transaccion,gestion, indice, unidad_medida){
//    alert("cod_transaccion: "+cod_transaccion+", gestion: "+gestion+", indice: "+indice+" ");
    $.ajax({
        type: 'post',
        url: '/capri-web/addItem.umsa',
        data:{cod_transaccion:cod_transaccion,gestion:gestion},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function(json) {
            //arma_resultados_clasificador();
            //alert("se adiciono con exito :D"+json.cod_trans_detalle);
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
            $('#mainTable').prepend('<tr data-cod_trans_detalle="'+json.cod_trans_detalle+'" >\n\
                    <td>'+indice+'</td>\n\
                    <td class="cantidad"><input required type="number" min="1" max="9999999" /></td>\n\
                    <td class="unidad_medida">'+unidad_medida+'</td>\n\
                    <td class="item"><input id="Input'+json.cod_trans_detalle+'" list="List'+json.cod_trans_detalle+'" required type="text" maxlength="55" size="65"/><datalist class="dataList" id="List'+json.cod_trans_detalle+'" ></datalist></td>\n\
                    <td id="Part'+json.cod_trans_detalle+'"></td>\n\
                    <td class="precio"><input size="10" type="text" placeholder="Ingrese precio" value="0"/></td>\n\
                    <td class="subtotal"> 0 </td>\n\
                    <td><a class="delArticulo" href="#"><img class="img_peque" src="imagenes/minus.png"/></a><a class="detailItem" href="#"><img class="img_peque" src="imagenes/detail.png"/></a></td></tr>');
            //$('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
            activaEditable();
            Controla_Cambios();
            Detalle();
//            Total();
        }
    });
}
function ajax_updateItem(){
    
    
    //alert(":P");
    //msj();
    //alert(":P "+$(this).val());
    var fila =$(this).parents(":eq(1)");
    var cod_trans_detalle = fila.attr('data-cod_trans_detalle');
    var cantidad=fila.children(".cantidad").find("input").val();
    var cod_unidad_medida = fila.children(".unidad_medida").find("select option:selected").val();
//    if(cod_unidad_medida==7){
//        $("#dialog_unidad_medida").dialog("open");
//    }
    var detalle = fila.children(".item").find("input").val();
    var precio_unitario = fila.children(".precio").find("input").val();
    
    
//    alert("precio_unitario --> "+precio_unitario);
//    var partida = $("#Part"+cod_trans_detalle).html();
//    alert("tamanio: "+partida.length);
//    if(partida.length!=0){
        //        alert("Vacio!!!!");
        //alert("Antes de..");
        var valInput = $('#Input'+cod_trans_detalle).val();
        var valDataL = $('#List'+cod_trans_detalle+' option').filter(function(){
            return this.value == valInput;
        });
        var partida = valDataL.data('partida');
        var cod_item = valDataL.data('cod_item');
        //alert("El cod_item: "+cod_item+" -- partida: "+partida);
//    }
//    alert("cod_trans_detalle: "+cod_trans_detalle+", cantidad: "+cantidad+" , cod_unidad_medida: "+cod_unidad_medida+", \n\
//     detalle: "+detalle+", precio_unitario: "+precio_unitario+", partida: "+partida+", cod_item: "+cod_item);
    if(typeof partida === "undefined"){
//        alert("vacio!!!");
        partida = $("#Part"+cod_trans_detalle).html();
    }else{
        $("#Part"+cod_trans_detalle).html(partida);
    }
//    alert("mi partida es : "+partida);
    
    //alert(partida);
//    var val = $('#Input'+cod_trans_detalle).val();
//    var xyz = $('#List'+cod_trans_detalle+' option').filter(function(){
//        return this.value == val;
//    });
//    var partida = xyz.data('partida');
//    var cod_item = xyz.data('cod_item');

//    alert("El cod_item: "+cod_item+" -- partida: "+partida);
    
    
   
    $.ajax({
        type: 'post',
        url: '/capri-web/updateItem.umsa',
        data:{
            cod_trans_detalle:cod_trans_detalle,
            cantidad:cantidad,
            cod_unidad_medida:cod_unidad_medida,
            detalle:detalle,
            partida:partida,
            cod_item:cod_item,
            precio_unitario:precio_unitario
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function() {
            Total();
            //arma_resultados_clasificador();
            //alert("se actualizo con exito :D");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
            //$("#Part"+cod_trans_detalle).html(partida);
//            alert("Hola :P");
        }
    });
}
function ajax_delItem(cod_trans_detalle){
//    alert(":P "+cod_transaccion+" - "+gestion);
    $.ajax({
        type: 'post',
        url: '/capri-web/delItem.umsa',
        data:{cod_trans_detalle:cod_trans_detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function() {
            //arma_resultados_clasificador();
            //alert("se elimino con exito :D");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
        }
    });
}
function ajax_delDetalle(cod_complemento,cod_trans_detalle){
//    alert(":P "+cod_transaccion+" - "+gestion);
    $.ajax({
        type: 'post',
        url: '/capri-web/delDetalle.umsa',
        data:{cod_complemento:cod_complemento,cod_trans_detalle:cod_trans_detalle},
        error: function(xhr, ajaxOptions, thrownError) {
            alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
        },
        success: function() {
            //arma_resultados_clasificador();
            //alert("se elimino con exito :D");
            //arma_resultados_clasificador(json,c,Limite,sw_paginacion,total_filas);
            //genera_lista_items(json);
        }
    });
}
function addArticulo(unidad_medida){
	/*var sel = '<select id="" name="">\n\
                    <option value="" selected>Seleccione</option>\n\
               </select>';*/
        //var i=1;
        //var tipo_item = getTipoItem();
        //var selUnidadMedida= getUnidadMedida();
        //alert(sel);
	$("#addArticulo").on("click",function(e){
            e.preventDefault();
            var index=parseInt($("#mainTable tbody tr").first().find("td:first-child").html());
            if(!isNaN(index)){
                //alert("nulo");
                index++;
                ajax_addItem($(this).attr('data-cod_transaccion'),$(this).attr('data-gestion'),index,unidad_medida);
            }
            else{
                ajax_addItem($(this).attr('data-cod_transaccion'),$(this).attr('data-gestion'),1,unidad_medida);
            }
            //ajax_addItem($(this).attr('data-cod_transaccion'),$(this).attr('data-gestion'),index,unidad_medida);
            //i++;
		
	});
}
function delArticulo(){
	$(".delArticulo").on("click",function(e){
            e.preventDefault();
            ajax_delItem($(this).parents(":eq(1)").attr('data-cod_trans_detalle'))
	    $(this).parents(":eq(1)").remove();
	    //alert("Hola :P");
	});
}
function detalleArticulo(){
	$(".detailItem").on("click",function(){
		//$(this).parents(":eq(1)").remove();
		alert("Hola :P");
	});
}
function delDetalle(){
	$(".delDetalle").on("click",function(e){
            e.preventDefault();
            ajax_delDetalle($(this).parents(":eq(1)").attr('data-cod_complemento'),$("#dialog_detalle_item").data("cod_trans_detalle"))
	    $(this).parents(":eq(1)").remove();
	    //alert("Hola :P");
	});
}
function dupliArticulo(){
	$(".dupliArticulo").on("click",function(){
		$('#mainTable').append('<tr class="success">\n\
			<td class="cantidad">'+7+'</td>\n\
			<td class="umedida">'+8+'</td>\n\
			<td class="titem">'+9+'</td>\n\
			<td class="item">'+10+'</td>\n\
			<td><a class="delArticulo" href="#">Eliminar</a><a class="dupliArticulo" href="#">Duplicar</a><a href="#">Detalle</a></td></tr>');
		$('#mainTable').editableTableWidget();
  	$('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
  	delArticulo();
		//$('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
		//activaEditable();
	});
}