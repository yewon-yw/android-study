package com.example.recyclerviewtest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.databinding.ItemBinding

class ItemRVAdapter(private var list:ArrayList<Item>): RecyclerView.Adapter<ItemRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRVAdapter.ViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRVAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.plusTv.setOnClickListener{
            mItemClickListener.onAddItem(list[position])
        }
        holder.binding.minusTv.setOnClickListener {
            mItemClickListener.onRemoveItem(position)
        }
    }

    override fun getItemCount(): Int = list.size

    // 매개변수로 아이템 뷰 객체를 받음
    // 아이템 뷰 객체 재활용 위해 담아두는 그릇
    inner class ViewHolder(val binding:ItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.tvNumber.text = item.number
            binding.tvColor.setBackgroundColor(Color.parseColor(item.color))
        }
    }

    fun addItem(item:Item){
        list.add(item)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }
    interface MyItemClickListener{
        fun onAddItem(item: Item)
        fun onRemoveItem(position: Int)
    }
    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }
}