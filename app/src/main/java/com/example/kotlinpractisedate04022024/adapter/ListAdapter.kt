package com.example.kotlinpractisedate04022024.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpractisedate04022024.databinding.ListDataBinding
import com.example.kotlinpractisedate04022024.di.mvvm.model.Products

class ListAdapter(private val context: Context, private val list: List<Products>) :
    RecyclerView.Adapter<ListAdapter.Holder>() {

    class Holder(binding: ListDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val thumbnail: AppCompatImageView = binding.thumbnail
        val description: AppCompatTextView = binding.description
        val brand: AppCompatTextView = binding.brand
        val price: AppCompatTextView = binding.price
        val rating: AppCompatTextView = binding.rating
        val stock: AppCompatTextView = binding.stock
        val discount: AppCompatTextView = binding.discount


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListDataBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listData = list[position]
        holder.title.text = listData.title.toString()
        holder.description.text = listData.description.toString()
        holder.brand.text = "Brand- ${listData.brand.toString()}"
        holder.price.text = "Price- $ ${listData.price.toString()}"
        holder.rating.text = "Rating- * ${listData.rating.toString()}"
        holder.stock.text ="Stock-  ${ listData.stock.toString()}"
        holder.discount.text = "Discount- "+listData.discountPercentage.toString() + " %"

       // Log.e("Ahmed", "onBindViewHolder: ${listData.thumbnail} ")
        Glide.with(context)
            .load(listData.thumbnail)
            .into(holder.thumbnail)
    }
}