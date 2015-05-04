<%-- 
    Document   : informacion
    Created on : 20-03-2014, 04:30:32 PM
    Author     : UMSA-JES
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.js"></script>
        <script src="js/Proceso.js"></script>
        
        <link rel="stylesheet" type="text/css" href="css/main_style.css"/>
    </head>
    <body>
        <div id="menu_procedimiento">
            <nav>
                <ul>
                    <li><a data-tipo_id="proceso1" class="boton_menu_proc" href="#">Contratación Menor</a></li>
                    <li><a data-tipo_id="proceso2" class="boton_menu_proc" href="#">ANPE</a></li>
                    <li><a data-tipo_id="proceso3" class="boton_menu_proc" href="#">Licitación Publica</a></li>
                    <li><a data-tipo_id="proceso4" class="boton_menu_proc" href="#">Contratación por Excepción</a></li>
                    <li><a data-tipo_id="proceso5" class="boton_menu_proc" href="#">Contratación Directa</a></li>
                    <!--
                    <li><a data-tipo_id="proceso4" class="boton_menu_proc" href="#">Convocatoria Publica Nacional</a></li>
                    <li><a data-tipo_id="proceso5" class="boton_menu_proc" href="#">Convocatoria Publica Internacional</a></li>
                    <li><a data-tipo_id="proceso6" class="boton_menu_proc" href="#">Contratación por Excepción</a></li>
                    -->
                </ul>
            </nav>
        </div>
        <div id="proceso_derecha">
            <div class="info_proceso">
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>BIENVENIDO</h4>
                </hgroup>
                
                <article>
                    <p>
                        Bienvenidos a la seccion de Procedimientos del Sistema de Adquisiciones de la Universidad Mayor de San Andres
                        (U.M.S.A.), en esta seccion podra consultar informacion acerca de los diferentes procesos de contratación para
                        Bienes, Obras, Servicios Generales y Servicios de Consultoría.
                    </p>
                     <p>
                        Ademas podra generar diferentes formularios, para los distintos tipos de procesos, dentro la Universidad.
                    </p>
                </article>
            </div>
            <div class="info_proceso" id="proceso1" hidden>
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>MODALIDAD CONTRATACIÓN MENOR</h4>
                </hgroup>
                
                <article>
                    <p>De Bs. 1.- (Un 00/100 Boliviano) hasta Bs. 50.000.- (Cincuenta mil 00/100 Bolivianos).-</p>
                    <p>
                        Las contrataciones dentro la modalidad de Contratación Menor, en el <span>ÁREA CENTRAL</span>, es la siguiente:
                    </p>
                     <p>
                        Las Contrataciones Menores, hasta Bs. 50.000.-, no requieren propuestas, se realizarán según el siguiente procedimiento:
                    </p>
                    <ol>
                        <li>
                            La Unidad Solicitante, adjunta a la Hoja de Ruta una proforma referencial del bien y en caso de servicios
                            que requieran de personal clave o calificado identificado, el Curriculum Vitae del personal a contratar, realizando su requerimiento al RPA instruya la emisión
                            de la Certificación Presupuestaria y continuar con el trámite.
                            <!--<p>Holia}tas</p>-->
                        </li>
                        <li>
                            El RPA autoriza el proceso de contratación previa verificación de la documentación presentada por la unidad solicitante
                            , con esta autorización la División de Adquisiciones realiza el trámite administrativo y emite la orden de compra y/u de
                            servicio con lo cual notifica al proponente seleccionado por la Unidad Solicitante (de conformidad al D.S. 956). Cuando 
                            la entrega del bien o prestación del servicio exceda a los 15 dias calendario o cuando corresponda por el objeto de contratación ,
                            el proceso será remitido al Departamento de Asesoría Jurídica para la elaboración del contrato.
                        </li>
                        <li>
                            El proveedor deberá presentar su Certificado RUPE como requisito indispensable, (D.S. 1497) en  las contrataciones entre Bs. 20.000.- y 
                            Bs. 50.000.-
                        </li>
                        <li>
                            Registrar en el SICOES las contrataciones menores cuyo precio referencial se encuentre entre Bs. 20.000.- y Bs. 50.000., una vez suscrito 
                            el contrato o la orden de compra u orden de servicio.
                        </li>
                        <li>
                            El responsable de recepción emitirá el acta de recepción y de conformidad.
                        </li>
                        <li>
                            La Unidad Solicitante, remitirá al encargado de almacén central el formulario de pedido de materiales más nota de conformidad en 
                            caso de tratarse de activos fijos, y en caso necesario incluirá informe técnico (Adquisición de equipos de computación).
                        </li>
                    </ol>
                </article>
            </div>
            <div class="info_proceso" id="proceso2" hidden>
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>MODALIDAD APOYO NACIONAL A LA PRODUCCIÓN Y EMPLEO - ANPE</h4>
                </hgroup>
                
                <article>
                    <p>Mayor a Bs. 50.000.- (Cincuenta mil 00/100 Bolivianos) hasta Bs. 1.000.000.- (Un millón 00/100 Bolivianos).-</p>
                    <ol>
                        <li>
                            La Unidad Solicitante, realizará su requerimiento al RPA, del Área Central
                            o del Área Desconcentrada adjuntando los siguientes documentos en la carpeta.
                            <nav class="lista_anidada">
                                <ol type="a">
                                    <li>Formulario de solicitud de compra (solo Bienes).</li>
                                    <li>Especiicaciones Técnicas del bien o servicios a contratar.</li>
                                    <li>Términos de referencia (consultorías y otros servicios no personales)</li>
                                    <li>Precio referencial (en base a una contización)</li>
                                    <li>Planos y diseños estructurales (para Obras)</li>
                                    <li>Presupuesto oficial aprobado por el Depto de infraestructura (para Obras)</li>
                                    <li>Certificación Presupuestaria</li>
                                </ol>
                            </nav>
                            
                        </li>
                        <li>
                           La Unidad Administrativa (División de Adquisiciones o Jefatura del Área Desconcentrada)
                           elaborará, con base en los modelos establecidos por el Órgano Rector, el Documento Base 
                           de Contratación - DBC, incluyendo las Especificaciones Técnicas o Términos de Referencia 
                           elaborados por la Unidad Solicitante.
                        </li>
                        <li>
                            El RPA aprueba el DBC y autoriza el inicio del proceso de contratación mediante Resolución 
                            Administrativa expresa, verificando previamente la documentación y se designa a los integrantes 
                            de la  Comisión de Calificación o Responsable de Evaluación mediante Notas Oficiales.
                        </li>
                        <li>
                            La División de Adquisiciones o Jefatura del Área Desconcentrada reportan la inormación en linea 
                            al SICOES del Formulario 100 sobre el inicio y publicación del proceso de contratación.
                        </li>
                        <li>
                            Si el Cronograma de actividades lo establece, la unidad solicitante efectúa la inspección previa 
                            en los plazos establecidos, realiza las consultas escritas a la Unidad Administrativa por los potenciales 
                            proponentes, sobre el contenido del DBC y realiza la Reunión de aclaración del DBC. En este caso el RPA 
                            aprobará mediante Resolución administrativa expresa el DBC con las enmiendas y aclaraciones.
                        </li>
                        <li>
                            Cuando se requiera la aclaración de una o más propuestas se podrán convocar a todos los proponentes que 
                            presentaron propuestas, siempre y cuando dichas aclaraciones no modifiquen la propuesta y no concedan ventajas 
                            indebidas en detrimetro de los otros proponentes.
                        </li>
                        <li>
                            El Responsable de Evaluación a la Comisión de Calificación, procederá con la evaluación las cotizaciones o propuestas 
                            y elabora el informe de Evaluación y recomendación de Adjudicación o declaratoria desierta, documento que debe estar 
                            debidamente suscrito y será remitido al RPA.
                        </li>
                        <li>
                            El RPA previa revisión y evaluación del informe, adjudica o declara desiertto el proceso de contratación mediante Resolución 
                            administrativa.
                        </li>
                        <li>
                            Realizada la adjudicación, se notificará al proponente y se le solicitará la presentación de la documentación requerida para la suscripción 
                            del contrato, orden de servicio y/u orden de compra, en original o fotocopia legalizada considerando para tal efecto los plazos y el vencimiento 
                            del plazo para la interposición de Recursos Administrativos de Impugnación.
                        </li>
                        <li>
                            Paralelamente, el RPA solicita al Asesor Legal elabore al Contrato a la Unidad Administrativa (División de Adquisiciones o el Área desconcentrada) 
                            elabore la Orden de Compra y/u Orden de Servicio.
                        </li>
                        <li>
                            La Unidad Administrativa (División de Adquisiciones o el Área desconcentrada) remite al SICOES el formulario 200 de adjudicación o declaratoria desierta 
                            del proceso; y registra en linea a la Contraloría General del Estado.
                        </li>
                        <li>
                            La recepción de bienes, obras, servicios generales o servicios de consultoría contratados, se realizará por la Unidad Solicitante mediante acta de recepción 
                            correspondiente. En el caso de consultorías debe existir el informe final y conormidad emitido por el responsable de la unidad solicitante o la contraparte.
                        </li>
                    </ol>
                </article>
            </div>
            <div class="info_proceso" id="proceso3" hidden>
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>Licitación Publica</h4>
                </hgroup>
                
                <article>
                    <p>
                        Mayor a Bs. 1.000.000.- (Un millón 00/100 Bolivianos).-
                    </p>
                    <p>
                        Se designará como RPC: al Director Administrativo Financiero (DAF) en el Área Central, alos Decanos, Directores de Programas 
                        de Postgrados: CIDES y CEPIES; DIPGIS, IDR, Director del Canal 13TVU y el IDRU-CCI.
                    </p>
                    <p>
                        El RPC, designado por la MAE mediante Resolución Rectoral, es el responsable de las contrataciones de bienes y 
                        servicios, bajo la modalidad de Licitación Pública, sus funciones están establecidas en el Árticulo 33.- del Decreto Supremo 
                        No 0181.
                    </p>
                    <hgroup>
                        <h4>PROCESO DE CONTRATACIÓN EN LA MODALIDAD DE LICITACIÓN PÚBLICA</h4>
                        <h5>Condiciones Previas para iniciar los procesos de contratación</h5>
                    </hgroup>
                    
                    <p>La Unidad Solicitante, realizará su requerimiento al RPC, luego de elaborar y adjuntar la siguiente documentación:</p>
                    <nav>
                        <ol>
                            <li>Solicitud de Contratación</li>
                            <li>
                                En caso de Obras, debe contarse con los estudios de pre-inversión (Estudio geológico - geotécnico, proyecto)
                                arquitectónico aprobado por el GAMLP, proyecto de instalaciones eléctricas, topográfico, instalaciones hidrosanitarias, 
                                proyecto estructural y si corresponde proyecto de gas industrial).
                            </li>
                            <li>Certificación Presupuestaria</li>
                            <li>
                                Contar con Resolución expresa de aprobación del Comité Ejecutivo del Honorable Consejo Universitario de Bs. 1.000.001.- 
                                hasta Bs. 40.000.000.- y Resolución del Honorable Consejo Universitario de Bs. 40.000.001.- para adelante.
                            </li>
                            <li>
                                Documento Base de Contratación (DBC). conteniendo las especificaciones técnicas o términos de referencia.
                            </li>
                        </ol>
                    </nav>
                    <h5>Procedimiento de contratación</h5>
                    <nav>
                        <ol>
                            <li>El RPC, autoriza el inicio del proceso de contratación y aprueba el DBC.</li>
                            <li>
                                Recibida la autorización de inicio del proceso de contratación y la aprobación del DBC (incluyendo las Garantías a primer requerimiento), 
                                la Unidad Administrativa (División de Adquisiciones o Área Desconcentrada) publica la convocatoria y el DBC en el SICOES, Mesa de Partes, página WEB de la UMSA y a requerimiento del 
                                Área Solicitante, en algún(os) medio(s) de prensa escrita.
                            </li>
                            <li>
                                En los casos que se considere pertinente de acuerdo a las características de la contratación, el Área Solicitante y los potenciales proponentes efectúan la inspección 
                                previa en el plazo establecido en el cronograma de actividades del proceso de contratación.
                            </li>
                            <li>
                                Los potenciales proponentes podrán formular consultas escritas dirigidas al RPC, sobre el contenido del DBC, hasta la hora y fecha establecidas 
                                en el cronograma de actividades del proceso de contratación.
                            </li>
                            <li>
                                La Unidad Solicitante, la Unidad Administrativa o Asesoria Legal, según corresponda, preparan las respuestas y realizarán la reunión de aclaración del DBC, en la hora y fecha establecidas 
                                en el cronograma de actividades, dónde se darán a conocer las mismas e incorporando en el Acta de la Reunión las respuestas correspondientes.
                            </li>
                            <li>
                                Realizada la reunión de aclaración, la Unidad Administrativa (División de Adquisiciones o Área Desconcentrada) envía los antecedentes del proceso y las enmiendas si corresponde, a Asesoría Legal a 
                                efectos de la elaboración de la Resolución de Aprobación del DBC.
                            </li>
                            <li>
                                División de Adquisiciones en el Área Central y las Áreas Desconcentradas en las Facultades elaborará el Proyecto de Resolución y remite el mismo al RPC, para su suscripción.
                            </li>
                            <li>
                                La Unidad Administrativa, una vez recibida la Resolución, procede a su notificación via correo electrónico y/o fax y a través de la publicación en el SICOES.
                            </li>
                            <li>
                                El RPC designa a los integrantes de la Comisioón de Calificación.
                            </li>
                            <li>
                                La Unidad Administrativa (División de Adquisiciones o el Área Desconcentrada) recibirán la propuestas hasta la fecha y hora establecida para su presentación, registrándolas en el libro habilitado para el efecto, documento 
                                que se constituirá en el comprobante de recepción respectivo.
                            </li>
                            <li>
                                Inmediatamente después de la recepción de propuestas, la Comisión de Calificación efectúa la apertura de sobres en un acto público, en la fecha y hora determinada en la 
                                convocatoria y luego en sesión reservada realiza la evaluación de propuestas.
                            </li>
                            <li>
                                Cuando se requiera la aclaración de una o más propuestas se podrán convocar a todos los proponentes que presentaron propuestas, siempre y cuando dichas 
                                aclaraciones no modifiquen la propuesta y no concedan ventajas indebidas en detrimento de los otros proponentes.
                            </li>
                            <li>
                                Una vez emitido el informe de Evaluación y Recomendación de Adjudicación o Declaratoria Desierta, la Comisión de Calificación lo remite al RCP.
                            </li>
                            <li>
                                En caso de no estar de acuerdo con la recomendación, el RCP mediante nota requerirá a la Comisión revise la misma, para que en Informe Complementario 
                                sustente o complemente su recomendación; recibido el informe complementario, el RCP adjudica o declara desierto el proceso o puede, bajo su exclusiva 
                                responsabilidad apartarse de la recomendación, para lo cual deberá elaborar un informe fundamentado dirigido a la MAE y a la Contraloria General del Estado.
                            </li>
                            <li>
                                División de Adquisiciones en el Área Central y las Áreas Desconcentradas en las Facultades elabora el Proyecto de Resolución de Adjudicación o Declaratoria Desierta 
                                y remite el mismo al RCP, para su suscripción.
                            </li>
                            <li>
                                Recibida la Resolución respectiva, la Unidad Administrativa procede a su notiicacion vía regular, correo electrónico o fax y a través de la publicación en el SICOES. En 
                                la misma se solicitará, al proponente adjudicado, la presentación de la documentación requerida para la suscripción del contrato en original o fotocopia legalizada, contemplando 
                                los plazos para tal efecto y el vencimiento del plazo para la interposición de Recursos Administrativos de Impugnación.
                            </li>
                            <li>
                                La Unidad Administrativa (División de Adquisiciones o Área Desconcentrada), remite la documentación a Asesoría Legal y solicita la elaboración del contrato, revisa la legalidad de 
                                la documentación presentada por el proponente adjudicado, elabora el contrato y gestiona las firmas respectivas. Deberá considerarse el contenido del articulo 88 de las NB-SABS.
                            </li>
                            <li>
                                La Unidad Administrativa, recibido el Contrato, informa al SICOES mediante el Formulario 200, asimismo, remite un ejemplar al proveedor, otro al Área Solicitante (requiriendo el seguimiento a la ejecución del mismo y la remisión del 
                                Formulario 500 del SICOES).
                            </li>
                        </ol>
                    </nav>
                </article>
            </div>
            <div class="info_proceso" id="proceso4" hidden>
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>Convocatoria Publica Nacional</h4>
                </hgroup>
                
                <article>
                    <hgroup>
                        <h4>MODALIDAD DE CONTRATACIÓN POR EXCEPCIÓN</h4>
                        <h5>RESPONSABLE DE CONTRATACIÓN POR EXEPCIÓN</h5>
                    </hgroup>
                    <p>
                        El responsable de la Contratación por Excepción es el (la) señor(a) RECTOR(A) de la Universidad Mayor de San Andrés.
                    </p>
                    <h5>PROCESO DE CONTRATACIÓN POR DESASTRE Y/O EMERGENCIAS</h5>
                    <p>
                        Las Contrataciones por Desastre y/o Emergencias serán realizadas conforme dicte la Resolución de Declaratoria de Desastre y/o Emergencias, conforme 
                        la Ley No 2140, de 25 de octubre de 2000, para la Reducción de Riesgos y Atención de Desastres.
                    </p>
                    <p>
                        Las contrataciones por Desastre y/o Emergencias serán realizadas conforme dicta la Resolución de Declaratoria de Desastre y/o Emergencias.
                    </p>
                    <hgroup>
                        <h4>MODALIDAD DE CONTRATACIÓN DIRECTA DE BIENES Y SERVICIOS</h4>
                        <h5>RESPONSABLE DE CONTRATACIÓN DIRECTA DE BIENES Y SERVICIOS</h5>
                    </hgroup>
                    <p>
                        El responsable de la Contratación Directa de Bienes y Servicios es el RPC o el RPA designado de acuerdo con lo establecido en los Articulos 12 y 14 
                        del presente Reglamento Específico.
                    </p>
                    <p>
                        El responsable de la Contratación Directa de Bienes y Servicios es el RPA o el RPC designado de acuerdo al monto de la contratación.
                    </p>
                    <p>La Contratación Directa de Biemes y Servicios será realizada de acuerdo con el siguiente proceso:</p>
                    <nav>
                        <ol>
                            <li>
                                Para la contratación de servicios públicos, imciso b) del artículo 72 del Decreto Supremo No 0181, se procederá a pagar de forma periódica o 
                                mensual, el importe respectivo al consumo efectuado. Cuando corresponda, se suscribirá el contrato de adhesión.
                            </li>
                            <li>
                                Para la contratación de servicios públicos, inciso a) del artículo 72 del Decreto Supremo No 0181, se procederá de la siguiente manera:
                                <nav class="lista_anidada">
                                    <ol type="a">
                                        <li>Requerimiento de la contratación por Unidad Solicitante, conteniendo las Especificaciones técnicas y la justificación de la contratación.</li>
                                        <li>
                                            Emisión de la Certificación Presupuestaria debidamente firmada por la Unidad Administrativa.
                                        </li>
                                        <li>
                                            El RPA o RPC autoriza la contratación.
                                        </li>
                                        <li>
                                            La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), realiza el proceso de contratación directa.
                                        </li>
                                        <li>
                                            Adjudicada la contratación, la Unidad Administrativa procede a la notificación solicitando al 
                                            proponente adjudicado, la presentación de la documentación para firma del Contrato.
                                        </li>
                                        <li>
                                            La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), remite la documentación a Asesoría Legal y solicita la elaboración del 
                                            contrato.
                                        </li>
                                        <li>
                                            La Unidad Administrativa una vez suscrito el contrato, lo remite junto con los antecedentes a la Unidad 
                                            Solicitante para el seguimiento a la ejecución de la contratación y la remisión del formulario 400 del SICOES.
                                        </li>
                                        <li>
                                            La Unidad Administrativa una vez suscrito el contrato, lo remite junto con los antecedentes a la Unidad Solicitante para el seguimiento a la ejecución 
                                            de la contratación y la remisión del formulario 400 del SICOES.
                                        </li>
                                    </ol>
                                </nav>
                            </li>
                            <li>
                                Con relación a lo establecido en los incisos c) Medios de comunicación, d) Arrendamoento de inmuebles para funcionamiento de centros educativos, 
                                e) Arrendamiento de inmuebles para funcionamiento de oficinas y g) Suscripción a medios de comunicación del citado artículo 72, se procederá de la siguiente manera:
                                <nav class="lista_anidada">
                                    <ol type="a">
                                        <li>El RPC o RPA autoriza el inicio de la contratación, firmando la solicitud de inicio del proceso.</li>
                                        <li>
                                            La Unidad Administrativa elabora el DBC y la(s) invitación(es) y la(s) remite al(los) potencial(es) proponente(s).
                                        </li>
                                        <li>
                                            En caso de que el requerimiento de la Unidad Solicitante, especifique a que proveedor debe contratarse, se deberá aplicar 
                                            directamente el procedimiento a partir del paso señalado en el inciso i) del presente numeral.
                                        </li>
                                        <li>
                                            La Unidad Administrativa recibe la(s) propuesta(s) y remite las mismas a la Unidad Solicitante para su correspondiente 
                                            evaluación.
                                        </li>
                                        <li>
                                            La Unidad Solicitante elabora el informe de Evaluación y lo presenta al responsable del proceso de contratación.
                                        </li>
                                        <li>
                                            Una vez recibido el Informe de Evaluación, el RPC o RPA adjudica o declara desierto, según correspondiere, el 
                                            proceso mediante proveído.
                                        </li>
                                        <li>
                                            Realizada la adjudicación, la Unidad Administrativa procede a la notiicación sobre los resultados del proceso, solicitando al 
                                            proponente adjudicado, la presentación de la documentación para firma del Contrato, Orden de Compra u Orden de Servicio.
                                        </li>
                                        <li>
                                            La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), remite la documentación a Asesoría Legal y solicita 
                                            la elaboración del contrato.
                                        </li>
                                        <li>
                                            La Unidad Administrativa elabora la Orden de Compra u Orden de Servicio y obtiene las firmas correspondientes. Una vez suscritos la remite junto con los antecedentes a la 
                                            Unidad Solicictante para el seguimiento a la ejecución de la contratación y la remisión del formulario 400 del SICOES.
                                        </li>
                                    </ol>
                                </nav>
                            </li>
                            <li>
                                Con relación a lo establecido en el inciso f) Adquisición de pasajes aéreos, del artículo 72 del Decreto Supremo N° 0181, se procederá de la siguiente manera:
                                <nav class="lista_anidada">
                                    <ol type="a">
                                        <li>Requerimiento de la contratación por Unidad Solicitante.</li>
                                        <li>
                                            Emisión de la Certificación Presupuestaria debidamente firmada por la Unidad Administrativa.
                                        </li>
                                        <li>
                                            Invitación directa al proveedor.
                                        </li>
                                        <li>
                                            El RPA o RCP autoriza la compra directa del(los) repuesto(s) del proveedor.
                                        </li>
                                        <li>
                                            La Unidad Solicitante realiza el seguimiento y verificación a la entrega de los repuestos correspondientes.
                                        </li>
                                    </ol>
                                </nav>
                            </li>
                        </ol>
                    </nav>
                    <p>
                        Una vez formalizada la contratación, la información de la contratación será presentada 
                        a la Contraloría General del Estado y registrada en el SICOES.
                    </p>
                </article>
            </div>
            <div class="info_proceso" id="proceso5" hidden>
                <hgroup>
                    <h3>Procedimientos</h3>
                    <h4>Modalidad de Contratación directa de bienes y servicios.</h4>
                </hgroup>
                
                <article>
                    <h5>Responsable de Contratación directa de bienes y servicios</h5>
                    <p>
                        El responsable de la Contratación Directa de Bienes y Servicios es el RPC o el RPA designado 
                        de acuerdo con lo establecido en los Articulos 12 y 14 del presente Reglamento Específico.
                    </p>
                     <p>
                       El responsable de la Contratación Directa de Bienes y Servicios es el RPA o el RPC designado de 
                       acuerdo al monto de la contratacíon.
                    </p>
                    <p>
                        La Contratación Directa de Bienes y Servicios será realizada de acuerdo con el siguiente proceso:
                    </p>
                    <ol>
                        <li>
                            Para la contratación de servicios públicos, inciso b) del artículo 72 del Decreto Supremo N° 0181, 
                            se procederá a pagar de forma periódica o mensual, el importe respectivo al consumo efectuado. Cuando 
                            corresponda, se suscribirá el contrato de adhesión.
                        </li>
                        <li>
                            Para la contratación de bienes referidos en el insico a) del articulo 72 del Decreto Supremo N° 0181, se 
                            procederá de la siguiente manera:
                            <nav class="lista_anidada">
                                <ol type="a">
                                    <li>
                                        Requerimiento de la contratación por Unidad Solicitante, conteniendo las Especificaciones técnicas y 
                                        la justificación de la contratación.
                                    </li>
                                    <li>
                                        Emisión de la Certificación Presupuestaria debidamente firmada por la Unidad Administrativa.
                                    </li>
                                    <li>
                                        El RPA o RPC autoriza la contratación.
                                    </li>
                                    <li>
                                        La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), realiza el proceso de contratación directa.
                                    </li>
                                    <li>
                                        Adjudicada la contratación, la Unidad Administrativa procede a la notificación solicitando al proponente adjudicado, 
                                        la presentación de la documentación para firma del contrato.
                                    </li>
                                    <li>
                                        La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), remite la documentación 
                                        a Asesoria Legal y solicita la elaboración del contrato.
                                    </li>
                                    <li>
                                        La Unidad Administrativa una vez suscrito el contrato, lo remite junto con los antecedentes a la Unidad Solicitante 
                                        para el seguimiento a la ejecución de la contratación y la remisión del formulario 400 del SICOES.
                                    </li>
                                </ol>
                            </nav>
                        </li>
                        <li>
                            Con relación a lo establecido en los incisos c) Medios de comunicación, d) Arrendamiento de inmuebles para funcionamiento de 
                            oficinas y g) Suscripción a medios de comunicación del citado articulo 72, se procederá de la siguiente manera:
                            <nav class="lista_anidada">
                                <ol type="a">
                                    <li>
                                        El RCP o RPA autoriza el inicio de la comunicación, firmando la solicitud de inicio del proceso.
                                    </li>
                                    <li>
                                        La Unidad Administrativa elabora el DBC y la(s) invitacion(es) y la(s) remite al(los) potencial(es) proponente(s).
                                    </li>
                                    <li>
                                        En caso de que el requerimiento de la Unidad Solicitante, espedifique a que proveedor debe contratarse, se deberá aplicar 
                                        directamente el procedimiento a partir del paso señalado en el inciso i) del presente numeral.
                                    </li>
                                    <li>
                                        La Unidad Administrativa recibe la(s) propuesta(s) y remite las mismas a la Unidad Solicitante para su 
                                        correspondiente evaluación.
                                    </li>
                                    <li>
                                        La Unidad Solicitante elabora el informe de Evaluación y lo presenta al responsable del proceso de contratación.
                                    </li>
                                    <li>
                                        Una vez recibido el informe de Evaluación, el RCP o RPA adjudica o declara desierto, según correspondiere, el proceso 
                                        mediante proveido.
                                    </li>
                                    <li>
                                        Realizada la adjudicación, la Unidad Administrativa procede a la notificación sobre los resultados del proceso, 
                                        solicitando al proponente adjudicado, la presentación de la documentación para firma del Contrato, Orden de Compra u Orden 
                                        de Servicio.
                                    </li>
                                    <li>
                                        La Unidad Administrativa (División de adquisiciones o Área Desconcentrada), remite la documentación a Asesoría Legal y 
                                        solicita la elaboración del contrato.
                                    </li>
                                    <li>
                                        La Unidad Administrativa elabora la Orden de Compra u Orden de Servicio y obtiene las firmas correspondientes. Una vez 
                                        suscritos la remite junto con los antecedentes a la Unidad Solicitante para el seguimiento a la ejecución de la contratación 
                                        y la remisión del formulario 400 del SICOES.
                                    </li>
                                </ol>
                            </nav>
                        </li>
                        <li>
                            Con relación a lo establecido en el inciso f) Adquisición de pasajes aéreos, del artículo 72 del decreto Supremo N° 0181, se procederá 
                            de la siguiente manera:
                            <nav class="lista_anidada">
                                <ol type="a">
                                    <li>
                                        Requerimiento de la contratación por Unidad Solicitante. 
                                    </li>
                                    <li>
                                        Emisión de la Certificación Presupuestaria debidamente firmada por la Unidad Administrativa. 
                                    </li>
                                    <li>
                                        El RPA o RPC autoriza la compra directa de pasajes.
                                    </li>
                                </ol>
                            </nav>
                        </li>
                        <li>
                            Con relación al inciso h) Adquisición de repuestos, del artículo 72 del Decreto Supremo N° 0181, se procederá de la siguiente manera:
                            <nav class="lista_anidada">
                                <ol type="a">
                                    <li>
                                        Requerimiento de la contratación por Unidad Solicitante. 
                                    </li>
                                    <li>
                                        Emisión de la Certificación Presupuestaria debidamente firmada por la Unidad Administrativa. 
                                    </li>
                                    <li>
                                        Invitación directa al proveedor.
                                    </li>
                                    <li>
                                        El RPA o RPC autoriza la compra directa del(los) repuesto(s) del proveedor.
                                    </li>
                                    <li>
                                        La Unidad Solicitante realiza el seguimiento y verificación a la entrega de los repuestos correspondientes.
                                    </li>
                                </ol>
                            </nav>
                        </li>
                    </ol>
                    <p>
                        Una vez formalizada la contratación, la información de la contratación será presentada a la Contraloría General del Estado y registrada en el SICOES.
                    </p>
                </article>
            </div>
            <div class="info_proceso" id="proceso6" hidden>
                <hgroup>
                    <h3>PROCEDIMIENTO</h3>
                    <h4>Contratación por Excepción</h4>
                </hgroup>
                
                <article>
                    <p>
                        Bienvenidos a la seccion de Procedimientos del Sistema de Adquisiciones de la Universidad Mayor de San Andres
                        (U.M.S.A.), en esta seccion podra consultar informacion acerca de los diferentes procesos de contratación para
                        Bienes, Obras, Servicios Generales y Servicios de Consultoría.
                    </p>
                     <p>
                        Ademas podra generar diferentes formularios, para los distintos tipos de procesos, denntro la Universidad.
                    </p>
                    
                </article>
            </div>
        </div>
    </body>
</html>
