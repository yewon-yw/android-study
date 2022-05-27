package com.example.viewpagertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.viewpagertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메인 액티비티의 frameLayout 부분을 HomeFragment로 변경
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm,HomeFragment())
            .commitAllowingStateLoss()
    }
}