docker exec -it mysql-db mysql -u root -prootpassword

USE example;
SHOW TABLES;
SELECT * FROM data_warehouse;
SELECT * FROM product;

----

./gradlew clean

./gradlew bootRun

curl -X POST http://localhost:8080/warehouse/setup

curl http://localhost:8080/warehouse/all