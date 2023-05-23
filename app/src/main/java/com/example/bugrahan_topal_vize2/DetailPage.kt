package com.example.bugrahan_topal_vize2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bugrahan_topal_vize2.adapters.ProductAdapter
import com.example.bugrahan_topal_vize2.configs.ApiClient
import com.example.bugrahan_topal_vize2.models.*
import com.example.bugrahan_topal_vize2.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPage : AppCompatActivity() {
    lateinit var dummyService: DummyService
    lateinit var productImage:ImageView
    lateinit var producdTitle:TextView
    lateinit var productDes:TextView
    lateinit var productScore:TextView
    lateinit var productPrice:TextView
    lateinit var addProduct:Button
    var productId:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        producdTitle=findViewById(R.id.productTitle)
        productImage=findViewById(R.id.productImage)
        productDes=findViewById(R.id.productDes)
        productScore=findViewById(R.id.productScore)
        productPrice=findViewById(R.id.productPrice)
        addProduct=findViewById(R.id.addProduct)

        val id=intent.getIntExtra("id",0)

        dummyService= ApiClient.getClient().create(DummyService::class.java)
        dummyService.productDetail(id).enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.d("response",response.body().toString())
                val data=response.body()
                if(data!=null){
                    productId=data.id
                    productDes.text=data.description
                    producdTitle.text=data.title
                    productPrice.text=data.price.toString()+"$"
                    productScore.text="Rating:"+data.rating.toString()
                    Glide.with(this@DetailPage)
                        .load(data.images[0])
                        .centerCrop()
                        .into(productImage)
                }

            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("hata","server Hatası")
            }

        })

        addProduct.setOnClickListener {
            val list= mutableListOf<ProductAddCart>()


            list.add(ProductAddCart(productId,1))
            val card=AddCart(1,list)
            Log.d("card",card.toString())
            dummyService.addCard(card).enqueue(object: Callback<AddCartResult>{
                override fun onResponse(
                    call: Call<AddCartResult>,
                    response: Response<AddCartResult>
                ) {
                    Log.d("response",response.body().toString())
                    if(response.body()!=null){
                        Toast.makeText(applicationContext, "Ürün Sepete Başarıyla Eklendi", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<AddCartResult>, t: Throwable) {
                    Log.e("hata","server Hatası")
                }

            })

        }

    }


}