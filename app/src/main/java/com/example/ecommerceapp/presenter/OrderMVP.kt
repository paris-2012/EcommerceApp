package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.Order
import com.example.ecommerceapp.model.remote.User

class OrderMVP {

    interface OrderView {
        fun setResult(message: String)
    }

    interface OrderPresenter {
        fun sendOrder(order: Order): String
    }
}