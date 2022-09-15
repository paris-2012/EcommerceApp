package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.databinding.ActivityProductBinding
import com.example.ecommerceapp.model.local.AppDatabase
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.local.CartItem
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.CategoriesMVP
import com.example.ecommerceapp.presenter.CategoriesPresenter
import com.example.ecommerceapp.presenter.ProductMVP
import com.example.ecommerceapp.presenter.ProductPresenter

class ProductActivity : AppCompatActivity(), ProductMVP.ProductView {
    private lateinit var binding: ActivityProductBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao

    private lateinit var presenter: ProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        database = AppDatabase.getInstance(applicationContext)
        cartDao = database.cartDao()
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("product_id")
        val volleyHandler = VolleyHandler(this)
        presenter = ProductPresenter(volleyHandler, this)
        setResult(presenter.findProductInfo(productId))
    }

    override fun setResult(productInfo: Array<String>) {
        binding.txtTitle.text = productInfo[0]
        binding.txtPrice.text = "$${productInfo[3]}"
        binding.txtDescription.text = productInfo[4]
        binding.imgProduct.setImageResource((R.drawable.ic_baseline_phone_android_24))
        binding.btnAddCart.setOnClickListener {
            val note = CartItem(productInfo[2].toLong(),  productInfo[0], productInfo[3], 1)
            cartDao.insertCartItem(note)
        }
    }
}
