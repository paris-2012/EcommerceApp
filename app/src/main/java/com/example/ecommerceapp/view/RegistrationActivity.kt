package com.example.ecommerceapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.databinding.ActivityRegistrationBinding
import com.example.ecommerceapp.model.remote.User
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.RegistrationMVP
import com.example.ecommerceapp.presenter.RegistrationPresenter

class RegistrationActivity : AppCompatActivity(), RegistrationMVP.RegistrationView {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var presenter: RegistrationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val volleyHandler = VolleyHandler(this)
        presenter = RegistrationPresenter(volleyHandler, this)
        setUpEvents()
    }

    override fun setResult(message: String) {
        binding.apply {
            edtName.text!!.clear()
            edtMobile.text!!.clear()
            edtEmail.text!!.clear()
            edtPassword.text!!.clear()
        }
    }

    private fun isValidUser(user: User): Boolean {
        if (user.mobile.length < 9 || user.mobile.length > 12) {
            binding.txtError.text = "invalid mobile"
            return false
        }
        for (digit in user.mobile) {
            if (!"1234567890".contains(digit)) {
                binding.txtError.text = "invalid mobile"
                return false
            }
        }
        if (!user.email.contains("@")) {
            binding.txtError.text = "invalid email"
            return false
        }
        if (!user.email.contains(".")) {
            binding.txtError.text = "invalid email"
            return false
        }
        if (user.password.length < 9) {
            binding.txtError.text = "invalid password"
            return false
        }
        return true
    }

    private fun setUpEvents() {
        binding.apply {
            btnRegister.setOnClickListener {
                val user = User(
                    edtName.text.toString(),
                    edtMobile.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
                if (isValidUser(user)) {
                    presenter.registerUser(user)
                }
            }
        }
    }
}