# MVC_Spring_cars  

Database name: springmvc  
Table name : cars  

create table cars (   
carId int(11) NOT NULL AUTO_INCREMENT,  
manufacturer varchar(45) DEFAULT NULL,  
model int(11) DEFAULT NULL,  
city varchar(45) DEFAULT NULL,  
registrationNumber varchar(45) DEFAULT NULL,  
PRIMARY KEY(carId)   
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;  
