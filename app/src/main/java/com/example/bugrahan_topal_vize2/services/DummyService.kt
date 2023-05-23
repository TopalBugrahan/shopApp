package com.example.bugrahan_topal_vize2.services

import com.example.bugrahan_topal_vize2.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface DummyService {

    @GET("/products")
    fun products(): Call<Products>

    @GET("/products/{id}")
    fun productDetail(@Path("id") productId: Int):Call<Product>

    @POST("carts/add")
    fun addCard(@Body card: AddCart):Call<AddCartResult>

    @GET("/carts/{id}")
    fun getCard(@Path("id") cardId:Int): Call<Cards>
}