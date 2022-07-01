package com.example.coroutinestest

import android.util.Log

class myThread :Thread() {
    override fun run() {
        super.run()
        Log.d("TAG","${Thread.currentThread().name} ****")
    }
}