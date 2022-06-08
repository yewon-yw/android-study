package com.example.sharedpreferencetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferencetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var list = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        list.apply{
            list.add(Item("타일런트","Zombie",10,100,10,50,false))
            list.add(Item("리오레우스","Dragon",2500,10000,1000,50000,false))
        }

        var adapter = RVAdapter(list)
        binding.rv.adapter=adapter

        setContentView(binding.root)

    }
}