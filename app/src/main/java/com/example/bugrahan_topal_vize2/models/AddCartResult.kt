package com.example.bugrahan_topal_vize2.models

data class AddCartResult (
    val id: Long,
    val products: List<ProductResult>,
    val total: Long,
    val discountedTotal: Long,
    val userID: Long,
    val totalProducts: Long,
    val totalQuantity: Long
)

data class ProductResult (
    val id: Long,
    val title: String,
    val price: Long,
    val quantity: Long,
    val total: Long,
    val discountPercentage: Double,
    val discountedPrice: Long
)