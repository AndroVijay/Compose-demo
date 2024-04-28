package com.developersmarket.componentscompose.retrofit.network

import okhttp3.MultipartBody
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUploadService {


    companion object{

        const val IMAGE_BASE_URL = ""
    }

    @POST("upload")
    suspend fun getUploadSingleImage(@Part file: MultipartBody.Part)
}