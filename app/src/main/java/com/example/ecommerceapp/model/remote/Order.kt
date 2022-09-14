package com.example.ecommerceapp.model.remote

import org.json.JSONArray

data class Order(val userId: Int, val title: String, val address: String, val items: JSONArray, val billAmount: Int, val paymentMethod: String)