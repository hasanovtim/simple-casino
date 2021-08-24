# Simple Casino
*Miscroservice oriented project with service registry, load balancer, API getway, unit and postman tests. Project made in scope of test task for one of Kyiv IT companies. Initial requirements you can find [here](https://github.com/hasanovtim/simple-casino/blob/main/docs/assignment.pdf)*

## Architecture of microservices
![](https://github.com/hasanovtim/simple-casino/blob/main/docs/architecture.jpeg)

## Quick tech overview
Main tech stack - java with spring boot 
**Dependency management** system is  maven. Parent [pom.xml](https://github.com/hasanovtim/simple-casino/blob/main/pom.xml) file in root folder ‘microservice’ contains 4 modules. 
- **Discovery** (port 8081) - service registration and discovery using [Eureka Server](https://spring.io/guides/gs/service-registration-and-discovery)
- **Gateway** (8080) - API router using [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- **Game-service** (8083) - responsible for game activities like placeBet, getBetsByGame, getAllBets. Also it has rest communication with wallet-service using [fein](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html) client. Rest calls carried out by dinamic service name (name registered in Eureka server). So all calls are load balanced. If we have multiple instances of wallet-service running it will handle it ([view code](https://github.com/hasanovtim/simple-casino/blob/main/game-service/src/main/java/com/simplecasino/gameservice/service/WalletClient.java))
- **Wallet-service** (8084) - registerWallet, allWallets, getBalance, deposit, withdraw

**Error handling**
Game and Wallet servives have global error handling using spring AOP and custom exceptions ([view code](https://github.com/hasanovtim/simple-casino/tree/main/game-service/src/main/java/com/simplecasino/gameservice/exception))

**Transaction management**
Transactions are managed using spring @Transcational annotation in all not atomic operations, like deposit, withdrow, placeBet

**DB**
H2 testDb - in memory database


## Requirements
- Java 8+
- Maven
- Git client

## How to run in command line (mac)
1. Clone project to local disk
    ```sh
    git clone git@github.com:hasanovtim/simple-casino.git
    ```
2. Install maven 
    ```sh
    brew install maven
    ```
3. Cd to microservice directory
4. Run
    ```sh
    brew install maven
    ```
5. Run all services
    ```sh
    java -jar discovery/target/discovery-0.0.1-SNAPSHOT.jar
    java -jar gateway/target/gateway-0.0.1-SNAPSHOT.jar
    java -jar wallet-service/target/wallet-service-0.0.1-SNAPSHOT.jar
    java -jar game-service/target/game-service-0.0.1-SNAPSHOT.jar
    ```
6. To run multiple instances of services you can use same command with different (from default) port numbers
    ```sh
    java -jar -Dserver.port={PORT} wallet-service/target/wallet-service-0.0.1-SNAPSHOT.jar
    java -jar -Dserver.port={PORT} game-service/target/game-service-0.0.1-SNAPSHOT.jar
    ```
  
## How to run in Intellij Idea

1. Import project from existing sources as Maven project. Choose parent folder ‘microservice’
2. Wait until all dependencies will be loaded
3. Enable annotation processor: Settings -> Build, Execution, Deployment -> Compiler
-> Annotation processors -> Enable annotation processing
4. Run/Debug configurations -> Add new configuration -> Spring boot -> Choose
module and set main App.class. Do it for all 4 microservices.
You need two configs for Game-service. At second instance of game-service set in
VM options: -Dserver.port=8088 (at first instance you can leave empty - by default)
5. When all configs is ready, start discovery-service first, then all other services.

## Eureka service monitoring
You can see all instances of services in eureka web http://localhost:8081

![](https://github.com/hasanovtim/simple-casino/blob/main/docs/eureka.png)

## Postman
Postman scripts are placed in [postman](https://github.com/hasanovtim/simple-casino/tree/main/postman) package in project root folder. Don't forget to import environment into your postman (local_env.postman_environment.json)

## Furure plans

In scope of business logic
- add validations
- add rounding for bigdecimals
- add support of currency
- add authentification and authorization

Tech improvements
- wrap project to docker compose
- add health metrics
- add e2e tests
- change sync communication between services to message broker (kafka)
- change maven to gradle
 

