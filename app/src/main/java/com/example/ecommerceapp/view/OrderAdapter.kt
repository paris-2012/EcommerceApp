package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.OrderItemLayoutBinding
import com.example.ecommerceapp.model.local.AppDatabase
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.local.CartItem

class OrderAdapter(private val notes: ArrayList<CartItem>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    internal lateinit var binding: OrderItemLayoutBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        binding = OrderItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        database = AppDatabase.getInstance(parent.context)
        cartDao = database.cartDao()
        return ViewHolder(binding.root)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: CartItem) {
            binding.apply {
                txtTitle.text = item.title
                txtPrice.text = "$${item.price}"
                txtQuantity.text = item.quantity.toString()
            }
        }
    }
}