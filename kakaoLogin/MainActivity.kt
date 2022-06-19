package com.example.kakaologintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.kakaologintest.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            login()
        }
        binding.logout.setOnClickListener {
            logout()
        }

        updateKakaoLoginUI()

    }

    fun updateKakaoLoginUI(){
        UserApiClient.instance.me { user, error ->
//            if(error != null){
//                Log.e(TAG,"사용자 정보 요청 실패",error)
//            }
            if (user != null) {
                binding.logout.visibility = View.VISIBLE
                binding.login.visibility = View.GONE
                binding.nickname.text=user.kakaoAccount?.profile?.nickname
                //Log.i(TAG,"${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                Glide.with(binding.profile)
                    .load(user.kakaoAccount?.profile?.thumbnailImageUrl)
                    .circleCrop()
                    .into(binding.profile)
            }
            else{
                binding.login.visibility = View.VISIBLE
                binding.logout.visibility = View.GONE
                binding.nickname.text = null
                binding.profile.setImageBitmap(null)
            }
        }
    }


    fun login(){
        val callback: (OAuthToken?, Throwable?) -> Unit = {token, error ->
            if(error !=null){
                Log.e(TAG,"카카오톡으로 로그인 실패",error)
            }
            else if(token !=null){
                Log.i(TAG,"카카오계정으로 로그인 성공 ${token.accessToken}")
                updateKakaoLoginUI()
            }
        }

        if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } // 사용자가 취소하거나 에러가 난 경우 카카오톡이 아닌 카카오 계정으로 로그인실행
                else if(token != null){
                    Log.i(TAG,"카카오톡으로 로그인 성공 ${token.accessToken}")
                    updateKakaoLoginUI()
                }
            }
        }
        else{
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    fun logout(){
        UserApiClient.instance.logout { error ->
            if(error!=null){
                Log.e(TAG,"로그아웃 실패. SDK에서 토큰 삭제됨",error)
                binding.nickname.text="로그아웃 실패" // 임의로 만듦
            }
            else{
                Log.i(TAG,"로그아웃 성공. SDK에서 토큰 삭제됨")
                updateKakaoLoginUI()
            }
        }
    }
}