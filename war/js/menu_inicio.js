/*alert("yeihhh1111 :D");
$("#capri_menu_cuerpo").load("inicio/capri_documentos.html");*/
$(document).on("ready",inicia);
function inicia(){
   /*$('#link_tuto').attr('href','/capri-web/login.umsa');
   $('#link_tuto').attr('target',"capri_menu_cuerpo");*/
   
   $('#link_docu').attr({
       href: '/capri-web/ver_docu.umsa?tipo=1',
       target: "capri_menu_cuerpo" 
   });
   $('#link_tuto').attr({
       href: '/capri-web/ver_tuto.umsa?tipo=2',
       target: "capri_menu_cuerpo" 
   });
   $('#link_otro').attr({
       href: '/capri-web/ver_otro.umsa?tipo=3',
       target: "capri_menu_cuerpo" 
   });
   $("#cuerpo_inicio nav ul #proc_1").on("click",add_lista_docs1);
   $("#cuerpo_inicio nav ul #proc_2").on("click",add_lista_docs2);
   $("#cuerpo_inicio nav ul #proc_3").on("click",add_lista_docs3);
}
function add_lista_docs1(){
    $("#cuerpo_inicio_doc nav ul").html("\n\
    <li>Doc1</li><li>Doc2</li><li>Doc3</li>");
}
function add_lista_docs2(){
    $("#cuerpo_inicio_doc nav ul").html("\n\
    <li>Doc3</li><li>Doc4</li><li>Doc5</li>");
}
function add_lista_docs3(){
    $("#cuerpo_inicio_doc nav ul").html("\n\
    <li>Doc6</li><li>Doc7</li><li>Doc8</li>");
}

    




