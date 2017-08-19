# Notes Application
![](https://raw.githubusercontent.com/phillip-kruger/notes-example/master/notes-example.png)

## Example application to demonstrate [Java EE 7](http://www.oracle.com/technetwork/java/javaee/tech/index.html)

This is a simple application that allows create, read , update and delete notes. 

### Specifications

* JAX-RS 2.0 ([JSR 339](https://www.jcp.org/en/jsr/detail?id=339))
* JAX-WS 2.2 ([JSR 224](https://www.jcp.org/en/jsr/detail?id=224))
* JAXB 2.2 ([JSR 222](https://www.jcp.org/en/jsr/detail?id=222))
* Bean validation 1.1 ([JSR 349](https://www.jcp.org/en/jsr/detail?id=349))
* EJB 3.2 ([JSR 345](https://www.jcp.org/en/jsr/detail?id=345)) 
* Interceptors 1.2 ([JSR 318](https://jcp.org/en/jsr/detail?id=318)) 
* CDI 1.1 ([JSR 346](http://www.cdi-spec.org/))
* Web sockets 1.0 ([JSR 356](https://www.jcp.org/en/jsr/detail?id=356))
* JPA 2.1 ([JSR 338](https://jcp.org/en/jsr/detail?id=338))
* JSON-P 1.0 ([JSR 353](https://jcp.org/en/jsr/detail?id=353))
* JSF 2.2 ([JSR 344](https://www.jcp.org/en/jsr/detail?id=344))

### Libraries

* POJO with [Lombok](https://projectlombok.org/)
* [Apiee](https://github.com/phillip-kruger/apiee) for creating [Swagger](http://swagger.io/) and [Swagger UI](http://swagger.io/swagger-ui/)
* [Stompee](https://github.com/phillip-kruger/stompee) to view the log file
* [Semantic-UI](https://semantic-ui.com/) in the Websocket GUI (via [webjars](http://www.webjars.org/))
* [Bootstrap 3](https://getbootstrap.com/docs/3.3/) in the JSF GUI (via [webjars](http://www.webjars.org/))

### Deployment

* [Wildfly](http://www.wildfly.org/)
* [TomEE](http://tomee.apache.org/)
* [Payara](https://www.payara.fish/)
* [IBM Liberty](https://developer.ibm.com/wasdev/websphere-liberty/)
* [Docker](https://www.docker.com/) and [Kubernetes](https://kubernetes.io/)

### TODO

* JCache ([JSR 107](https://www.jcp.org/en/jsr/detail?id=107))
* Batch 1.0 ([JSR 352](https://jcp.org/en/jsr/detail?id=352))
* Concurrency 1.0 ([JSR 236](https://jcp.org/en/jsr/detail?id=236))
* MVC 1.0 ([JSR 371](https://www.mvc-spec.org/))
* JMS 2.0 ([JSR 343])(https://jcp.org/en/jsr/detail?id=343)

### Later (once Java EE8 is released)

* JSR 366 - Java EE 8
* JSR 367 - The Java API for JSON Binding
* JSR 368 - Java Message Service 2.1
* JSR 369 - Java Servlet 4.0
* JSR 370 - Java API for RESTful Web Services 2.1
* JSR 371 - Model-View-Controller 1.0
* JSR 372 - Java Server Faces 2.3
* JSR 373 - Java EE Management API 1.0
* JSR 374 - Java API for JSON Processing 1.1
* JSR 375 - Java EE Security API 1.0

More details [here](https://github.com/phillip-kruger/notes-example/wiki) 