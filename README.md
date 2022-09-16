# Android Web Store

Android Web Store is a android application mimicking the features of an online store. It is created for learning purposes and is not meant to actually transact products. The app uses modern android application development technologies and uses kotlin, the best language for android development.

## About

The app is a simple ecommerce application in which you can create an account, browse products, add them to a cart and check them out.

## Features

•Registration - Create an account through an API request<br />
•Login - Login to account through API request can save login credentials using shared preferences<br />
•Category View - Gets categories through API request and displays categories of products in a recycler view<br />
•Subcategory View - Gets subcategories through API request and displays subcategories of products with a tablayout and the respective products in the tabs<br />
•Product View - Gets product information through API request<br />
•Shopping Cart - Adds products to cart using a local database managed by RoomDb<br />
•Checkout - Sends an order request to the API with information given regarding address, payment info, account id and products<br />

## Screenshots

<img src="https://user-images.githubusercontent.com/68170232/190650053-6d31b7fe-eb28-42eb-bffd-a69fc79da133.png" width="200"/><img src="https://user-images.githubusercontent.com/68170232/190651025-3a45489c-0a90-4148-8c8a-d4ee2f920934.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190651232-28167c0b-f103-4013-a6de-0457d723a5a5.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190651281-05ba5ba4-013d-4c15-aefe-d99399a1da64.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190651380-f7e8fd26-dba5-4807-94ff-717354e7b143.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190651420-c6c20e9e-4af4-4893-88e0-3f67f9107abe.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190651465-929f5c8f-f084-4d13-beaf-8dd6c9a44930.png" width="200"/>
<img src="https://user-images.githubusercontent.com/68170232/190656578-021d3aad-b8f5-4d3b-8da8-3aa0a44b14b4.png" width="200"/>


## Built with

•Kotlin - For easy to read code<br />
•Volley API - To handle API requests<br />
•Roomdb - To store complex data locally<br />
•Shared Preferences - To store simple data locally<br />
•MVP Architecture - To organize project in a easy to maintain codebase<br />

## Package Structure

com.example.ecommerceapp             # Root Package<br />
.<br />
├── model                            # Model Layer <br />
│<br />
│   ├── remote                       # API handling<br />
│   │   <br />
│   ├──local                         # Roomdb Database and Shared Preferences<br />
│<br />
│── presenter                        # Logic for views<br />
│<br />
└── view                             # Activiies and Adapters<br />


## Architecture

This app uses MVP Architecture

![image](https://user-images.githubusercontent.com/68170232/190653675-0e6b4025-887e-4c07-908e-b50b4905d007.png)

## Thanks!

Thanks for looking at my project! Happy coding!



