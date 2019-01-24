# E-shop with microservices

This is an example of a web shop implemented with microservices. I have 3 components used for business logic, one for UI
and an Eureka naming server.

All microservices use Ribbon for load balancing.

I use both MySQL and Postrgres databases just as an exercise, but if you want to run this demo app make sure you have both of them installed.

When starting the application first start the Eureka naming server and afterwards the rest of the microservices. You can also play with 
multiple instances of each microservice.
