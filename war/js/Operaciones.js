$(document).on("ready",inicia);
function inicia(){
    tabs();
    botones();
    //alert("Hola");
    $("#dialog_detalle_env").dialog({
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
    /*$("#dialog_poa").dialog({
        autoOpen: false,
        width:400,
        height:'auto',
        modal: true,
        buttons: {
            "Ok": function() {
                $( this ).dialog("close");
            }
        }
    });*/

    $(".btn_detalle_env").on("click",function(){
        $.ajax({
            type: 'post',
            url: '/capri-web/GetEnviadosDetalle.umsa',
            data:{cod_transaccion:$(this).attr('data-cod_transaccion')},
            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            },
            success: function(json) {
                //alert("Exito");
                var x=$("#dialog_detalle_env table tbody");
                x.empty();
                $.each(json.Transaccion, function(index, item){
                    x.append("<tr><td>"+item.cuce+"</td><td>"+item.articulo+"</td><td>"+item.estado+"</td></tr>");
                });
                $("#dialog_detalle_env").dialog( "open" );
            }
        });

    });
}
function tabs(){
    $( "#tabs" ).tabs();
}
function botones(){
    $("#btn-poa").on("click",function(){
        //alert("Changos Charangos");
        //$("#dialog_poa").dialog( "open" );
        
        $.ajax({
            type: 'post',
            url: '/capri-web/GetPOA.umsa',
            //data:{cuce:$(this).attr('data-cuce')},
            dataType: 'json',
            error: function(xhr, ajaxOptions, thrownError) {
                alert(":( ERROR: "+xhr+" "+ajaxOptions+" "+thrownError);
            },
            success: function(json) {
                alert("Exito");
                var x=$("#dialog_poa table tbody");
                x.empty();
                $.each(json.POA, function(index, item){
                    x.append("<tr><td>"+item.OBJETO_DEL_GASTO+"</td><td>"+item.DESC_OBJETO_DEL_GASTO+"</td><td>"+item.FUENTE+"</td><td>"+item.ORGANISMO+"</td><td>"+item.PPTO_INICIAL+"</td><td>"+item.TRASP+"</td><td>"+item.MODIFICACIONES_PRESUPUESTALES+"</td><td>"+item.DEVENGADO+"</td><td>"+item.SALDO_POR_PREVENIR+"</td></tr>");
                });
                $("#dialog_poa").dialog( "open" );
            }
        });
    });
}