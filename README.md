# Monster Manual Application

Built for SER322. This application allows users to build encounters from a pool of pre-existing monsters.

## Database

The program uses a relational database, in this case MySQL to keep track of encounters and monsters in the system. Accesses are made via the JDBC driver, allowing the application to work with any relational DBMS. Users can search for encounters and monsters which sends queries to the database before marshalling results into java objects. Save functions will automatically determine whether an object should be updated or insterted to the database.

## GUI

The GUI was built using Java Swing. Stylistically, it is pretty bare as it is meant to show off the functionality of the application using JDBC.
