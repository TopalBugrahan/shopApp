package com.example.bugrahan_topal_vize2.models

data class AddCart (
    val userId: Long,
    val products: List<ProductAddCart>
)

data class ProductAddCart (
    val id: Long,
    val quantity: Long
)
