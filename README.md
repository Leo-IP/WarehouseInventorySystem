# Warehouse Inventory System

**Description**

* Able to store product data via csv file consumption. 
    * The system will only accept .csv file
    * The system will not accept duplicate product data. Please make sure your data does not exist in the product table.
*	Able to store quantities of such products in different locations via csv file consumption.
    * The system will only accept .csv file
    * If the data already existed in the database. The system will find the inventory record and add the product qty to that record. Otherwise, it will create a new inventory record.
*	UI to show inventory level of given product code
    * Your product code must be exact.
*	Able to transfer inventory from one location to another given amount of quantity and product code via UI
    * Make sure your qty is not larger that the current inventory levels.

Stack
---
Database: MySQL\
Framework: Spring(Spring boot)\
ORM: Hibernate\
Template engine: Thymeleaf\
Language: Java, HTML5, JavaScript, CSS

Git clone link
---
https://github.com/Leo-IP/WarehouseInventorySystem.git

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
Insert the product data using Product_Data.csv in the csv folder or you can make up your own data set following the CSV format I provided. 

Step 2:
After inserted the product using Inventory_Data.csv in the csv folder or you can make up your own data set following the CSV format I provided but make sure your warehouse data and product data match the inventory data.

Step 3:
Using the product code to search. The system will show the inventory levels base on the given product code.

Step 4:
Transfer inventory from one location to another by clicking the transfer button.

Step 5: 
Input the product qty that you want to transfer and select the location using the drop-down list.

Test cases
---
I provided some invalid data in the Invalid product data folder and  Invalid inventory data folder.

The following is my checklist for testing the system.

**Store product data**

  * Valid data
  * Empty
    * Product Name empty  
    * Product Code empty
    * Weight empty
  * Spaces
    * Product Name with spaces
    * Product Code with spaces
    * Only spaces(Weight)
    * Weight with Spaces in between
  * Negative
    * Weight with negative number
  * Letters
    * Weight with letters
    
Store inventory data

  * Valid data
  * Empty
    * Qty empty
  * Negative
    * Qty Negative
  * Letters
    * Qty with letters
  * Constraints
    * Warehouse code does not exist
    * Product code does not exist
  * Spaces
    * Only spaces(Qty)
    * Qty with Spaces in between
    * Qty with spaces at the end
    
Search Product

  * Empty
    * Input box empty
  * Existence
    * Product exists in inventory
    * Product does not exist in inventory
  * Qty
    * Qty is 0
    * Qty is not 0
    
Transfer

  * Empty
    * Qty empty 
  * Qty does not match inventory status
  * Qty is less than 0
  * Qty and location

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
28/05/2020

Completed
1. Able to store product data via csv file consumption

What I have learnt:
1. How to use Spring initializr to bulid a Spring boot project.
2. How to upload and parse using opencsv library.
3. How to use Spring framework annotation @Controller, @Service, @Repository.
4. How to use Thymeleaf to create a template.
5. How to Spring Data JPA to save data to database.
6. How to use lombok library to generate getter, setter, toString, etc.
7. How to denfine a BaseEntity.


29/05/2020

Completed
1. Able to store quantities of such products in different locations via csv file consumption.\
2. UI to show inventory level of given product code

What I have learnt:
1. How to pass parameters for mthe get form action in the url.
2. How to @Entity, @OneToMany, @ManyToOne, @@Column, @Id and @NatureId to define tables and the relationship between different tables.
3. How to use @Configuration and @Bean to register a bean to the Spring IoC container.
4. How to send data from select option using Thymeleaf.
5. How to use boolean condition with Thymeleaf and spring
6. How to use redirect attributes to send parameters.

30/05/2020

Completed 
1. Able to transfer inventory from one location to another given amount of quantity and product code via UI

What I have learnt:
1. How to use ModelMapper with customPropertyMap.
2. How to use mappedBy to establish bidirecxtional association between Inventory, Product and Warehouse entities.
3. How to overcome problems(toString(),HashCode() endless loop) when using hibernate, JPA and Lombok.





