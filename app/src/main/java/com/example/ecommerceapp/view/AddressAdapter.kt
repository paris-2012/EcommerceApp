package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.AddressListItemBinding
import com.example.ecommerceapp.databinding.CategoriesListItemBinding
import com.example.ecommerceapp.model.remote.CustomVolleyRequestQueue

class AddressAdapter(private val categories: MutableList<Array<String>>, val userId: String, context: Context) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    internal lateinit var binding: AddressListItemBinding
    val myContext = context
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        binding = AddressListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: Array<String>) {

            binding.txtTitle.text = item[1]
            binding.txtAddress.text = item[2]
            binding.btnSelect.setOnClickListener {
                val intent = Intent(myContext, PaymentActivity::class.java)
                intent.putExtra("place", item[1])
                intent.putExtra("address", item[2])
                intent.putExtra("user_id", userId)
                ContextCompat.startActivity(myContext, intent, null)
            }
        }
    }
}