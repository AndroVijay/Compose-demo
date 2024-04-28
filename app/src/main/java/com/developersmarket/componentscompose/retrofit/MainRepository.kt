package com.developersmarket.componentscompose.retrofit

import com.developersmarket.componentscompose.retrofit.network.ApiService
import com.developersmarket.componentscompose.retrofit.ui.NewProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiService: ApiService){

    fun getProduct():Flow<NewProduct> = flow {

        emit(apiService.getProducts())
    }.flowOn(Dispatchers.IO)



}