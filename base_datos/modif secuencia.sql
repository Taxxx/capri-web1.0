CREATE OR REPLACE PROCEDURE setSequence (seqname IN VARCHAR2, newnumber IN INTEGER) as
curr_val INTEGER;
curr_inc INTEGER;
curr_min INTEGER;
BEGIN
SELECT INCREMENT_BY, MIN_VALUE into curr_inc, curr_min from user_sequences where sequence_name = upper(seqname);
EXECUTE IMMEDIATE 'ALTER SEQUENCE ' ||seqname||' MINVALUE ' || LEAST((newnumber - curr_inc - 1) , curr_min) ;
EXECUTE IMMEDIATE 'SELECT ' ||seqname ||'.nextval FROM dual' INTO curr_val;
IF (newnumber - curr_val - curr_inc) != 0 THEN
EXECUTE IMMEDIATE 'ALTER SEQUENCE ' ||seqname||' INCREMENT BY '||(newnumber - curr_val - curr_inc);
END IF;
EXECUTE IMMEDIATE 'SELECT ' ||seqname ||'.nextval FROM dual' INTO curr_val;
EXECUTE IMMEDIATE 'ALTER SEQUENCE ' ||seqname||' INCREMENT BY ' || curr_inc;
END setSequence;
/

exec setSequence ('SEC_COD_PROVEEDOR', 332);