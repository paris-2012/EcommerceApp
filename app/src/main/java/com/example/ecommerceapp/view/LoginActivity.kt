package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.model.remote.LoginCredentials
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.LoginMVP
import com.example.ecommerceapp.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginMVP.LoginView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val volleyHandler = VolleyHandler(this)
        presenter = LoginPresenter(volleyHandler, this)
        setUpEvents()
    }

    override fun setResult(message: Array<String>) {
        binding.apply {
            edtEmail.text!!.clear()
            edtPassword.text!!.clear()
        }
        if (message[0] == "Login successful") {
            val intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("user_id", message[1])
            startActivity(intent)
        }
    }
    //TODO IMPLEMENT CHECKBOX WITH REMEMBER ME FUNCTION
    private fun setUpEvents() {
        binding.apply {
            btnLogin.setOnClickListener {
                presenter.loginUser(
                    LoginCredentials(
                        edtEmail.text.toString(),
                        edtPassword.text.toString()
                    )
                )
            }
        }
    }
}