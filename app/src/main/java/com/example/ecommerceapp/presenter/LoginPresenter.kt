package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.*

class LoginPresenter(
    private val volleyHandler: VolleyHandler,
    private val loginView: LoginMVP.LoginView
) : LoginMVP.LoginPresenter {

    override fun loginUser(user: LoginCredentials): Array<String> {
        val message = volleyHandler.userLogin(user, object : OperationalCallbackLogin {
            override fun onSuccess(message: Array<String> ) {
                loginView.setResult(message)
            }

            override fun onFailure(message: Array<String> ) {
                loginView.setResult(message)
            }
        })
        return message ?: arrayOf("a")
    }

    companion object {
        const val DEFAULT_MESSAGE = "default message"
    }
}