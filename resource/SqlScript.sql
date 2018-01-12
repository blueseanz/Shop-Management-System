CREATE DATABASE ShopSystem;
USE ShopSystem;

CREATE TABLE SALESMAN
(
		sid        VARCHAR(20) NOT NULL,
		sname      VARCHAR(10) NOT NULL UNIQUE,
		spassword  VARCHAR(20) NOT NULL,
		PRIMARY KEY (sid)
)ENGINE=INNODB;


INSERT INTO SALESMAN VALUES ('10001234', 'Joe','test1234');
INSERT INTO SALESMAN VALUES ('10001231', 'Mark','test1231');
INSERT INTO SALESMAN VALUES ('10001232', 'Joy','test1232');
INSERT INTO SALESMAN VALUES ('10001233', 'JAVA','test1233');
INSERT INTO SALESMAN VALUES ('10001235', 'LIBRARY','test1235');
INSERT INTO SALESMAN VALUES ('10001236', 'Oak','test1236');
INSERT INTO SALESMAN VALUES ('10001237', 'mingming','ming');

CREATE TABLE GOODS
(
       gid     VARCHAR(20) NOT NULL,
       gname   VARCHAR(20) NOT NULL UNIQUE,
       gprice  FLOAT(18,2) NOT NULL,
       gnum    INT NOT NULL,
	   PRIMARY KEY (gid)
)ENGINE=INNODB;

INSERT INTO GOODS VALUES ('32651234', 'Apple',4.38,9);
INSERT INTO GOODS VALUES ('32651230', 'Orange',5.38,19);
INSERT INTO GOODS VALUES ('32651231', 'Banana',2.38,29);
INSERT INTO GOODS VALUES ('32651232', 'Apricot',3.38,39);
INSERT INTO GOODS VALUES ('32651233', 'Coconut',9.38,49);
INSERT INTO GOODS VALUES ('32651235', 'Peach',1.38,59);

CREATE TABLE GSALES
(
       gsid  VARCHAR(20) NOT NULL,
       gid   VARCHAR(20) NOT NULL,
       sid   VARCHAR(20) NOT NULL,
       sdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       snum  INT NOT NULL,
	   PRIMARY KEY (gsid),
	   FOREIGN KEY (gid) REFERENCES GOODS(gid) ON DELETE CASCADE,
	   FOREIGN KEY (sid) REFERENCES SALESMAN(sid) ON DELETE CASCADE
)ENGINE=INNODB;

INSERT INTO GSALES (gsid,gid,sid,snum) VALUES ('20000000','32651234','10001234',1);
INSERT INTO GSALES (gsid,gid,sid,snum) VALUES ('20000001','32651230','10001234',2);
INSERT INTO GSALES (gsid,gid,sid,snum) VALUES ('20000002','32651231','10001234',3);
INSERT INTO GSALES (gsid,gid,sid,snum) VALUES ('20000003','32651232','10001233',4);
INSERT INTO GSALES (gsid,gid,sid,snum) VALUES ('20000004','32651233','10001233',5);

=============================================================================================
Clerk, Mark

SELECT goods.gname as 'Goods', 
	   gsales.snum as 'Number',
	   gsales.snum * goods.gprice as 'Sub Total'
FROM gsales, salesman, goods 
WHERE salesman.sid = gsales.sid
AND goods.gid = gsales.gid
AND salesman.sname = 'ming'
AND sdate> CURDATE()
GROUP BY Goods
Union
SELECT 'Total' as 'Goods',  
	   sum(gsales.snum) as 'Number',
	   sum(gsales.snum * goods.gprice) as 'Sub Total'
FROM gsales, salesman, goods 
WHERE salesman.sid = gsales.sid
AND goods.gid = gsales.gid
AND salesman.sname = 'ming'
AND sdate> CURDATE()
GROUP BY Goods
=====================================================
Goods, Banana

SELECT salesman.sname as 'Clerk', 
	   SUM(gsales.snum) as 'Number',
	   SUM(gsales.snum * goods.gprice) as 'Sub Total'
FROM gsales, salesman, goods 
WHERE salesman.sid = gsales.sid
AND goods.gid = gsales.gid
AND goods.gname = 'Banana'
AND sdate> CURDATE()
GROUP BY Clerk
Union
SELECT 'Total' as 'Clerk', 
	   SUM(gsales.snum) as 'Number',
	   SUM(gsales.snum * goods.gprice) as 'Sub Total'
FROM gsales, salesman, goods 
WHERE salesman.sid = gsales.sid
AND goods.gid = gsales.gid
AND goods.gname = 'Banana'
AND sdate> CURDATE()
GROUP BY Clerk;

SELECT COUNT(*) AS rowcount
FROM gsales, salesman, goods 
WHERE salesman.sid = gsales.sid
AND goods.gid = gsales.gid
AND sdate> CURDATE()


SELECT DISTINCT salesman.sname
FROM gsales, salesman
WHERE salesman.sid = gsales.sid
AND gsales.sdate> CURDATE();

SELECT DISTINCT goods.gname
FROM gsales, goods 
WHERE goods.gid = gsales.gid
AND gsales.sdate> CURDATE()


