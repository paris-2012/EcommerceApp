package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.databinding.ActivityProductsBinding
import com.example.ecommerceapp.model.remote.OperationalCallback
import java.util.ArrayList
import com.example.ecommerceapp.model.remote.VolleyHandler

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var categoryAdapter: CategoriesListAdapter
    private var items: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {

        binding.btnChat.setOnClickListener {
            val intent = Intent(this, SupportChatActivity::class.java)
            intent.putExtra("position", -1)
            startActivity(intent)
        }


        val volleyHandler = VolleyHandler(this)
        items = volleyHandler.getCategories()
        //temporary until api edition utilized
        items.add("Smart Phones")
        items.add("Laptops")
        items.add("Men's Fashion")
        items.add("Women's Fashion")
        items.add("Kid's Fashion")
        items.add("Home Appliances")
        items.add("Grocery")
        items.add("Beauty & Cosmetics")
        binding.recyclerView.layoutManager = LinearLayoutManager(this@ProductsActivity)
        categoryAdapter = CategoriesListAdapter(items as ArrayList<String>)
        binding.recyclerView.adapter = categoryAdapter
    }
}