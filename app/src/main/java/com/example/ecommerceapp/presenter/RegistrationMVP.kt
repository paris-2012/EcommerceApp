package com.example.ecommerceapp.presenter
import com.example.ecommerceapp.model.remote.User

class RegistrationMVP {

    interface RegistrationView {
        fun setResult(message: String)
    }

    interface RegistrationPresenter {
        fun registerUser(user: User): String
    }
}