package com.example.kakaologintest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "1bf25e534e2d1d80910c9892e368a8df")
    }
}