package com.example.bugrahan_topal_vize2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.bugrahan_topal_vize2.adapters.ProductAdapter
import com.example.bugrahan_topal_vize2.configs.ApiClient
import com.example.bugrahan_topal_vize2.models.Cards
import com.example.bugrahan_topal_vize2.models.Product
import com.example.bugrahan_topal_vize2.models.Products
import com.example.bugrahan_topal_vize2.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var dummyService: DummyService
    val products= mutableListOf<Product>()
    lateinit var  customAdapter: ArrayAdapter<Product>
    lateinit var productsListView:ListView
    lateinit var btnSepet:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productsListView=findViewById(R.id.productsListView)
        btnSepet=findViewById(R.id.btnSepet)

        dummyService= ApiClient.getClient().create(DummyService::class.java)

        dummyService.products().enqueue(object: Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                Log.d("response",response.body().toString())
                for (item in response.body()!!.products){
                    products.add(item)
                }
                customAdapter=ProductAdapter(this@MainActivity ,products)
                productsListView.adapter=customAdapter
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Log.e("hata","server Hatası")
            }

        })

        productsListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent=Intent(this,DetailPage::class.java)
            val data =products.get(i).id.toInt()
            intent.putExtra("id",data)
            startActivity(intent)
        }

        btnSepet.setOnClickListener {
            val intent=Intent(this,MyCart::class.java)
            startActivity(intent)
        }

    }
}