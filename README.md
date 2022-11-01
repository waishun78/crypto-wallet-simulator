# crypto-wallet-simulator
Cryptocurrency Information System Management Simulator using Spring Framework backend, PostgresQL database and React front end to track Cryptocurrencies owned

## Brief
This web app is intended to allow users to trade cryptocurrencies in a simulated environment. 
Users may create multiple accounts with different objectives and risk profiles and compare the relative performance over time based on their investment strategy. Each account balance will have a user defined account balance used to make trades for their portfolio.

Stretch goal:
Query live crypto trading API for real-time information: https://www.coingecko.com/en/api

## Objects
![Java Tech](https://user-images.githubusercontent.com/64391406/199349224-dd5fd28c-35c2-424c-a538-6bed3e42d71f.jpg)
Note: accountBalance refers to the money the account has to make cryptocurrency transactions (which defers from the derived value of the portfolio based on market value of cryptocurrencies owned.

## Client Functionalities 
- Create Accounts
- Cryptocurrency Transaction
### Stretch Goal
- Monitor value of portfolios of particular trades
- View Cryptocurrency Market Value
- Sort/Filter Accounts by performance

## Backend Functionalities/Query
- Create, Read, Update and Delete cryptocurrencies owned (CRUD)
- Get all the users with the top performing accounts/portfolios
### Stretch Goal
- Sort/Filter cryptocurrencies owned by value
- Sort/Filter all cryptocurrencies by value
