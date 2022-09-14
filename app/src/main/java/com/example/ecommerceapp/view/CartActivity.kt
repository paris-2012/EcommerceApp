package com.example.ecommerceapp.view
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.model.local.AppDatabase
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.local.CartItem
import java.util.*


class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }
    private fun initView() {
        database = AppDatabase.getInstance(applicationContext)
        cartDao = database.cartDao()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@CartActivity)
        }
        val userId = intent.getStringExtra("user_id")
        binding.btnAddress.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }
        fetchCart()
    }

    private fun fetchCart() {
        cartAdapter = CartAdapter(ArrayList(cartDao.getAllCartItems()))
        binding.recyclerView.adapter = cartAdapter
    }
}