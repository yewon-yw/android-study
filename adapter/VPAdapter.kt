package com.example.viewpagertest

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val pages = 2
    override fun getItemCount() = pages

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> FirstFragment()
            else -> SecondFragment()
        }
    }
}