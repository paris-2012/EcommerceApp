package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.databinding.ActivityPaymentBinding

class PaymentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        val userId = intent.getStringExtra("user_id")
        val place = intent.getStringExtra("place")
        val address = intent.getStringExtra("address")
        binding.btnCheckout.setOnClickListener {
            val paymentId = binding.radioPayment.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(paymentId)
            val paymentMethod = radioButton.text.toString()
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("address", address)
            intent.putExtra("user_id", userId)
            intent.putExtra("place", place)
            intent.putExtra("paymentMethod", paymentMethod)
            ContextCompat.startActivity(this, intent, null)
        }
    }
}