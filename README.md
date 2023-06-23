# joaov_grpc_communication
Application development using gRPC for inter-service communication and performance comparison with REST

The prototype is developed using gRPC but also with REST (both in Java Spring Boot) for performance comparison using JMeter. Each one has multiple services : Authentication, Category, Ingredient, Sandwich, Reviews, Votes, Reservations, Reports.
The focus of the project was to explore gRPC as service-to-service communication.
To ensure that both prototypes are equal in terms of funcionality and most importante, that they are full functioning there are 641 integration tests (in Postman Collection provided) and 113 Unitary Tests across all services (226 if we sum both protorypes).

## Context of the project

There is a sandwich shop that has been gaining popularity over time. With the increasing demand, the staff at the shop realized they needed a more efficient and convenient way to manage sandwich orders from their customers. Therefore, an application was created that allows customers to view the ingredients and existing sandwiches, make advance sandwich reservations, as well as provide reviews, view reviews from other customers, and even vote positively or negatively on other customers' reviews to express their opinion about them. The sandwiches are characterized by a private identifier, public identifier, name, description, and their respective list of ingredients. Each ingredient is categorized.

Registered users can rate a sandwich by providing text feedback and a score (0 to 5 stars, including half stars). Once someone votes on a review, it cannot be deleted unless done by a moderator. In addition, they can also make reservations for one or multiple sandwiches by providing the desired list of items and the quantity of each item. The reservation will initially be in a non-active state, and once the delivery date has passed, it will be changed to either a "delivered" or "not delivered" state by a shop employee. Registered users can also cancel a reservation.

The administrator is responsible for creating and removing sandwiches, ingredients, and their categories, and has full access to all the application's functionalities.

The moderator is responsible for reviewing customer reviews and can remove them if any violations are found. Additionally, they can view reports made by other users regarding reviews and decide if these reports indeed involve any violations.

Since the developed application is a prototype, it will not be possible to register new users, and the existing users will be defined and created in advance through bootstrap. Additionally, no limits have been set for reservations on a specific date or for ingredient and sandwich stock.

Since the project is developed using REST and gRPC, there is documentation, following the C4 and 4+1 principles, for both prototypes.


## Analysis

### Domain Model:

![ModeloDeDominio](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/2ad1a841-2e98-438b-95f9-bf3d7532ec88)

### Use Case Diagrams 

![CasosDeUsoAuthentication](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/496931d0-35c5-419d-9504-514a1e0d89b9)

![CasosDeUsoCategory](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/d072146f-62ae-4a9b-9cb5-a7cb9caa1fe4)

![CasosDeUsoIngredient](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/e8a94434-1472-486d-88ac-2263cb05a98d)

![CasosDeUsoSandwich](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/36225ad7-e22f-44b4-8fa6-d4ac1586257e)

![CasosDeUsoReview](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/db9ef18d-2c56-4f94-8a91-e45fad11ea6d)

![CasosDeUsoReservation](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/a6661963-3c82-4693-a50e-d88ff39cda28)

![CasosDeUsoReport](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/9b6ed47d-61b4-449a-891a-797638d86c6f)

![CasosDeUsoVotes](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/009ac84e-44b4-4c5b-a23e-88fcae60b533)


## Design

### gRPC Documentation

Here, the architectural representation of the application will be presented and two models will be used for this, C4 and 4+1.
In the diagrams made for this project, not all levels of abstraction are explored since they are not relevant to the understanding of the project.

### Logic View

Level 1:

![VistaLogicaLv1](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/d52d150c-fa0e-4e3d-8ec2-72adefdf56b4)

Level 2:

![VistaLogicaLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/a6be1df6-38b7-4cc4-b912-a7f075341ea2)

 State Diagram of Reservations:

![StateDiagramReservation](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/144587c5-3f19-4b6f-9731-d16dbf7796a5)

### Physical View

![VistaFisica](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/4b4f6761-9fd6-4d95-a803-9c7b5a86a2ca)



The database per service pattern:

![DatabasePerService](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/879d52de-05cf-4cda-bf20-3e11df7929c7)


### Process View

Here there will be only three diagrams to provide example.

Get catalog of sandwiches (Level 3):

![getCatalogOfSandwichSequenceLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/5b0710de-a1e0-4d1d-908a-6e7ec1ce64a9)

Create Reservation (Level 2):

![createReservationSequencegRPCLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/a582409c-1cdd-4861-b662-8d0b5fd440e5)

Create Reservation (Level 3):

![CreateReservationSequencegRPCLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/29351d9b-1cc1-4e93-a37e-fada3437b512)


### Development View

![ImplementacaoGrpc](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/2d43b653-bba1-46ae-81da-bb3d3ba5f5c4)


### REST Documentation

### Logic View

Level 1: The same as in gRPC

Level 2:

![RESTVistaLogicaLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/37a9a997-c481-4186-9460-d358feb4c489)

State Diagram: The same as in gRPC

### Physical View

![RESTVistaFisica](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/f5d728dc-09c1-4ff0-b722-e1dbc742692c)

### Process View

Get catalog of sandwiches (Level 3): The same as in gRPC

Create Reservation (Level 2):

![RESTcreateReservationSequenceLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/c79ab957-0e8a-47ed-8eee-ead34a0407f1)


Create Reservation (Level 3):

![RESTCreateReservationSequenceLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/24c0f370-18e1-4d70-89d0-04eb70c3a1a4)



### Development View

![ImplementacaoREST](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/7d6e8a8d-3fa0-4ebb-95bf-4823e1ef4dd2)



