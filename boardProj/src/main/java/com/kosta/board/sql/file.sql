CREATE TABLE IF NOT EXISTS  file  (
   num  int(11) NOT NULL AUTO_INCREMENT,
   DIRECTORY  varchar(255) DEFAULT NULL,
   NAME  varchar(255) DEFAULT NULL,
   size  bigint(20) DEFAULT NULL,
   contenttype  varchar(255) DEFAULT NULL,
   uploaddate  date DEFAULT NULL,
  PRIMARY KEY ( num )
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

