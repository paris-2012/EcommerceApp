package com.example.ecommerceapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.model.remote.LoginCredentials
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.LoginMVP
import com.example.ecommerceapp.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginMVP.LoginView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val volleyHandler = VolleyHandler(this)
        presenter = LoginPresenter(volleyHandler, this)
        setUpEvents()
    }

    override fun setResult(message: Array<String>) {
        if (message[0] == "Login successful") {
            val intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("user_id", message[1])
            startActivity(intent)
        }
    }
    private fun setUpEvents() {
        binding.apply {
            sharedPreferences = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE)
            edtEmail.setText(sharedPreferences.getString("EMAIL", ""))
            edtPassword.setText(sharedPreferences.getString("PASSWORD", ""))
            editor = sharedPreferences.edit()
            btnLogin.setOnClickListener {
                if (checkRememberMe.isChecked) {
                    editor.apply {
                        putString("EMAIL", edtEmail.text.toString())
                        putString("PASSWORD", edtPassword.text.toString())
                        editor.commit()
                    }
                }
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