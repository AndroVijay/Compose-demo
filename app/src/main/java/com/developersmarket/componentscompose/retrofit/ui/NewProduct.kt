package com.developersmarket.componentscompose.retrofit.ui

data class NewProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)