package com.developersmarket.componentscompose.retrofit.network

import com.developersmarket.componentscompose.retrofit.ui.NewProduct
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    companion object {
        const val BASE_URL = "https://dummyjson.com/"

    }


    @GET("products")
    suspend fun getProducts():NewProduct







}