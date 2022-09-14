package com.example.ecommerceapp.presenter

class AddressMVP {
    interface AddressView {
        fun setResult(names: MutableList<Array<String>>)
        fun changeResult(message: String)
    }

    interface AddressPresenter {
        fun findAddresses(userId: String?): MutableList<Array<String>>
        fun addAddress(userId: String, title: String, address: String): String
    }
}