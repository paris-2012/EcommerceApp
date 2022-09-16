# Android Web Store

Android Web Store is a android application mimicking the features of an online store. It is created for learning purposes and is not meant to actually transact products. 
Modern android application development technologies and uses kotlin, the best language for android development.

## About

The app is a simple ecommerce application in which you can create an account, browse products, add them to a cart and check them out.

## Features

•Registration - Create an account through an API request
•Login - Login to account through API request can save login credentials using shared preferences
•Category View - Gets categories through API request and displays categories of products in a recycler view
•Subcategory View - Gets subcategories through API request and displays subcategories of products with a tablayout and the respective products in the tabs
•Product View - Gets product information through API request
•Shopping Cart - Adds products to cart using a local database managed by RoomDb
•Checkout - Sends an order request to the API with information given regarding address, payment info, account id and products

## Screenshots

![image](https://user-images.githubusercontent.com/68170232/190650053-6d31b7fe-eb28-42eb-bffd-a69fc79da133.png) ![image](https://user-images.githubusercontent.com/68170232/190651025-3a45489c-0a90-4148-8c8a-d4ee2f920934.png) ![image](https://user-images.githubusercontent.com/68170232/190651232-28167c0b-f103-4013-a6de-0457d723a5a5.png) ![image](https://user-images.githubusercontent.com/68170232/190651281-05ba5ba4-013d-4c15-aefe-d99399a1da64.png) ![image](https://user-images.githubusercontent.com/68170232/190651380-f7e8fd26-dba5-4807-94ff-717354e7b143.png) ![image](https://user-images.githubusercontent.com/68170232/190651420-c6c20e9e-4af4-4893-88e0-3f67f9107abe.png)   ![image](https://user-images.githubusercontent.com/68170232/190651465-929f5c8f-f084-4d13-beaf-8dd6c9a44930.png)

## Built with

•Kotlin - For easy to read code
•Volley API - To handle API requests
•Roomdb - To store complex data locally
•Shared Preferences - To store simple data locally
•MVP Architecture - To organize project in a easy to maintain codebase

## Package Structure

com.example.ecommerceapp             # Root Package

.

├── model                            # Model Layer 

│   ├── remote                       # API handling

|   │   

│   ├──local                         # Roomdb Database and Shared Preferences

|

├── presenter                        # Logic for views

└── view                             # Activiies and Adapters


## Architecture

This app uses MVP Architecture

![image](https://user-images.githubusercontent.com/68170232/190653675-0e6b4025-887e-4c07-908e-b50b4905d007.png)

## Thanks!

Thanks for looking at my project! Happy coding!



