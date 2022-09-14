package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.databinding.ActivityOrderBinding
import com.example.ecommerceapp.databinding.ActivityRegistrationBinding
import com.example.ecommerceapp.model.local.AppDatabase
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.remote.Order
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.OrderMVP
import com.example.ecommerceapp.presenter.OrderPresenter
import com.example.ecommerceapp.presenter.RegistrationMVP
import com.example.ecommerceapp.presenter.RegistrationPresenter
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class OrderActivity : AppCompatActivity(), OrderMVP.OrderView {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var cartAdapter: OrderAdapter
    private lateinit var presenter: OrderPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        val volleyHandler = VolleyHandler(this)
        presenter = OrderPresenter(volleyHandler, this)

    }
    private fun initView() {
        database = AppDatabase.getInstance(applicationContext)
        cartDao = database.cartDao()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@OrderActivity)
        }
        val userId = intent.getStringExtra("user_id")
        val place = intent.getStringExtra("place")
        val address = intent.getStringExtra("address")
        val paymentMethod = intent.getStringExtra("paymentMethod")

        val daoOutput = cartDao.getAllCartItems()
        cartAdapter = OrderAdapter(ArrayList(daoOutput))
        binding.recyclerView.adapter = cartAdapter
        var total = 0
        for (item in daoOutput) {
            total += item.price.toInt() * item.quantity
        }
        binding.txtTotal.text = "Total: $${total.toString()}"
        binding.txtPlace.text = place
        binding.txtAddress.text = address
        binding.txtPaymentMethod.text = paymentMethod
        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            val items = JSONArray()
            for (i in daoOutput) {
                val cartItem = JSONObject()
                cartItem.put("product_id", i.id)
                cartItem.put("quantity", i.quantity)
                cartItem.put("unit_price", i.price)
                items.put(cartItem)
            }
            val order = Order(userId?.toInt()?:1, place?:"", address?:"", items, billAmount = total, paymentMethod?:"")
            presenter.sendOrder(order)
        }
    }

    override fun setResult(message: String) {

    }
}