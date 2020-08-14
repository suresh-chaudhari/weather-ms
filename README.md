
Loyalty-One Service
=============================

``` Java

Following things are implemented: 	
    a. Spring Boot application.
    a. Imlemented OpeaWeather API for add Weather information by city name
    b. Manage the a database store , post ,reply and user data
    c. Use Redis to introduce a caching layer
    d. Secured application with https
    e. Docker containerization 
    f. Deployment: Docker/PCF file (docker-compose.yml)(manifest.yml)

Note: Redis caching layer is implemented for expose endpoint for /api/v1/post and /api/v1/post/{postId}/reply
      Redis will keep data in Caching for 30 seconds after that it will make query to again database and put it in cache

      To update Redis Cache time , you can update in application.yml file with ;time-to-live: 20000' field.


Run project:

1. If you have not installed a mysql and redis on your machine, you can use docker-compose.yml file to start mysql on docker engine
    
	docker-compose up -d

Note: Please update the mysql IP in application.yaml file. It will written as localhost in mysql in yaml file.
Docker IP: 192.168.99.100

2. Start application using maven command

   mvn spring-boot:run
   
   It will start server by on 8443 and you can use swagger page to hit the endpoint 
   You can access swagger page on https://localhost:8443/swagger-ui.html


   
3. To make docker image use below command (Docker is required to installed your machine)
 
   mvn clean package docker:build
    

4. Deploy this application on Docker Container:

    You need to change application.yaml file. Comment the keystore related property on file. You can create docker image by using above command. (3 steps)
    To run the application on Docker container. Please use docker-compose.yml and run below command.

    docker-compose up -d


    Note: It will start server by default on 8080 port and if you want to test the exposed endpoint you can use swagger page to hit the endpoint
     You can access swagger page on http://localhost:8080/swagger-ui.html


``` 
###### Deployment on Kubernetes (Minikube)
``` Java
    To deploy this application on minikube/kubernetes. You need to run below command. First command will deploy mysql server and redis server
    in single POD. Once this pod is up. Run the second command. This command will deploy the loyaltyone-ms image file which is already pushed
    on docker hub.

    1. kubectl apply -f kubernetes/mysql-redis/
   
    2. kubectl apply -f kubernetes/app/
 
You can verify deployed pods and services using below command

C:\Projects\LoyaltyOne\loyaltyone-ms>kubectl get pods -n development
NAME                                       READY   STATUS    RESTARTS   AGE
database-mysql-redis-ms-7bf4dc94c6-mc6t2   2/2     Running   2          62m
loyaltyone-api-ms-7cb4f4f884-6p25b         1/1     Running   0          11m

C:\Projects\LoyaltyOne\loyaltyone-ms>kubectl get services -n development
NAME                      TYPE       CLUSTER-IP       EXTERNAL-IP   PORT(S)                         AGE
database-mysql-redis-ms   NodePort   10.100.173.255   <none>        3306:30414/TCP,6379:30022/TCP   49m
loyaltyone-api-ms         NodePort   10.99.187.136    <none>        8080:31583/TCP                  11m


Note: To check endpoint of loyalyone-ms, you can use swagger page below URL. Kubernetes will always exposed service on new port for outside.
Here it is exposed on 31583 port.

http://192.168.99.102:31583/swagger-ui.html
```

``` Java 	
Mysql database schema:

mysql> describe post;
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| post_id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| city         | varchar(255) | NO   |     | NULL    |                |
| username     | varchar(255) | NO   |     | NULL    |                |
| content      | text         | NO   |     | NULL    |                |
| latitude     | float        | NO   |     | NULL    |                |
| longitude    | float        | NO   |     | NULL    |                |
| temperature  | float        | NO   |     | NULL    |                |
| created_date | datetime     | YES  |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+


mysql> describe reply_post;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| reply_post_id | int(11)      | NO   | PRI | NULL    | auto_increment |
| comment       | text         | NO   |     | NULL    |                |
| created_date  | datetime     | YES  |     | NULL    |                |
| post_id       | int(11)      | NO   |     | NULL    |                |
| username      | varchar(255) | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+



mysql> describe reply_post;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| reply_post_id | int(11)      | NO   | PRI | NULL    | auto_increment |
| username      | varchar(255) | NO   |     | NULL    |                |
| comment       | text         | NO   |     | NULL    |                |
| created_date  | datetime     | YES  |     | NULL    |                |
| post_id       | int(11)      | NO   | MUL | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+


Create Post API:
Request URL: POST https://localhost:8443/api/v1/post
Request Body:
{
  "content": "How are You..!!",
  "username": "suresh",
  "city": "toronto"
}


Response:
{
  "postId": 1,
  "username": "suresh",
  "content": "How are You..!!",
  "city": "toronto",
  "latitude": 43.7,
  "longitude": -79.42,
  "temperature": 26.14,
  "createdDate": "Thu Aug 13 21:00:20 EDT 2020"
}


Get ALL Posts API:
Request URL: GET https://localhost:8443/api/v1/post

[
  {
    "postId": 1,
    "username": "suresh",
    "content": "How are You..!!",
    "city": "toronto",
    "latitude": 43.7,
    "longitude": -79.42,
    "temperature": 26.14,
    "createdDate": "Thu Aug 13 21:00:20 EDT 2020"
  }
]


Reply Post API:
Request URL: POST /api/v1/post/{postId}/reply
Request Body:
{
  "comment": "Replied Comment",
  "username": "Suresh Chaudhari"
}

Response:
{
  "replyPostId": 2,
  "username": "Suresh Chaudhari",
  "comment": "Replied Comment",
  "createdDate": "Thu Aug 13 21:27:29 EDT 2020",
  "postId": 1
}


Get Post Reply API:
Request URL: GET /api/v1/post/{postId}/reply

[
  {
    "replyPostId": 1,
    "username": "suresh chaudhari",
    "comment": "Replied Comment",
    "createdDate": "2020-08-13 20:54:28.0",
    "postId": 1
  }
]

Delete Post Reply API:
Request URL: DELETE /api/v1/post/{postId}/reply/{replyId}

```