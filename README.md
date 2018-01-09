# MVC_Spring_cars  

A Spring MVC WEB APP that maintains cars list with CRUD operations. Uses mysql   
as the database and Pivotal tx Server3.2


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
