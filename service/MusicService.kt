package com.example.project14_1

import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MusicService:android.app.Service() {
    lateinit var mp:MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        android.util.Log.i("서비스 테스트","onStartCommand()")
        Toast.makeText(this,"onStartCommand()",Toast.LENGTH_SHORT).show()
        mp=MediaPlayer.create(this@MusicService,R.raw.song)
        mp.isLooping=true
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        android.util.Log.i("서비스 테스트","onCreate()")
        Toast.makeText(this,"onCreate()",Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onDestroy() {
        android.util.Log.i("서비스 테스트","onDestroy()")
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show()
        mp.stop()
        super.onDestroy()
    }
}