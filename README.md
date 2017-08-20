# Notes Application
![](https://raw.githubusercontent.com/phillip-kruger/notes-example/master/build-on.png)

## Example application to demonstrate [Java EE 7](http://www.oracle.com/technetwork/java/javaee/tech/index.html)

* [Quick start](https://github.com/phillip-kruger/notes-example/wiki/Overview)
* [Kubernetes](https://github.com/phillip-kruger/notes-example/wiki/Kubernetes)

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
* JMS 2.0 ([JSR 343](https://jcp.org/en/jsr/detail?id=343))

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
* [IBM Liberty](https://developer.ibm.com/wasdev/websphere-liberty/) (not working :( )
* [Docker](https://www.docker.com/) and [Kubernetes](https://kubernetes.io/)

### TODO

* JCache ([JSR 107](https://www.jcp.org/en/jsr/detail?id=107))
* Batch 1.0 ([JSR 352](https://jcp.org/en/jsr/detail?id=352))
* Concurrency 1.0 ([JSR 236](https://jcp.org/en/jsr/detail?id=236))
* MVC 1.0 ([JSR 371](https://www.mvc-spec.org/))
* JCA 1.7 ([JSR 322](https://jcp.org/en/jsr/detail?id=322))

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

### High level design

![](https://raw.githubusercontent.com/phillip-kruger/notes-example/master/notes-example.png)

## Application Server specific notes.


### Wildfly
No extra notes

### Payara
No extra notes

### TomEE

As we are using the default Queue (for demo purpose), it needs to be enabled in vanilla TomEE server:

In `conf/system.properties` add:

    openejb.environment.default = true

### IBM WAS Liberty

Edit the server.xml with the following:

* Change the port to 8080 (to be the same as other app servers)

    <httpEndpoint id="defaultHttpEndpoint"
                  httpPort="8080"
                  httpsPort="9443" />

* Make sure the default datastore exist:

In /opt/wlp/usr/shared/resources:

    mkdir derby
    cd derby
    wget https://repo1.maven.org/maven2/org/apache/derby/derby/10.13.1.1/derby-10.13.1.1.jar

In server.xml
    
    <dataSource id="DefaultDataSource">
        <jdbcDriver>
            <library name="derbyLibrary">
               <fileset dir="${shared.resource.dir}/derby"/>
            </library>
        </jdbcDriver>
        <properties.derby.embedded databaseName="memory:notesdatabase"
                    createDatabase="create" />
    </dataSource>


* Configure the Queue (there is not default it seems ?) (see [this link](https://www.ibm.com/support/knowledgecenter/en/was_beta_liberty/com.ibm.websphere.wlp.nd.multiplatform.doc/ae/twlp_msg_single_p2p.html))
 
    TODO: Figure this out ....