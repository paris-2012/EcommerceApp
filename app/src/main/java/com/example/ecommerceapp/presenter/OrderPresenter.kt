package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.Order
import com.example.ecommerceapp.model.remote.User
import com.example.ecommerceapp.model.remote.VolleyHandler

class OrderPresenter(
    private val volleyHandler: VolleyHandler,
    private val registrationView: OrderMVP.OrderView
) : OrderMVP.OrderPresenter {
    override fun sendOrder(order: Order): String {
        val message = volleyHandler.orderConfirmation(order, object : OperationalCallback {
            override fun onSuccess(message: String) {
                registrationView.setResult(message)
            }

            override fun onFailure(message: String) {
                registrationView.setResult(message)
            }
        })
        return message ?: DEFAULT_MESSAGE
    }

    companion object {
        const val DEFAULT_MESSAGE = "default message"
    }
}