# Warehouse Inventory System

* Able to store product data via csv file consumption. 
*	Able to store quantities of such products in different locations via csv file consumption. 
*	UI to show inventory level of given product code
*	Able to transfer inventory from one location to another given amount of quantity and product code via UI

How to start the application
---
This project is using Intellij as the IDE.

Step 1:
Import the project using Intellij

Step 2:
Change the application.properties to match your environment
spring.datasource.url=jdbc:mysql://localhost:3306/{db_name}\
spring.datasource.username={db_username}\
spring.datasource.password={db_password}\
spring.jpa.hibernate.ddl-auto=create\
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect (Change it if your are not using MySQL8)\
spring.jpa.show-sql=true

Step 3:
Run the WarehouseInventorySystemApplication, the application will automatically generate the database tables for you.

Step 4:
Insert the warehouse data in the sql folder warehouse_data.sql (You can add more data if you want)

Step 5:
Using localhost:8080 to access the application

How to test the application
---
Step 1:
Insert the product data using sample_product_csv_data.csv in the csv folder or you can make up your own data set following the CSV format I provided. 

Step 2:
After inserted the product using sample_inventory_csv_data.csv in the csv folder or you can make up your own data set following the CSV format I provided. 

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

Weight can only be digits.

**Inventory csv file**\
Product_Code,Warehouse_Code,Qty\
FM-HKTV01,TKO,5\
FM-HKTV01,CAB,4

Qty can only be Integer.

Daily
---
28/05/2020\
Completed: Able to store product data via csv file consumption

What I have learnt:
1. 




