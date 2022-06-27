package com.example.jsouptest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsouptest.databinding.ActivityShopBinding
import org.jsoup.Jsoup

class ShopActivity:AppCompatActivity() {
    lateinit var binding: ActivityShopBinding
    var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        onClickEvent()
        crawling()
    }

    fun onClickEvent(){
        binding.gotoMainActivityBtn.setOnClickListener {
            finish()
        }
    }

    fun crawling(){
        Thread(Runnable{
            val url = "https://ba-on.com/product/list.html?cate_no=34"
            val doc = Jsoup.connect(url).get()
            val imgList = doc.select("div.thumbnail")
            val titleList = doc.select("div.description")

            for(i in imgList.indices){
                val item_img = "https:"+imgList[i].select("a img").attr("src")
                val item_title = titleList[i].select("div.description").select("a ul li span").text()
                //Log.d("MyActivity",item_img.toString())
                //Log.d("MyActivity",item_title)
                productList.add(Product(item_img,item_title))
            }

            runOnUiThread{
                val productRvAdapter=ProductRVAdapter(productList,this)
                binding.productListRv.adapter=productRvAdapter
                binding.productListRv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            }
        }).start()
    }
}