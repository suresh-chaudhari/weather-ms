CREATE TABLE post (
post_id INT AUTO_INCREMENT PRIMARY KEY,
city VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL,
content text NOT NULL,
latitude float NOT NULL,
longitude float NOT NULL,
temperature float NOT NULL,
created_date datetime
)				   


CREATE TABLE reply_post (
reply_post_id INT(11) AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255) NOT NULL,
comment text NOT NULL,
created_date datetime,
post_id INT,
FOREIGN KEY(post_id) REFERENCES post(post_id) 
)