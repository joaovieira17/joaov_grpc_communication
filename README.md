# joaov_grpc_communication
Application development using gRPC for inter-service communication and performance comparison with REST

The prototype is developed using gRPC but also with REST. Each one has multiple services : Authentication, Category, Ingredient, Sandwich, Reviews, Votes, Reservations, Reports.
Since the focus of the project was to explore gRPC as service-to-service communication, the diagrams provided are only for the gRPC prototype.

## Context of the project

There is a sandwich shop that has been gaining popularity over time. With the increasing demand, the staff at the shop realized they needed a more efficient and convenient way to manage sandwich orders from their customers. Therefore, an application was created that allows customers to view the ingredients and existing sandwiches, make advance sandwich reservations, as well as provide reviews, view reviews from other customers, and even vote positively or negatively on other customers' reviews to express their opinion about them. The sandwiches are characterized by a private identifier, public identifier, name, description, and their respective list of ingredients. Each ingredient is categorized.

Registered users can rate a sandwich by providing text feedback and a score (0 to 5 stars, including half stars). Once someone votes on a review, it cannot be deleted unless done by a moderator. In addition, they can also make reservations for one or multiple sandwiches by providing the desired list of items and the quantity of each item. The reservation will initially be in a non-active state, and once the delivery date has passed, it will be changed to either a "delivered" or "not delivered" state by a shop employee. Registered users can also cancel a reservation.

The administrator is responsible for creating and removing sandwiches, ingredients, and their categories, and has full access to all the application's functionalities.

The moderator is responsible for reviewing customer reviews and can remove them if any violations are found. Additionally, they can view reports made by other users regarding reviews and decide if these reports indeed involve any violations.

The project is developed using REST and gRPC, but since the main focus is on studying and exploring gRPC, all the documentation will be for the application that uses gRPC as the communication method between services.

Since the developed application is a prototype in practice, it will not be possible to register new users, and the existing users will be defined and created in advance through bootstrap. Additionally, no limits have been set for reservations on a specific date or for ingredient and sandwich stock.

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

Here, the architectural representation of the application will be presented and two models will be used for this, C4 and 4+1.
In the diagrams made for this project, not all levels of abstraction are explored since they are not relevant to the understanding of the project.

### Logic View

Level 1:

![VistaLogicaLv1](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/d52d150c-fa0e-4e3d-8ec2-72adefdf56b4)

Level 2:

![VistaLogicaLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/e16a07d8-fefe-4b79-9fdc-b3661e39ed7c)

 State Diagram of Reservations:

![StateDiagramReservation](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/144587c5-3f19-4b6f-9731-d16dbf7796a5)

### Physical View

![VistaFisica](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/0c07ee18-5f74-4222-a700-884338a460fa)

The database per service pattern:

![Arquitetura](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/43c2b842-b1d7-4535-be02-ecd106ab536b)


### Process View

Here there will be only three diagrams to provide example.

Get catalog of sandwiches (Level 3):

![getCatalogOfSandwichSequenceLv3](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/5b0710de-a1e0-4d1d-908a-6e7ec1ce64a9)

Create Reservation (Level 2):

![createReservationSequencegRPCLv2](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/a582409c-1cdd-4861-b662-8d0b5fd440e5)

Create Reservation (Level 3):

![CreateReservationSequencegRPCLv3-Teste](https://github.com/joaovieira17/joaov_grpc_communication/assets/84910996/c426ecce-99d4-4112-b394-492ca8055e9e)




