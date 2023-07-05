# gRPC for service-to-service communication 
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

![ModeloDeDominio](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/c207f97b-3bbb-4fc1-a8c3-ff3fabb0368f)


### Use Case Diagrams 

![CasosDeUsoAuthentication](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/ef011d9b-6d45-4e0e-9267-d390607b8230)

![CasosDeUsoCategory](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/b66d0c06-ae03-4df1-9869-e4f433a80cbe)

![CasosDeUsoIngredient](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/0c79f4d5-622e-49e0-94ac-eb5dc226735d)

![CasosDeUsoSandwich](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/eb07a489-9700-4d73-b567-142e9acd7ebe)

![CasosDeUsoReview](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/e6d757ed-ce17-4141-ada1-b80a17dc4bea)

![CasosDeUsoReservation](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/0e0bf075-a7cb-41d9-ac3f-5850f83153e6)

![CasosDeUsoReport](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/452b3370-aab3-4238-b9af-252e55510450)

![CasosDeUsoVotes](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/1f874c28-c842-4ebb-abdf-7f5251d9fa4d)


## Design

### gRPC Documentation

Here, the architectural representation of the application will be presented and two models will be used for this, C4 and 4+1.
In the diagrams made for this project, not all levels of abstraction are explored since they are not relevant to the understanding of the project.

### Logic View

Level 1:

![VistaLogicaLv1](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/8ef80cb0-b88d-46ae-8960-8d69fae93b9b)


Level 2:

![VistaLogicaLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/e3d304f3-5367-4efd-a12d-f805a95e5c8f)


 State Diagram of Reservations:

![StateDiagramReservation](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/8fe9549b-a4db-443a-aa03-0b79c30ad08f)

### Physical View

![VistaFisica](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/899317b0-7def-466b-99da-0cb69b1a4ee9)


The database per service pattern:

![DatabasePerService](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/fef104a7-93a4-4331-b2c9-a311bcd039fb)


### Process View

Here there will be only three diagrams to provide example.

Get catalog of sandwiches (Level 3):

![getCatalogOfSandwichSequenceLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/946ae759-498b-4890-aa18-0217bda00510)


Create Reservation (Level 2):

![createReservationSequencegRPCLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/4ed43bc5-af86-482b-806d-3a624011aa32)


Create Reservation (Level 3):

![CreateReservationSequencegRPCLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/8aaa0c43-0b95-40de-8a13-3662c37d9b10)

### Development View

![ImplementacaoGrpc](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/ab0928ea-2278-495e-8821-de2cff17c8d9)

### REST Documentation

### Logic View

Level 1: The same as in gRPC

Level 2:

![RESTVistaLogicaLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/695397fa-a836-4e19-ba7b-5f6a27715d86)


State Diagram: The same as in gRPC

### Physical View

![RESTVistaFisica](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/1ad3120f-5af5-4149-8221-4aa93d8d06c7)

### Process View

Get catalog of sandwiches (Level 3): The same as in gRPC

Create Reservation (Level 2):

![RESTcreateReservationSequenceLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/dee36a40-6a55-421b-a953-083c8b2ecff4)


Create Reservation (Level 3):

![RESTCreateReservationSequenceLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/d8250a15-0e1a-4c87-9227-3fe1ea94efee)


### Development View

![ImplementacaoREST](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/b948f740-5a40-47f3-b525-6cec9f03ce68)


