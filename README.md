
Loyalty-One Service
=============================

``` Java

Following things are implemented: 	
    a. Connect to an API to retrieve a a Weather information by city name
    b. Manage the a database store post and user information, reply post information
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

	
Mysql database schema:

mysql> describe post;
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| post_id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| city         | varchar(255) | NO   |     | NULL    |                |
| content      | text         | NO   |     | NULL    |                |
| created_date | datetime     | YES  |     | NULL    |                |
| latitude     | float        | NO   |     | NULL    |                |
| longitude    | float        | NO   |     | NULL    |                |
| temperature  | float        | NO   |     | NULL    |                |
| username     | varchar(255) | NO   |     | NULL    |                |
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
| comment       | varchar(255) | NO   |     | NULL    |                |
| created_date  | datetime     | YES  |     | NULL    |                |
| post_id       | int(11)      | YES  |     | NULL    |                |
| username      | varchar(255) | NO   |     | NULL    |                |
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