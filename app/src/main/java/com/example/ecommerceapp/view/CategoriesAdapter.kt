package com.example.ecommerceapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.CategoriesListItemBinding
import com.example.ecommerceapp.model.remote.CustomVolleyRequestQueue


class CategoriesAdapter(private val categories: MutableList<Array<String>>, context: Context) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    internal lateinit var binding: CategoriesListItemBinding
    val myContext = context
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        binding = CategoriesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: Array<String>) {
            val endpoint = item[1]
            val url = "https://psmobitech.com/myshop/images/$endpoint"
            val imageLoader = CustomVolleyRequestQueue.getInstance(myContext).imageLoader
            imageLoader.get(url, ImageLoader.getImageListener(binding.networkImageView, R.drawable.ic_baseline_image_24, android.R.drawable.ic_dialog_alert))
            binding.networkImageView.setImageUrl(url, imageLoader)
            binding.txtTitle.text = item[0]
            binding.myCard.setOnClickListener {

                val intent = Intent(myContext, SectionActivity::class.java)

                intent.putExtra("category", item[2])

                startActivity(myContext, intent, null)
            }
        }
    }
}