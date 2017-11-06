# Intro

# Setup

## AWS Dev environment
Refer https://one-confluence.pearson.com/pages/viewpage.action?spaceKey=POQC&title=Environment+Information
Add the following to your local ~/.bash_profile
```
alias p1_authdev="ssh -i /path_to/ProjectOne.pem ec2-user@35.161.159.36"
alias p1_globaldev="ssh -i /path_to/ProjectOne.pem ec2-user@52.42.8.158"
alias p1_customerdev="ssh -i /path_to/ProjectOne.pem ec2-user@52.41.252.212"
alias p1_globaldbdev="mysql --user=project_one --password=Project1! -h project-one.c4xzntwdisln.us-west-2.rds.amazonaws.com  project_one_global"
alias p1_authdbdev="mysql --user=project_one_auth --password=Project1_auth! -h project-one-auth2-cluster.cluster-c4xzntwdisln.us-west-2.rds.amazonaws.com  project_one_auth2"
alias p1_mongodev="ssh -i  /path_to/ProjectOne.pem ec2-user@34.208.212.234"
alias p1_customerdb="mongo mongodb://customerdb_user:password1!@34.208.212.234:27017/customerdb"
alias p1_tokenstoredb="mongo mongodb://tokenstoredb_user:password1!@34.208.212.234:27017/tokenstoredb"
```

Source using `source ~/.bash_profile` if you need this in the current terminal or simply open a new terminal

## Mysql
Setup mysql on your local env:
### Auth Schema
```
$ mysql -u root
create database auth_db;
create user auth_user;
grant all on auth_db.* to 'auth_user'@'localhost' identified by 'password1!';
grant all on auth_db.* to 'auth_user'@'%' identified by 'password1!';
grant select, insert, delete, update on auth_db.* to 'auth_user'@'localhost' identified by 'password1!';
grant select, insert, delete, update on auth_db.* to 'auth_user'@'%' identified by 'password1!';
exit

$ mysql -u auth_user auth_db -p
```
### Global Schema
```
$ mysql -u root
create database global_db;
create user global_user;
grant all on global_db.* to 'global_user'@'localhost' identified by 'password1!';
grant all on global_db.* to 'global_user'@'%' identified by 'password1!';
grant select, insert, delete, update on global_db.* to 'global_user'@'localhost' identified by 'password1!';
grant select, insert, delete, update on global_db.* to 'global_user'@'%' identified by 'password1!';
exit

$ mysql -u global_user global_db -p
```

### Generic Schema for Integration Tests
```
$ mysql -u root
create database integration_db;
create user intg_user;
grant all on integration_db.* to 'intg_user'@'localhost' identified by 'password1!';
grant all on integration_db.* to 'intg_user'@'%' identified by 'password1!';
grant select, insert, delete, update on integration_db.* to 'intg_user'@'localhost' identified by 'password1!';
grant select, insert, delete, update on integration_db.* to 'intg_user'@'%' identified by 'password1!';
exit

$ mysql -u intg_user integration_db -p
```

## MongoDB
MongoDB is used for the customer and the token stores.

* Follow google to find out how to install mongo on your local. On OSX `$ brew install mongo` should just work.
* Create a db directory in your user directory - `$ mkdir -p ~/mongo/data/db`
* Start mongo  - `$ mongod --dbpath  ~/mongo/data/db` .
Or follow this https://alicoding.com/how-to-start-mongodb-automatically-when-starting-your-mac-os-x/ on  OSX to get it started automatically, or other mechanisms
out there to get mongod get going ( no_hup bash_profile, launcher etc )
* Typically local mongodb runs on `localhost:27017`
* Create an admin user/password if not created
`mongo> use admin` and then
`db.createUser( { user: "admin", "pwd": "admin" , roles: [{role: "userAdminAnyDatabase", db: "admin"}]})`.

* Then disconnect from the mongo shell and restart mongod ( or change your service ) with `$mongod --auth --dbpath  ~/mongo/data/db`
* Now open a terminal and try to connect to your local mongo with credentials:
`$ mongo admin -u admin -p`
enter admin password
* If you get the following warnings on OSX when connecting to a mongo db, even the above admin connection, as the following:
```
Server has startup warnings:
2017-03-31T13:13:28.657-0500 I CONTROL  [initandlisten]
2017-03-31T13:13:28.657-0500 I CONTROL  [initandlisten] ** WARNING: soft rlimits too low. Number of files is 256, should be at least 1000
```
Stop the mongodb process, run the following commands and then restart mongo process
```
$ ulimit -n
$ sudo launchctl limit maxfiles 65536 65536
$ sudo launchctl limit maxproc 2048 2048
$ ulimit -n 65536
$ ulimit -n
```

### Customer DB
* Create a user for the customer DB:

```
 $ mongo admin -u admin -p
 use customerdb
 var user = {
          "user" : "customerdb_user",
          "pwd" : "password1!",
          roles : [
              {
                  "role" : "readWrite",
                  "db" : "customerdb"
              }
          ]
        };
 db.createUser(user);
 exit
```
* Test the login
`$ mongo customerdb -u customerdb_user -p`

### Global TokenStore DB
* Create a user for the global token store DB:

```
 $ mongo admin -u admin -p
 use tokenstoredb
 var user = {
          "user" : "tokenstoredb_user",
          "pwd" : "password1!",
          roles : [
              {
                  "role" : "readWrite",
                  "db" : "tokenstoredb"
              }
          ]
        };
 db.createUser(user);
 exit
```
* Test the login
`$ mongo tokenstoredb -u tokenstoredb_user -p`


### Integration Test DBs
* We need to create two DBs for integration testing for the customer app and the auth app

```
 $ mongo admin -u admin -p
 use tokenstoredb_intg
 use customerdb_intg
 exit
```

### Application.yml
Typical configurations are provided in the each server ( war ) application config yml files. An example for the tokenstore is:
```
tokenDatasource:
    mongo-uri: mongodb://tokenstoredb_user:password1!@localhost:27017/tokenstoredb
customerDatasource:
    mongo-uri: mongodb://customerdb_user:password1!@localhost:27017/customerdb
```

## IDE
We recommend IntelliJ and Atom ( for JS, TS )
### IntelliJ

* To create Idea project files, run `$ ./gradlew idea`
* Import the `application` folder in IntelliJ and select Gradle as the tool for build tools. This is so that we can
run/debug from IntelliJ's gradle tool window
usually by right click and run/debug bootRun task.
* If not imported already, import code style from ../tools/intellij/project_one_idea_styles.xml into IntelliJ
( IntellijIdea -> Preferences -> Code Style -> Manage -> Import
* Every file needs to be formated as per the code style. This is accomplished by ⌘ - ⌥ - L key combination. To format
a selected portion, make the selection and use the above key combination.

# Design
## Project Architecture
[Architecture Principles](https://docs.google.com/document/d/1QzylL2Y2zLBEPfdRj8qG8wFx1eQl771EeV0vVV0yoiw/edit#)

## Authentication
### Projects.
* auth-data contains dao and entity classes related to authentication
* auth-commons provides the services from oauth2 client details and user authentication management
* auth-server aggregates the above and provides a http rest end point war app with `/auth` context path to server oauth2 requests and `/me` requests

### Development environment
* data is populated into user and oauth2 client details within the com.pearson.projectone.authserver.dev.AuthServerDevDataPopulator
* passwords for users and client ( clientSecrets ) are in clear format.
* This is governed using profiles in the com.pearson.projectone.core.support.application.BaseServerApplication.
* Note that there are two versions of the password encoder provided.
**passwords are encrypted in higher environments using the `!dev` declaration in the above profile**

### Authentication Requests

#### Login with user password example
In dev mode, the client credentials and the user and password information is available in the populator mentioned above.

```
$ curl -X POST  -u projectOneApp:password1 http://localhost:9999/auth/oauth/token \
-H "Accept: application/json" -d "password=Password1&username=jane&grant_type=password&scope=read&client_id=projectOneApp"
```
#### 'Me' to get basic user principal
From the token retrieved above:
```
$ TOKEN=<the_token> curl -X GET -u projectOneApp:password1 -H "Authorization: Bearer $TOKEN" http://localhost:9999/auth/me
```
#### Example resource server request with token
```
$ TOKEN=<the_token> curl -X GET -H "Authorization: Bearer $TOKEN" http://localhost:9997/global-api/user
```
## Database Design
  * https://docs.google.com/spreadsheets/d/1sdfSb3y7zw4sWiST3bp4Rx2-EJoyyx0yo3TJtGrGRL0/edit?ts=5898b325#gid=0
  * https://drive.google.com/a/pearson.com/file/d/0B65wlCAcm8DVZmM5VThYTGxLQmM/view?usp=sharing ( A mySqlWorkbench model project )

# Build/Deploy

### Local
* `$ ./gradlew clean build` to build all projects.  Individual artifacts would be in build/lib folder.
* `$ ./gradlew build runAll` to run the servers and ui from terminal
* `$ ./gradlew clean` cleans all build artifacts and makes the `application` folder pristine.

### Tests
* Unit tests - `$ ./gradlew clean test`
* Integration tests - `$ ./gradlew clean integration`

### Build and Run for Environments
* Running in Dev env in Debug/Run mode with IntelliJ see RUN_DEV_Intellijs.png
* for TST env
  - `$ P1_ENV=tst ./gradlew clean build`

### DBMaintain
DBMaintain is used for auth and global schema database script management.

* For distribution run `$ ./gradlew createDBMaintainArchive`. This creates a jar of scripts within the build/dbmaintain folder in the individual data projects
* For distrubution targeted to a specific db, override with the property file location as `$ dbmaint_config_file=/location_of/dbmaintain.properties ./gradlew createDBMaintainArchive`.
* To update database `$ ./gradlew updateDatabase` , or to target a specific db, `$ dbmaint_config_file=/location_of/dbmaintain.properties ./gradlew updateDatabase`.

### Mongo DB Maintain
We have added a facility to rollout mongo db data to higher db envs. This needs the `mongo` client command, installed part of the mongo package

* For running in a dev controlled env, say a devqa , qa etc we can issue `$ ./gradlew updateMongo`.
* To target a specific mongo db store, `$ mongodbmaint_config_file=/location_of/mongo_dbmaintain.properties ./gradlew updateMongo`. For example properties to be added to the custom/overriden `mongo.properties` refer to customer-data/src/database/resources
* To create distributions, run `$ ./gradlew createMongoDBMaintainArchive`. This creates a zip inside build/mongo-dbmaintain ,including the scripts to be run and a build.gradle
* To run the script based on the above created zip:- ( This is typicaly done by an admin )
    - `gradle` needs to be in path.
    - Unzip the above zip file in any location
    - cd into the extracted folder. There should be a single jar and a build.gradle file
    - Run `$ mongodbmaint_config_file=/location/mongodb.properties gradle runMongoJar`
    - The properties should contain :-
        * uri , for eg: `mongo-uri=mongodb://scott:tiger@localhost:27017/customerdb_maintaintest`
        * which sub customer location's script to be run using `default-script-location=US`


### Sonar
  * `$ ./gradlew sonarqube`

# Appendix
# References
