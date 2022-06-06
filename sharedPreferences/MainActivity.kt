package com.example.sharedpreferencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.sharedpreferencetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            savePref()
        }
        binding.loadBtn.setOnClickListener {
            loadPref()
        }
    }
    private fun savePref(){
        val sharedPreferences = getSharedPreferences(KEY_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt(KEY_GRAPHIC, binding.graphicQualityRg.checkedRadioButtonId)
        editor.putInt(KEY_MUSIC,binding.musicVolumeSb.progress)
        editor.putInt(KEY_SFX,binding.sfxVolumeSb.progress)
        editor.putBoolean(KEY_VSYNC,binding.vsyncSwitch.isChecked)

        editor.apply()
        Toast.makeText(this,"Game settings has saved",Toast.LENGTH_SHORT).show()

    }

    private fun loadPref(){
        val sharedPreferences = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)
        if(sharedPreferences.contains(KEY_GRAPHIC)){
            val graphicValue = sharedPreferences.getInt(KEY_GRAPHIC,0)
            val musicValue = sharedPreferences.getInt(KEY_MUSIC,50)
            val sfxValue = sharedPreferences.getInt(KEY_SFX,50)
            val vsyncValue = sharedPreferences.getBoolean(KEY_VSYNC,true)

            binding.graphicQualityRg.check(graphicValue)
            binding.musicVolumeSb.progress = musicValue
            binding.sfxVolumeSb.progress = sfxValue
            binding.vsyncSwitch.isChecked = vsyncValue

            Toast.makeText(this,"Game setting has loaded",Toast.LENGTH_SHORT).show()
        }
    }

    // SharedPreference는 key = value값으로 저장하기 때문에 savePref와 loadPref에서 동시에 사용하는 key값을 companion object로 미리 설정
    companion object{
        private const val KEY_PREFS = "game_settings"
        private const val KEY_GRAPHIC = "graphic_quality"
        private const val KEY_MUSIC = "music_volume"
        private const val KEY_SFX = "sfx_volume"
        private const val KEY_VSYNC = "vertical"
    }
}