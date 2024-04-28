package com.developersmarket.componentscompose.retrofit.di

import android.content.Context
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.retrofit.network.ApiService
import com.developersmarket.componentscompose.retrofit.network.ImageUploadService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides @Singleton fun providesApiService(): ApiService =
        Retrofit.Builder()
            .run {
                baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }.create(ApiService::class.java)




    //This will differentiate retrofit object
    @Provides @Singleton fun providesImageUploadService(): ImageUploadService =
        Retrofit.Builder()
            .run {
                baseUrl(ImageUploadService.IMAGE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }.create(ImageUploadService::class.java)




    // For storage data in local database
    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManager(appContext)

}