package com.example.jsouptest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jsouptest.databinding.ShoppingProductBinding

class ProductRVAdapter(private var productList:ArrayList<Product>,val context:Context):RecyclerView.Adapter<ProductRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductRVAdapter.ViewHolder {
        val binding: ShoppingProductBinding = ShoppingProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int =productList.size

    inner class ViewHolder(val binding: ShoppingProductBinding,val context:Context): RecyclerView.ViewHolder(binding.root){
        fun bind(product:Product){
            Glide.with(context).load(product.productSrc).into(binding.itemIv)
            binding.itemTv.text=product.productName
        }
    }
}