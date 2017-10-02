Example of Spring Boot 2 server / client application with Flux controllers
-

Server emits events every 100 ms and client retrieves those events, buffers them and makes packages (from 3-sec events window), that are emitted to the endpoint.

Endpoints:

* server: curl -i http://localhost:8080/infos
* client: curl -i http://localhost:8081/