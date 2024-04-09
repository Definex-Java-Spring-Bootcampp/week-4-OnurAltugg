This project includes a set of services for the Kredi Bizde system. These services include Kredi-Bizde main service, Akbank service, Garanti service, Notification service, Gateway server and Discovery server.

In our main service, kredi-bizde service, API endpoints are provided for controllers managing campaigns, applications, credit cards, loans, banks, and users. Leveraging MySQL as the database, these controllers ensure seamless interaction for efficient storage. kredibizde service -> port 8080

In garanti-service and akbank-service we keep applications that were made specifically for this purpose. These applications can be made from within akbank and garanti service or kredibizde-service. akbank service -> port 8081 garanti service -> port 8082

In notification-service, we retrieve detailed logs that encompass essential mail information registered by users within the kredibizde-service, leveraging the RabbitMQ messaging system for seamless communication and efficient data processing. This integration ensures comprehensive tracking and management of user interactions.

In discovery server, we can register our service applications to eureka server and provide communication with each other from a single point.  After the projects stand up, our services defined as eureka client connect to the eureka server and in this way, we monitor and manage features such as network communication load balancer of our applications from the center. discovery server -> port 8761

By using spring cloud gateaway in gw-server, it allows us to manage the requests coming to our services from a single place and direct all incoming requests to the relevant endpoints of the relevant services from here. gw server -> port 8084

The "SQL Scripts" folder comprises SQL scripts designed to create MySQL databases for the kredi-bizde service, akbank service, and garanti service. Since we only keep applications in akbank and garanti service, I did not put its EER Diagram. EER Diagram of kredi-bizde service can be found in EER_Diagram.png file.

Basic auth: spring.security.user.name = spring, spring.security.user.password = 1234

Swagger UI: localhost:8080/swagger-ui/index.html
