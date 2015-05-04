connect / as SYSDBA
set echo on
spool /u01/app/oracle/admin/ADQUI/create/CreateDBCatalog.log
@/u01/app/oracle/product/10.1.0/db_1/rdbms/admin/catalog.sql;
@/u01/app/oracle/product/10.1.0/db_1/rdbms/admin/catblock.sql;
@/u01/app/oracle/product/10.1.0/db_1/rdbms/admin/catproc.sql;
@/u01/app/oracle/product/10.1.0/db_1/rdbms/admin/catoctk.sql;
connect SYSTEM/manager
@/u01/app/oracle/product/10.1.0/db_1/sqlplus/admin/pupbld.sql;
connect SYSTEM/manager
set echo on
spool /u01/app/oracle/admin/ADQUI/create/sqlPlusADQUI.log
@/u01/app/oracle/product/10.1.0/db_1/sqlplus/admin/help/hlpbld.sql helpus.sql;
spool off
spool off
exit
