## To run tha app locally
1. Run the database docker container (you can open the root directory of the project in the IntelliJIdea and press the run button for the 'postgres' service)
2. Open pgAdmin and create database with credentials: user - 'root', password - 'password'
2. External port from the computer shouldn't be busy by internal process (for example, by your computer postgres server), the default port is 5432, try different, f.e. 5433
3. Open auth-api in a separate IntelliJIdea window and run the root "AuthApiApplication" class
4. Open data-api in a separate IntelliJIdea window and run the root "DataApiApplication" class
5. Open Postman and try endpoints: http://localhost:8080/api/auth/register, http://localhost:8080/api/auth/login, http://localhost:8080/process - with the appropriate body data and methods
6. Check the database in pgAdmin (there must be tables "users", "processing_log")


## To run the app in the docker containers
1. Run the docker-compose.yml file or open the root directory of the project in the IntelliJIdea and press the run buttons for every service
2. Open Postman and try endpoints: http://localhost:8080/api/v1/auth/register, http://localhost:8080/api/v1/auth/login, http://localhost:8080/api/process - with the appropriate body data and methods
P.S. The domain in the docker containers is localhost, because for the goal of this project I didn't want to use the real domain name# winwin-test
