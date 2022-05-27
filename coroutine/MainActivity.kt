package com.example.coroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutinestest.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        main()
    }
    fun main() {
        CoroutineScope(Dispatchers.IO).launch {
            // 병렬로 2개 함수 실행
            async { nonSuspendTask1() }
            async { nonSuspendTask2() }
        }
    }

    fun nonSuspendTask1() {
        Thread.sleep(3000)
        Log.d("TAG", "[nonSuspendTask1] After 3s in (${Thread.currentThread().name})")
        Thread.sleep(3000)
        Log.d("TAG", "[nonSuspendTask1] After 6s in (${Thread.currentThread().name})")

        Log.d("TAG", "[nonSuspendTask1] END in (${Thread.currentThread().name}) *****")
    }
    fun nonSuspendTask2() {
        Thread.sleep(1000)
        Log.d("TAG", "[nonSuspendTask2] After 1s in (${Thread.currentThread().name})")
        Thread.sleep(3000)
        Log.d("TAG", "[nonSuspendTask2] After 4s in (${Thread.currentThread().name})")

        Log.d("TAG", "[nonSuspendTask2] END in (${Thread.currentThread().name})*****")
    }
}