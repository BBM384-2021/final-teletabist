# Clubby Development Framework
Clubby development framework is the combination of frameworks and various package managers to provide a solid, compact and developer friendly environment for Teletabist team.

## Techincal details
- Framework is constructed over Spring Boot with Spring Web (MVC) and Spring Security packages of Spring Framework.
- Integration of versioned DB migrations via Flyway Migration
- Thymeleaf Java Template Engine for server-side rendering
- Spring Boot DevTools for Livereload of application
- Spring Configuration Processor for fast deployments
- Package structures are based on today's MVC frameworks to provide service, db and controller layers for application. This is also important for maintanace
- Clubby Development Framework also utilizes Vue.js and Webpack to provide on system front-end development.
- Clubby uses Maven and NPM for package management of theses related resources

## System Requirements
- Web framework needs Java 8+ to run.
- NPM 6.14.5+
- Node.js 12.18.2+
- Apache Maven 3.6.3+

## File Structure
```directory
.
├── resources                               #Front-end resources
|   ├── js                                  #JS resources
|   |   ├── components                      #Vue components
|   |   ├── main.js                         #Main js file for compilation
|   ├── scss                                #SCSS files
├── src                                     #Application directory
|   ├── main                                #Main application Files
|   |   ├── java\com\teletabist\clubby      #Java application source code
|   |   |   ├── config                      #Application java config classes
|   |   |   |   ├── SecurityConfig.java     #Application Security Layer config
|   |   |   |   ├── WebConfig.java          #Application Web Layer Config
|   |   |   ├── welcome                     #Module directory
|   |   |   |   ├── api                     #Api controllers
|   |   |   |   ├── daos                    #Data access layer
|   |   |   |   ├── http                    #Http controllers
|   |   |   |   ├── models                  #Module Models
|   |   |   |   ├── services                #Service code for business logic
|   |   |   ├── ClubbyApplication.java      #Application run code
|   |   ├── resources                       #Application resources
|   |   |   ├── config                      #Application Configuration Files
|   |   |   ├── db                          #DB resources
|   |   |   |   ├── migration               #FlyWay migration scripts
|   |   |   ├── public                      #Public application files, served on /public/
|   |   |   ├── static                      #Static html pages, served on web
|   |   |   ├── views                       #Thymeleaf templates
|   ├── test                                #Test codes
|   |   ├── java\com\teletabist\clubby      #Application source test code
|   |   |   ├── ClubbyApplicationTests.java #Default test class
├── target                                  #Build files
├── HELP.md                                 #Official Documentations
├── pom.xml                                 #Maven dependecies
├── package.json                            #NPM dependencies
├── README.md                               #This file
├── webpack.mix.js                          #Laravel-mix/Webpack mix configuration
```
## Shell Commands
- Installing java packages
```shell
mvn install
``` 
- Installing node packages
```shell
npm install
```
- Running npm on watch mode (Update on save)
```shell
npm run watch
```
- Running npm on hot mode (Updating on save, reloading components on page)
```shell
npm run hot
```
- Building js and scss files for production
```shell
npm run production
```
- Running Spring on live reload mode
```shell
mvn spring-boot:run
```
- Compiling Spring for deployment
```shell
mvn package
```

## Additional Notes
The framework is structured over module based implementation on MVC design pattern. We utilize Vue.js and SCSS for front end development with Webpack compiler. In backend, we use Spring framework with Flyway migrations for versioning and Spring Security for user session control.
Please refer to HELP.md for further documentation of these technologies

### This framework is created by Yigit Koc as a part of Teletabist Team.
