package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityAddressBinding
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.databinding.ActivitySectionBinding
import com.example.ecommerceapp.model.local.AppDatabase
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.AddressMVP
import com.example.ecommerceapp.presenter.AddressPresenter
import com.example.ecommerceapp.presenter.SectionMVP
import com.example.ecommerceapp.presenter.SectionPresenter
import com.google.android.material.tabs.TabLayoutMediator


class AddressActivity : AppCompatActivity(), AddressMVP.AddressView {
    private lateinit var binding: ActivityAddressBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var presenter: AddressPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val userId = intent.getStringExtra("user_id")
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)

        val volleyHandler = VolleyHandler(this)
        presenter = AddressPresenter(volleyHandler, this)
        setContentView(binding.root)

        setResult(presenter.findAddresses(userId))
        binding.btnAddAddress.setOnClickListener {
            changeResult(presenter.addAddress(userId ?: "1", binding.edtTitle.text.toString(), binding.edtAddress.text.toString()))
        }
    }

    override fun setResult(names: MutableList<Array<String>>) {
        val userId = intent.getStringExtra("user_id")
        binding.recyclerView.layoutManager = LinearLayoutManager(this@AddressActivity)
        adapter = AddressAdapter(names, userId?:"1", this@AddressActivity)
        binding.recyclerView.adapter = adapter

    }

    override fun changeResult(message: String) {
        val userId = intent.getStringExtra("user_id")
        setResult(presenter.findAddresses(userId))
    }
}