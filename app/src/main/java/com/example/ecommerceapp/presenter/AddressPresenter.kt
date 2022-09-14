package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.OperationalCallbackCategories
import com.example.ecommerceapp.model.remote.VolleyHandler

class AddressPresenter(
    private val volleyHandler: VolleyHandler,
    private val addressView: AddressMVP.AddressView
) : AddressMVP.AddressPresenter {
    override fun findAddresses(userId: String?): MutableList<Array<String>> {
        val message = volleyHandler.getAddresses(userId ?: "1", object :
            OperationalCallbackCategories {

            override fun onSuccess(message: MutableList<Array<String>>) {
                addressView.setResult(message)
            }

            override fun onFailure(message: MutableList<Array<String>>) {
                addressView.setResult(message)
            }
        })
        return message
    }
    override fun addAddress(userId: String, title: String, address: String): String {
        val message = volleyHandler.addAddress(userId, title, address, object :
            OperationalCallback {
            override fun onSuccess(message: String) {
                addressView.changeResult(message)
            }

            override fun onFailure(message: String) {
                addressView.changeResult(message)
            }
        })
        return message ?: ""
    }

}