package com.example.ecommerceapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionAdapter(fragmentActivity: FragmentActivity, private val totalCount:Int, private val positionalInfo: MutableList<String>) :FragmentStateAdapter(fragmentActivity)
{
    override fun getItemCount() = totalCount

    override fun createFragment(position:Int):Fragment{
        return SubcategoryFragment(positionalInfo[position])
    }
}