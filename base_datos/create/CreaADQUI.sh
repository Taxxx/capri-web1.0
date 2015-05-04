#!/bin/sh
#
# Copyright (c) 2004  by Proyecto MAFP
#
# NOMBRE
#   CREA_BD_10G.SH
# FUNCION
#   Funcion que crea una Base de Datos 10G
# NOTAS
#
# PARAMETROS DE ENTRADA
#
# MODIFICACIONES
#   Juan Pablo Yanez    09/09/2005    Creacion  
#
# Declaracion de variables globales
#
#DISPLAY=ADQUI.umsa.sigma.gov.bo:0.0; export DISPLAY
ORACLE_SID=ADQUI; export ORACLE_SID
ORACLE_BASE=/u01/app/oracle; export ORACLE_BASE
ORACLE_HOME=$ORACLE_BASE/product/10.1.0/db_1; export ORACLE_HOME   # lugar a ser modificado para la ruta donde se instalo oracle
ORA_NLS10=$ORACLE_HOME/nls/data; export ORA_NLS10
NLS_LANG=SPANISH_SPAIN.WE8ISO8859P1; export NLS_LANG
PATH=$ORACLE_HOME/bin:$PATH; export PATH
LANG=es_ES; export LANG

CAMINO=$ORACLE_BASE/admin/$ORACLE_SID/create; export CAMINO
DIR_FRA=/u05/flash_recovery_area; export DIR_FRA
CLAVE_SYS=manager; export CLAVE_SYS
CLAVE_SYSTEM=manager; export CLAVE_SYSTEM
CLAVE_SYSMAN=manager; export CLAVE_SYSMAN
CLAVE_DBSNMP=manager; export CLAVE_DBSNMP

rm /u01/app/oracle/oradata/$ORACLE_SID/*

#es recomendable crear manualmente las carpetas ya que se necesitan copiar y reconfigurar archivos 
#en algunas carpetas.

#mkdir /u01/app/oracle/admin/ADQUI #creamos la carpeta(instancia) de la nueva base de datos
mkdir /u01/app/oracle/admin/ADQUI/udump #creamos la carpeta(instancia) de la nueva base de datos
mkdir /u01/app/oracle/admin/ADQUI/bdump #creamos la carpeta(instancia) de la nueva base de datos
mkdir /u01/app/oracle/admin/ADQUI/cdump #creamos la carpeta(instancia) de la nueva base de datos
#mkdir /u01/app/oracle/admin/ADQUI/pfile #creamos la carpeta(instancia) de la nueva base de datos (archivo de inicio de sesion)
mkdir /u01/app/oracle/oradata/$ORACLE_SID

echo Add this entry in the oratab: $ORACLE_SID:$ORACLE_HOME:Y
$ORACLE_HOME/bin/orapwd file=$ORACLE_HOME/dbs/orapw$ORACLE_SID password=$CLAVE_SYS force=y

$ORACLE_HOME/bin/sqlplus /nolog @$ORACLE_BASE/admin/$ORACLE_SID/create/CreateDB.sql

#ES PARA LA CREACION DE LOS TABLE SPACE (SIGMA) SE LO PUEDE ADECUAR A UNA NUEVA DB QUE SE ESTA CREANDO
#$ORACLE_HOME/bin/sqlplus /nolog @$ORACLE_BASE/admin/$ORACLE_SID/create/CreateDBFiles.sql

#ESTA CONSULTA EJECUTAR LUEGO QUE SE COPIE MANUALMENTE LOS CONTROL FILES A ESA CARPETA
$ORACLE_HOME/bin/sqlplus /nolog @$ORACLE_BASE/admin/$ORACLE_SID/create/CreateDBCatalog.sql

#$ORACLE_HOME/bin/sqlplus /nolog @$ORACLE_BASE/admin/$ORACLE_SID/create/emRepository.sql   #solo para sigma
#$ORACLE_HOME/bin/sqlplus /nolog @$ORACLE_BASE/admin/$ORACLE_SID/create/postDBCreation.sql #solo para sigma
