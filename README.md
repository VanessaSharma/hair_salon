# Hair Salon
------

Application allows employees to add/edit clients and stylists. 

<br/>

### Setup/Installation Instructions
------
* In PSQL:
  * CREATE DATABASE hair_salon;
  * CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, specialty varchar );
  * CREATE TABLE clients (id serial PRIMARY KEY, name varchar, haircut varchar, color varchar, stylistid int);
* In command prompt enter: git clone https://github.com/Shabis/java_Hair-Salon.git
* In command prompt enter: cd hair_salon
* In command prompt enter: gradle run
* Navigate to  http://localhost:4567 in your preferred web browser.


<br/>

### Known Bugs
------

No known bugs as of now.



### Support and Contact Details
------

If you have any questions,concerns, or problens accessing the site, contact vanelunapalacios@live.com.

<br/>

### Technology Used
------

Java
Spark
Gradle
GitHub
Velocity 
PostgreSQL
JUnit 

<br/>


Copyright (c) 2016 **_Vanessa Palacios Sharma_**
