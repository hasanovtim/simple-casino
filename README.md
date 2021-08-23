**Simple-casino**

Parent service ‘microservice’ contains ​4 modules:
- discovery service (Eureka server) Port: 8081
- gateway API (zuul proxy) Port: 8080
- game-service. Port: 8083
- wallet-service. Port: 8084
*You can change default port (server.port) in bootstrap.yml file

Requirements:
1. Java 8+
2. Maven

**Hot to run project in Intellij Idea**

I have tested project In intellij idea. Hopefully you are familiar with it. Anyway quick overview
how to test project in Idea.
1. Import project from existing sources as Maven project. Choose parent folder ‘microservice’
2. Wait until all dependencies will be loaded
3. Enable annotation processor: Settings -> Build, Execution, Deployment -> Compiler
-> Annotation processors -> Enable annotation processing
4. Run/Debug configurations -> Add new configuration -> Spring boot -> Choose
module and set Main class. Do it for all 4 microservices.
You need two configs for Game-service. At second instance of game-service set in
VM options: -Dserver.port=8088 (at first instance you can leave empty - by default)

5. When all configs is ready, start discovery-service first, then all other services.

You can see all instances of services in ​eureka web ui​ : localhost:8081 Two instances of game service and one wallet service

**Tests**

Casino and wallet services containing units test.

**Postman**

Postman scripts are placed in 'postman' package in project root folder.

