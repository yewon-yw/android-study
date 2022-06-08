package com.example.sharedpreferencetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferencetest.databinding.ItemBinding

class RVAdapter(private var list:ArrayList<Item>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.nameTv.text = item.name
            binding.raceTv.text = item.race
            binding.levelTv.text = item.level.toString()
            binding.hpMpExpTv.text = "HP: " + item.hp.toString() + " / MP: " + item.mp.toString() + " / EXP: " + item.exp.toString()
            binding.encountedTv.text = item.encounted.toString()
        }
    }
}