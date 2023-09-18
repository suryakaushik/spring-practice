mysql> create database javabrains_restapi; -- Creates the new database
mysql> create user 'javabrains' identified by 'javabrains'; -- Creates the user
mysql> grant all on javabrains_restapi.* to 'javabrains'; -- Gives all privileges to the new user on the newly created database


Do above steps to create a DB and user and grant all permissions to user on that DB.

Now 