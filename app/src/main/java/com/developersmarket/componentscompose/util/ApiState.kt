package com.developersmarket.componentscompose.util

import com.developersmarket.componentscompose.retrofit.ui.NewProduct

sealed class ApiState {
    class Success(val data: NewProduct) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading:ApiState()
    object Empty: ApiState()
}


sealed class Resource<T>(val data :T? = null,val message:String? = null){

    class Success<T>(data: T):Resource<T>(data)
    class Error<T>(message: String?, data: T? = null):Resource<T>(data, message)
    class Loading<T>:Resource<T>()


}
