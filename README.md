# UIC News Crawler

This app will display the list of news articles on the web which have mentioned or reviewed "UIC". For now we can look for the articles for past 30 days.
You can access demo [here](http://nabinacharya.com)

## Getting Started
# How to Use?
* Click [here](http://nabinacharya.com)
* Select a date
* View the News Article details by clicking on the news source
* Click read more to go to the news details webpage

# About
This app is the bundle of 2 loosely coupled frontend and backend application 

**NewsCrawlerAPI** is the backend application built on Spring Boot which communicates with third party News Services and exposes RESTful web service for client communication.

**NewsCrawlerAPI** is the frontend application built on Angular 6 which communicates with user and the backend application to display the list of news articles.

## NewsCrawlerAPI


This is a java web app is built using Spring Boot framework. It is a RESTful web service and exposes the single GET endpoint to get the list of news articles for the date passed as parameter.
```
/news?date={yyyy-mm-dd}
```
This app plays the role of proxy app to access the third party news services. Front end client App (web or mobile) will have a consistence service endpoint and standard JSON response regardless of whichever 3rd party News Service implementation. you can inject any 3rd party news service in service layer of this app and the things remain unchanged for clients accessing our service. 
### Prerequisites
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Running the application locally

The to run a Spring Boot application on your local machine is to execute the `main` method in the `com.uic.newsCrawlerAPI.NewsCrawlerApiApplication.java` class from your IDE.

You can access the application endpoint with following URI
```
http://localhost:8081/news?date={yyyy-mm-dd}
```
You can change the port in application.properties file in src/resources folder

```
server.port=8080
```
You will get response in following JSON format

```
{
    "newsId": String;
    "sourceName": String;
	"author": String;
	"title": String;
	"description": String;
	"url": String;
	"urlToImage": String;
	"publishedAt": String;
	"content": String;
}
```
## NewsCrawlerUI

This is the responsive single page Angular application which takes input from user and communicate with our spring app to get JSON data and diplays it for the user.

### Prerequisites
For building and running the application you need:

- [@Angular/Cli](https://cli.angular.io/)

### Running the application locally

* use command line
```
cd .../newsCrawlerUI/
ng serve
````

* Access the app in

```
http://localhost:4200/
```

## Deployment

### NewsCrawlerAPI
* Use Maven in commandline to build the app
```
cd .../newsCrawlerAPI/
mvn clean
mvn install
```
* Deploy jar file from .../newsCrawlerAPI/target/ to your application server
* run jar file in your server with production configuration
```
% java -jar newsCrawlerAPI.jar --spring.profiles.active=production”
```

### NewsCrawlerUI
* Update Spring app prod url in .../newsCrawlerUI/src/environments/environment.prod.ts
```
apiUrl: "http://server:port/news?date="
```
* Use Angular CLI in commandline to build the app using production env configuration
```
ng build --configuration=production
```
* deploy the static files in .../newsCrawlerUI/dist/ folder in your http server
* alternatively to deploy within spring app, deploy the static files in .../newsCrawlerUI/dist/ to ../newsCrawlerAPI/src/main/resources/static and build and deploy the spring app

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used for backend
* [Maven](https://maven.apache.org/) - Dependency Management 
* [Angular](https://angular.io/) - The framework used for frontend
* [NewsApi](https://newsapi.org/) - 3rd Party News Service

## Author

* **Nabin Acharya** 

## Improvement Pending

* Implementing Database to store past news articles which will reduce expensive trips with 3rd party news service. 
