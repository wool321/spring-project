SELECT USER_NAME
  FROM USER_TABLE
 WHERE USER_ID='lee';
 
SELECT USER_NAME
  FROM USER_TABLE
 WHERE USER_ID='tjoeun'; 
 
DELETE FROM USER_TABLE; 
COMMIT;

INSERT INTO USER_TABLE
     VALUES(USER_SEQ.NEXTVAL, '더조은', 'tjoeun', '1234');
     
/*
INSERT INTO USER_TABLE
VALUES(USER_SEQ.NEXTVAL, #{user_name}, #{user_id}, #{user_pw})    
*/

ROLLBACK;

SELECT * FROM USER_TABLE
ORDER BY USER_IDX;


SELECT *
  FROM USER_TABLE
 WHERE USER_ID='leesoon' AND USER_PW=1111;  
 
/*` 
SELECT *
  FROM USER_TABLE
 WHERE USER_ID=#{USER_ID} AND USER_PW=#{USER_PW}
*/ 
 