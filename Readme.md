Main api server is deployed on: http://3.86.43.250/swagger-ui/index.html

Receiver app is deployed on http://54.89.255.183/swagger-ui/index.html

## How to run the project
- run the project with `dev` profile to use H2 database.
- run the project with `prod` profile to use MySQL database.
- use `data` profile to initialize the data.
- use swagger to test the APIs.
- http://localhost:8080/swagger-ui/index.html

## Deployment
- Main project is deployed on AWS EC2 instance.
- ActiveMQ is deployed on AWS EC2 instance.
- Receiver project is deployed on AWS EC2 instance.
- MySQL database is deployed on AWS RDS.
- Use nginx as a reverse proxy server.

## EA Project
1. Have at least 4 entities, with relationships between them.
    - Entity list: Doctor, Patient, Visit, Vital, Investigation (DescriptiveInvestigation and QuantitativeInvestigation), Treatment.
2. At least 2 entities must have full CRUD operations (with REST controller, Service, and Repository)
   - All entities have full CRUD operations.
3. Have at least one Dynamic Query, one Named query, and one Criteria Query.
   - Dynamic Query: Get all investigations.
   - Named Query: Get all Investigations by visit id.
   - Criteria Query: Get all patients using specification builder, get all treatments using specification builder.
4. At least one query involving 3 entities.
   - Get all investigations by patient id involving patient, visit, and investigation entities.
   - Get all treatments specification builder involving patient, visit, and treatment entities.
5. You must have JMS (with two projects, one being a sender and the other a receiver of the message)
   - send patient, visit and doctor information to the queue for reporting and data analysis purposes using JMS.
6. You must have content negotiation.
   - You can get all patients in JSON or XML format. For xml, please use Postman instead of Swagger.
7. You must have Spring Security with at least one API that is public, and at least two roles (and one API for each role).
   - Public API: Get all doctors, get one doctor with id, login, register, actuator.
   - Role:
       - Admin: all user management APIs, all doctor management APIs, all patient management APIs, all visit management APIs, can see all vitals, investigations, treatments but can't mutate them.
       - Practitioner: all patient management APIs, all visit management APIs, all investigations and treatments APIs.
       - Nurse: all patient management APIs except delete, all visit management APIs except delete, all vitals, investigations APIs except delete, can see all treatments but can't mutate them.
       - User: basic role, can only update his/her information. Admin needs to assign a new role to the user once registered.
8. Demonstrate AOP with one use case (using AspectJ)
   - Log all the methods in the service layer using around advice.
   - Log error messages using after throwing advice in the service layer.
9. Upload your work to GitHub, but submit your project to Sakai before the deadline, what is uploaded to Sakai will be graded. You will get extra points if you manage to deploy your work to any platform.
   - GitHub link:
       - Main project: https://github.com/agthumoe/ea-project
       - Receiver project: https://github.com/agthumoe/ea-project-receiver
10. Demonstrate two profiles (development using H2, and deployment using MySQL)
    - Development profile: dev
    - Production profile: prod
    - Data initialization profile: data
11. Make sure your REST API is stateless.
    - Use JWT for authentication and authorization.
12. Make sure you address concurrency in all your entities, and demonstrate both types of locking.
    - Optimistic locking: Doctor, Patient, Visit, Vital, Investigation, Treatment, User.
        - Use `@Version` annotation in the entity class.
    - Pessimistic locking: Treatment.
        - Use `lockMode = LockModeType.PESSIMISTIC_READ` in the Treatment Named Query.
        - Use `@Lock(LockModeType.PESSIMISTIC_READ)` in the `VitalRepository` interface for the `findByVisitId` method.

## Receiver Project
This project receives the message regarding visit info, patient info, and doctor info from the sender project. It then logs and stores the information in the database for further reporting and data analysis purposes.
- Has one endpoint to filter reports.
- Integrated Swagger OpenAPI documentation.
- Stored the information in the database.
- Did not implement Spring Security.

## User Roles
- Admin: 
    - Username: alice
    - Password: password
- Practitioner:
    - Username: bob
    - Password: password
- Nurse:
    - Username: casey
    - Password: password