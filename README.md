# Warehouse Inventory System

* Able to store product data via csv file consumption. 
*	Able to store quantities of such products in different locations via csv file consumption. 
*	UI to show inventory level of given product code
*	Able to transfer inventory from one location to another given amount of quantity and product code via UI

How to start the application
---
This project is using Intellij as the IDE.

Step 1:
Import the project in Intellij

Step 2:
Change the application.properties to match your environment
spring.datasource.url=jdbc:mysql://localhost:3306/{db_name}\
spring.datasource.username={db_username}\
spring.datasource.password={db_password}\
spring.jpa.hibernate.ddl-auto=create\
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect (Change it if your are not using MySQL8)\
spring.jpa.show-sql=true

Step 3:
Run the WarehouseInventorySystemApplication, the application 

Step 4:
Insert the warehouse data in the warehouse_data.sql

Stack
---
Database: MySQL\
Framework: Spring(Spring boot)\
ORM: Hibernate\
Template engine: Thymeleaf\
Language: Java, HTML5, JavaScript, CSS

CSV Data format
---
**Product csv file**\
Product_Name,Code,Weight\
face mask,FM-HKTV01,100\
face mask2,FM-HKTV02,102.2

**Inventory csv file**\
Product_Code,Warehouse_Code,Qty\
FM-HKTV01,TKO,5\
FM-HKTV01,CAB,4

Daily
---
28/05/2020\
Completed: Able to store product data via csv file consumption

What I have learnt:
1. 




