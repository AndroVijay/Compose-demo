package com.developersmarket.componentscompose.screens



const val PRODUCT_ID = "id"
const val PRODUCT_TITLE = "title"
const val PRODUCT_THUMBNAIL = "thumbnail"
const val PRODUCT_DES = "description"
//It contains route names to all three screens
sealed class Routes(val route: String){

    object SignupScreen: Routes("signup")
    object SignInScreen: Routes("signin")
    object HomeScreen:Routes("home")
    object DetailScreen:Routes("")



    object ProductDetails : Routes("productdetails/{$PRODUCT_ID}/{$PRODUCT_TITLE}/{$PRODUCT_THUMBNAIL}/{$PRODUCT_DES}"){

        fun productdetais(
            id : String,
            title:String,
            image: String,
            description:String
        ):String{
            return "productdetails/$id/$title/$image/$description"
        }

    }





}
