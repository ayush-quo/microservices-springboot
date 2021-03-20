## Steps to run application:
1. Create a Git Repository and push limits-service-dev.properties, limits-service-qa.properties and limits-service.properties.
2. Make changes in application.properties of spring-cloud-config-server like below example.
spring.cloud.config.server.git.uri=#URI#
spring.cloud.config.server.git.username=#username#
spring.cloud.config.server.git.password=#####

3. Run as a spring application.
## URLs

### Limits Service
- http://localhost:8080/limits

### Cloud Config Server
- http://localhost:8888/limits-service/default
- http://localhost:8888/limits-service/qa
- http://localhost:8888/limits-service/dev