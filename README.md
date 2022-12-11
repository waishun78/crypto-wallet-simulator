# crypto-wallet-simulator
Cryptocurrency Information System Management Simulator using Spring Framework backend, MySQL database and React front end to track cryptocurrencies owned

## Brief
This web app is intended to allow users to trade cryptocurrencies in a simulated environment. 
Users may create multiple accounts with different objectives and risk profiles and compare the relative performance over time based on their investment strategy. Each account balance will have a user defined account balance used to make trades for their portfolio.
All live cryptocurrency information was queried from the public free API: https://www.coingecko.com/en/api.

## Objects
![Java Tech](https://user-images.githubusercontent.com/64391406/206926887-ba8ce15f-8c08-4f93-b4f4-21e39495aed1.jpg)
Note: accountBalance refers to the money the account currently owns to make future transactions.

## Client Functionalities 
- Create Accounts
- Update Account Information
- Cryptocurrency Transactions
    - Buy Currency
    - Sell Currency
### Stretch Goal
- ~~Monitor value of portfolios of particular trades~~
- View Cryptocurrency Market Value
- ~~Sort/Filter Accounts by performance~~

## Backend Functionalities/Query
- Create, Read, Update and Delete cryptocurrencies owned (CRUD)
- ~~Get all the users with the top performing accounts/portfolios~~
### Stretch Goal
- Sort ~~/Filter~~ cryptocurrencies owned by value
- Sort ~~/Filter~~ all cryptocurrencies by value
- ~~Get all the users with the top performing accounts/portfolios~~

## How to run the application?
The application consists of 3 components: a client (React.js web frontend interface), a Spring RESTful API and a MySQL database. For the ease of setup, dockerfiles have been provided.

### Setting up Spring Backend and MySQL
1. Build a jar file for the spring boot app by doing Maven build in any IDE you are using.
    - This creates a jar file inside the target folder of the project structure.
2. Go to the `app-backend` using `cd app-backend` from the root directory.
3. Run `docker build -t app-backend` to build the docker image for the application using the Dockerfile provided.
4. Run `docker pull mysql` to retrieve the docker image for MySQL.
5. Use `ls` to check if `docker-compose.yml` is in the directory.
    - You now have the docker images to run both the MySQL container and the Spring Boot container. You will aggregate the output of each docker images using docker-compose.yml file.
Run `docker compose up`.
The Spring Boot application is now running on port 8080 on the local host.

### Setting up React
1. Go to the `web-client` using `cd web-client` from the root directory.
2. Create docker images using `docker build . -t react-docker-image`.
3. Run said docker image using `docker run react-docker-image`.
4. The react application is currently running on port 3000 on the local host.

Note: As the Reat.js application is fetching data from both the coingecko API and the app-backend Spring RESTful API, make sure that both are working as intended before running the application when debugging.

### Testing
A `app-backend.postman_collection.json ` has been provided for testing using Postman. Import the collection to run the tests.
