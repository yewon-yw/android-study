package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var list = ArrayList<Item>()
    val itemRVAdapter = ItemRVAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list.apply{
            add(Item("1","#FF0000"))
            add(Item("2","#FF5722"))
            add(Item("3","#FFEB3B"))
            add(Item("4","#4CAF50"))
            add(Item("5","#3349C8"))
            add(Item("6","#7BEFFD"))
            add(Item("7","#673AB7"))
            add(Item("8","#FFFFFFFF"))
            add(Item("9","#FF000000"))
        }
        binding.rv.adapter = itemRVAdapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        itemRVAdapter.setMyItemClickListener(object: ItemRVAdapter.MyItemClickListener {
            override fun onAddItem(item: Item) {
                itemRVAdapter.addItem(item)
            }
            override fun onRemoveItem(position: Int) {
                itemRVAdapter.removeItem(position)
            }
        })
    }
}