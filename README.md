
Loyalty-One Service
=============================

``` Java

Run project:

1. If you have not installed a mysql on your machine, you can use docker-compose.yml file to start mysql on docker engine
    
	docker-compose up -d

Note: Please update the mysql IP in application.yaml file. It will written as localhost in mysql in yaml file.

2. Start application using maven command

   mvn spring-boot:run
   
   It will start server by default on 8080 port and if you want to test the exposed endpoint you can use swagger page to hit the endpoint
   
   You can access swagger page on http://localhost:8080/swagger-ui.html
   
3. To make docker image use below command (Docker is required to installed your machine)
 
   mvn clean package docker:build
    



	
Mysql database schema:

mysql> describe post;
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| post_id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| city         | varchar(255) | YES  |     | NULL    |                |
| content      | text         | NO   |     | NULL    |                |
| created_date | datetime     | YES  |     | NULL    |                |
| latitude     | float        | YES  |     | NULL    |                |
| longitude    | float        | YES  |     | NULL    |                |
| temperature  | float        | YES  |     | NULL    |                |
| username     | varchar(255) | NO   |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+


mysql> describe reply_post;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| reply_post_id | int(11)      | NO   | PRI | NULL    | auto_increment |
| comment       | varchar(255) | NO   |     | NULL    |                |
| created_date  | datetime     | YES  |     | NULL    |                |
| post_id       | int(11)      | YES  |     | NULL    |                |
| username      | varchar(255) | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+



```