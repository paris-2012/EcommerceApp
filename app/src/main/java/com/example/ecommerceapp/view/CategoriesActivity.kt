package com.example.ecommerceapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.databinding.ActivityCategoriesBinding
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.CategoriesMVP
import com.example.ecommerceapp.presenter.CategoriesPresenter
import com.example.ecommerceapp.presenter.LoginPresenter

class CategoriesActivity : AppCompatActivity(), CategoriesMVP.CategoriesView {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var presenter: CategoriesPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        val volleyHandler = VolleyHandler(this)
        presenter = CategoriesPresenter(volleyHandler, this)
        setContentView(binding.root)
        initView()
        setResult(presenter.findCategories())
    }
    private fun initView() {

        val userId = intent.getStringExtra("user_id")
        Log.i("userid", userId?:"alksdf")
        binding.btnChat.setOnClickListener {
            val intent = Intent(this, SupportChatActivity::class.java)
            startActivity(intent)
        }
        binding.btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }
    }

    override fun setResult(names: MutableList<Array<String>>) {
        binding.recyclerView.layoutManager = GridLayoutManager(this@CategoriesActivity, 2)
        categoryAdapter = CategoriesAdapter(names, this@CategoriesActivity)
        binding.recyclerView.adapter = categoryAdapter
    }
}