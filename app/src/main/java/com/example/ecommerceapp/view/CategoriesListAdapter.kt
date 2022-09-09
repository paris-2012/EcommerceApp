package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CategoriesListItemBinding

class CategoriesListAdapter(private val categories: ArrayList<String>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    internal lateinit var binding: CategoriesListItemBinding
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListAdapter.ViewHolder {
        binding = CategoriesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: String) {
            binding.apply {
                txtTitle.text = item
            }
        }
    }
}