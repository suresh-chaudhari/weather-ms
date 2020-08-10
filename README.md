
Loyalty-One Service
=============================

``` Java

Following things are implemented: 	
    a. Product insert, delete, update and fetch operation
    b. Manage the news in a database
    c. Created Dockrefile to make containerization application


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

mysql> describe product;
+----------+------------------+------+-----+---------+----------------+
| Field    | Type             | Null | Key | Default | Extra          |
+----------+------------------+------+-----+---------+----------------+
| id       | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| pname    | varchar(45)      | NO   |     | NULL    |                |
| quantity | int(10) unsigned | NO   |     | NULL    |                |
+----------+------------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)



Request URL: POST /v1/api/product
Content-Type : application/json

Request Payload:
{
   "productName": "Mouse",
   "quantity": 1
}

Response For:
{
    "message": "Congratulations ! Data inserted successfully."
}



Request URL: GET /v1/api/product
Content-Type : application/json

Response For :

[
    {
        "id": 1,
        "productName": "Mouse",
        "quantity": 1
    },
    {
        "id": 2,
        "productName": "Keyboard",
        "quantity": 4
    }
]



Request URL: UPDATE /v1/api/product/{id}
Content-Type : application/json
Request Payload:
{
   "productName": "Mouse-Updated",
   "quantity": 2
}

Response For:
{
    "message": "Congratulations ! Data updated successfully."
}



Request URL: DELETE /v1/api/product/{id}
Content-Type : application/json

Response For:
{
    "message": "Congratulations ! Data deleted successfully."
}

```