package com.example.ecommerceapp.model.remote

interface OperationalCallback {
    fun onSuccess(message: String)
    fun onFailure(message: String)
}

interface OperationalCallbackLogin {
    fun onSuccess(message: Array<String>)
    fun onFailure(message: Array<String>)
}
interface OperationalCallbackCategories {
    fun onSuccess(message: MutableList<Array<String>>)
    fun onFailure(message: MutableList<Array<String>>)
}

interface OperationalCallbackProduct {
    fun onSuccess(message: Array<String>)
    fun onFailure(message: Array<String>)
}