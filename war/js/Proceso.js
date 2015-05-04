$(document).on("ready",inicia);
function inicia(){
    //alert(":D");
    botones();
}
function botones(){
    $(".boton_menu_proc").on("click",function(){
        //alert($(this).attr("data-tipo_id"));
        $(".info_proceso").hide();
        $("#"+$(this).attr("data-tipo_id")).show();
        
        //$("div:visible").hide();
        //$("div:hidden").show();
    });
}