#
# toRows procedure. The procedure create a temp table that is filled splitting each element of the column. To access
# data from each row a cursor is used.
#
# Tested using mysql 5.7.12
#
# @author Juan Rada
#
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE toRows()
BEGIN
    DECLARE V_ID VARCHAR(200);
    DECLARE V_NAME VARCHAR(200);
    DECLARE TIMES INT;
    DECLARE DONE INT DEFAULT FALSE;

    DECLARE TBL_CUR CURSOR FOR select ID, NAME From SOMETBL;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = TRUE;

    CREATE TEMPORARY TABLE TEMP_SOMETBL(ID INT, NAME VARCHAR(50));

    OPEN TBL_CUR;
    READ_LOOP: LOOP
        FETCH TBL_CUR INTO V_ID, V_NAME;

        IF DONE THEN
            LEAVE READ_LOOP;
        END IF;

        SET TIMES = LENGTH(V_NAME) - LENGTH(REPLACE(V_NAME, '|', '')) + 1;

        WHILE(TIMES > 0) DO
            INSERT INTO TEMP_SOMETBL VALUES (V_ID, SUBSTRING_INDEX(SUBSTRING_INDEX(V_NAME,'|', TIMES), '|', -1));
            SET TIMES = TIMES - 1;
        END WHILE;
    END LOOP;

    SELECT * FROM TEMP_SOMETBL;
    DROP TEMPORARY TABLE TEMP_SOMETBL;
END;;
DELIMITER ;