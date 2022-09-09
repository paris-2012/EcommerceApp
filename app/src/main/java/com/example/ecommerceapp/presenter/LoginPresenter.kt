package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.LoginCredentials
import com.example.ecommerceapp.model.remote.User
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.VolleyHandler

class LoginPresenter(
    private val volleyHandler: VolleyHandler,
    private val loginView: LoginMVP.LoginView
) : LoginMVP.LoginPresenter {

    override fun loginUser(user: LoginCredentials): String {
        val message = volleyHandler.userLogin(user, object : OperationalCallback {
            override fun onSuccess(message: String) {
                loginView.setResult(message)
            }

            override fun onFailure(message: String) {
                loginView.setResult(message)
            }
        })
        return message ?: DEFAULT_MESSAGE
    }

    companion object {
        const val DEFAULT_MESSAGE = "default message"
    }
}