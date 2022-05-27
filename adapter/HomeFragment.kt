package com.example.viewpagertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewpagertest.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val info = arrayListOf("tab1","tab2")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val vpadapter = VPAdapter(this)
        binding.viewPager.adapter = vpadapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){
            tab,position ->
            tab.text = info[position]
        }.attach()

        return binding.root
    }
}