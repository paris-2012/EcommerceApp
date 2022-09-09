package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.User
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.VolleyHandler

class RegistrationPresenter(
    private val volleyHandler: VolleyHandler,
    private val registrationView: RegistrationMVP.RegistrationView
) : RegistrationMVP.RegistrationPresenter {
    override fun registerUser(user: User): String {
        val message = volleyHandler.userRegistration(user, object : OperationalCallback {
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