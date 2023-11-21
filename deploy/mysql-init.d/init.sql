CREATE USER 'zoso'@'localhost' IDENTIFIED BY 'zoso';
CREATE USER 'zoso'@'%' IDENTIFIED BY 'zoso';

GRANT ALL PRIVILEGES ON *.* TO 'zoso'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'zoso'@'%';

CREATE DATABASE party_mate_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;