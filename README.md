# Webhook Manager Backend Application

This is a simple Spring Boot application that provides RESTful WebServices to manage webhooks.
Webhook Manager can send Scheduled Webhook requests to the given URL.

### Features
- Save Webhook to the database
- Get Webhook 
- Delete Webhook
- Send Scheduled Webhook requests with the given URL 
- Update Webhook


### Used Technologies
* Java 17
* Gradle
* Spring Boot/Spring Framework
* RESTful WebServices
* MongoDb
* Docker
* OpenFeign Client
* Lombok
* Scheduling and Asynchronous Processing
* Webhook
## Application Docker Container

docker files will be updated soon
### Build image
    docker build -t webhook-manager .
### Run App container
    docker run -p 8080:8027 --name webhook-manager --link webhook:mongo -d webhook-manager
### Mongodb Setup
    docker run -d -p 27017:27017 --name webhook mongo:latest
### Docker image
    docker pull sizzlemaster/webhook-manager:latest
### Docker compose
    docker-compose build
    docker-compose up

## HTTP Methods


#### TO-DO:
- Add Global Exception Handling
- Add Unit Tests
- Add More Logging
- Improve Generic Response Handling
