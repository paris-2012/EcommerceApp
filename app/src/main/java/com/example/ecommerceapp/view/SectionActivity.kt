package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivitySectionBinding
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.SectionMVP
import com.example.ecommerceapp.presenter.SectionPresenter
import com.google.android.material.tabs.TabLayoutMediator

class SectionActivity : AppCompatActivity(), SectionMVP.SectionView {
    private lateinit var binding: ActivitySectionBinding
    private lateinit var adapter: SectionAdapter
    private lateinit var presenter: SectionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val categoryId = intent.getStringExtra("category")
        super.onCreate(savedInstanceState)
        binding = ActivitySectionBinding.inflate(layoutInflater)

        val volleyHandler = VolleyHandler(this)
        presenter = SectionPresenter(volleyHandler, this)
        setContentView(binding.root)

        setResult(presenter.findSubCategories(categoryId))
    }
    private fun setUpViewPager(i: Int, positionalInfo: MutableList<String>) {
        adapter = SectionAdapter(this, i, positionalInfo)

        binding.viewPager.adapter = adapter

    }

    @SuppressLint("CutPasteId", "SetTextI18n", "InflateParams")
    override fun setResult(subCategory: MutableList<Array<String>>) {
        val positionalInfo: MutableList<String> = mutableListOf()
        for (i in subCategory) {
            positionalInfo.add(i[2])
        }
        setUpViewPager(subCategory.size, positionalInfo)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position -> }.attach()
        var incrementer = 0
        for (i in subCategory) {
            binding.tabLayout.apply {
                val tab1 = LayoutInflater.from(this@SectionActivity).inflate(R.layout.custom_tab_layout, null)
                tab1.findViewById<TextView>(R.id.customText).also { textView -> textView.text = i[0] }
                getTabAt(incrementer)?.apply { customView = tab1 }
            }
            incrementer++
        }
    }
}