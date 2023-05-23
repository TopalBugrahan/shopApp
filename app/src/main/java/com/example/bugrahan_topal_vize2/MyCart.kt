package com.example.bugrahan_topal_vize2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.bugrahan_topal_vize2.adapters.CartAdapter
import com.example.bugrahan_topal_vize2.adapters.ProductAdapter
import com.example.bugrahan_topal_vize2.configs.ApiClient
import com.example.bugrahan_topal_vize2.models.Cards
import com.example.bugrahan_topal_vize2.models.Product
import com.example.bugrahan_topal_vize2.models.ProductForCard
import com.example.bugrahan_topal_vize2.models.Products
import com.example.bugrahan_topal_vize2.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCart : AppCompatActivity() {
    lateinit var dummyService: DummyService
    lateinit var cardListView:ListView
    lateinit var  customAdapter: ArrayAdapter<ProductForCard>
    val cart= mutableListOf<ProductForCard>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        cardListView=findViewById(R.id.cartListView)

        dummyService= ApiClient.getClient().create(DummyService::class.java)
        dummyService.getCard(1).enqueue(object: Callback<Cards>{
            override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                Log.d("response",response.body().toString())
                val data = response.body()
                if(data!=null){
                    for (item in data.products){
                        cart.add(item)
                    }
                    customAdapter=CartAdapter(this@MyCart ,cart)
                    cardListView.adapter=customAdapter
                }
            }

            override fun onFailure(call: Call<Cards>, t: Throwable) {
                Log.e("hata","server Hatası")
            }

        })



    }
}