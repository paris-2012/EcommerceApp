package com.example.ecommerceapp.presenter
import com.example.ecommerceapp.model.remote.LoginCredentials

class LoginMVP {

    interface LoginView {
        fun setResult(message: String)
    }

    interface LoginPresenter {
        fun loginUser(user: LoginCredentials): String
    }
}