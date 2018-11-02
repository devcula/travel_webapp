# travel_book_webapp


#### A AngularJS, Java, Hibernate based web application trying to implement an online travel booking platform from scratch without using any pre-existing booking api ####


#A full fledged web app which can be used to book flights, trains, bus, cabs and hotel. With simple and smooth interface, this application is designed to provide users with best of the features.

#Features like dynamic pricing, rating and reviewing hotels are also provided to the users for complete clarity.

#This app doesn't use any pre-existing api's and contains it's own scripts and api's for generating its entire database and accessing it.

#All the codes and scripts are written from scratch to implement an original online booking platform.

#The Frontend is based on HTML, CSS, JavaScript, Jquery, BootStrap and AngularJS while the backend is built using Java, Hibernate framework and MySql database.

#Restful Api's were created to connect both ends.

################TO RUN THE PROJECT##################

1. Start the MySql server installed on your system.

2. Create a database with any name to store the dummy data.

3. Run the sql script User_TableScript.sql placed in src/com/devcula/resources.

4. Run the data.sql script in the same database. It's also present in src/com/devcula/resources.

5. Update the connection url in hibernate.cfg.xml file located in src/com/devcula/resources according to your sql server settings and your database name.

5. Update the same connection url in getConnectionString() method of Factory class present in src/com/devcula/resources.

6. Update your database credentials i.e. username and password into hibernate.cfg.xml file and 
also in getDbUsername() and getDbPassword() methods of factory class.

7. After successfully completing above steps, run the DataInit class as a standalone java program. DataInit class is also present in src/com/devcula/resources.

8. Once all the steps are completed, host the project on a web server like Apache Tomcat or IBM Websphere and run index.html present in WebContent folder.

NOTE: If on launching index.html, you get "Website Under maintenance", it means one of more steps failed to execute.
      I didn't have time to create admin panel so if anyone is interested to contribute, they are more than welcome. Just ping me.

For any help, mail me at abhimaxfield@gmail.com.
