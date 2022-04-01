package com.example.study0402

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.study0402.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pb1 = binding.pb1
        var pb2 = binding.pb2

        var tv1 = binding.tv1
        var tv2 = binding.tv2

//        스레드와 비교하기 위한 코드로 아래 코드는 정상 작동하지 않음
//        조금씩 늘어나는 것이 아니라 멈춰있다가 한꺼번에 진행됨
//        binding.btn1.setOnClickListener{
//            for(i in 0..99){
//                pb1.progress=pb1.progress+2
//                pb2.progress=pb2.progress+1
//                SystemClock.sleep(100) // 100밀리초 멈춤
//            }
//        }

//      아래의 코드는 스레드를 사용한 것으로 반복문에 맞춰 진행하는 것이 눈으로 보임
        binding.btn1.setOnClickListener {
            object : Thread() {
                override fun run() {
                    for (i in pb1.progress..99) {
//                      pb1.progress=pb1.progress+2 // 안정성을 위해 runOnUiThread내에 작성
//                      아래의 코드가 정상적으로 동작하지 않는 이유는 스레드 내에서 텍스트뷰의 글자를 변경하려고 했기 때문
//                      프로그레스 바는 예외적으로 스레드 내에서 변경할 수 있으나 안정적이진 않음
//                      tv1.text="1번 진행률 : "+pb1.progress.toString()+" %"
//                      안정적으로 위젯을 변경하기 위해 가능하면 UI스레드를 사용함
                        runOnUiThread {
                            pb1.progress = pb1.progress + 2
                            tv1.text = "1번 진행률 : " + pb1.progress.toString() + " %"
                        }
                        SystemClock.sleep(100)
                    }
                }
            }.start()
            object : Thread() {
                override fun run() {
                    for (i in pb2.progress..99) {
//                      pb2.progress=pb2.progress+1
                        runOnUiThread {
                            pb2.progress = pb2.progress + 1
                            tv2.text = "2번 진행률 : " + pb2.progress.toString() + " %"
                        }
                        // 스레드 내 위젯을 수정하기 위해 ActivityrunOnUiThread{} 방법 말고도 View.post{} 방법도 존재
                        // runOnUiThread{} 대신 pb2.post{}로 작성하면 됨
                        // 변경하면 아래의 주석처리 된 부분의 코드와 같음
//                        pb2.post{
//                            pb2.progress=pb2.progress+1
//                            tv2.text="2번 진행률 : "+pb2.progress.toString()+" %"
//                        }
                        SystemClock.sleep(100)
                    }
                }
            }.start()
        }
    }
}