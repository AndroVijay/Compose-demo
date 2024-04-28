package com.developersmarket.componentscompose.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.database.User
import com.developersmarket.componentscompose.retrofit.ui.MainViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(mainViewModel: MainViewModel, dataStoreManager: DataStoreManager) {

    val navController = rememberNavController()
    val data: State<User> =   dataStoreManager.getFromDataStore().collectAsState(initial = User())

    NavHost(navController = navController, startDestination = Routes.SignInScreen.route) {


        //Sign in screen
        composable(Routes.SignInScreen.route){

            //val isLogin = data.value.isLogin
            //HomeScreen(mainViewModel, navController = navController)
            if (true){
                HomeScreen(mainViewModel, navController = navController)
            }else{
                SignInScreen(navController,dataStoreManager)
            }

        }

        // Sign up screen
        composable(Routes.SignupScreen.route){
            SignUpScreen(navController,dataStoreManager)
        }

        //First Route : Home
        composable(Routes.HomeScreen.route) {

            HomeScreen(mainViewModel, navController = navController)
        }

        //Second Route : ProductDetail
        composable(Routes.ProductDetails.route, arguments = listOf(
            navArgument(PRODUCT_ID) {
            type = NavType.StringType
        },
            navArgument(PRODUCT_TITLE) {
                type = NavType.StringType
            },
            navArgument(PRODUCT_THUMBNAIL) {
                type = NavType.StringType
            },
            navArgument(PRODUCT_DES) {
                type = NavType.StringType
            }
        )


        ) {

            val pro_id = it.arguments?.getString(PRODUCT_ID).toString()
            val pro_title = it.arguments?.getString(PRODUCT_TITLE).toString()
            val pro_image = it.arguments?.getString(PRODUCT_THUMBNAIL).toString()
            val pro_des = it.arguments?.getString(PRODUCT_DES).toString()


            ProductDetails(id = pro_id,  title = pro_title, image = pro_image, des = pro_des,navController)

        }

    }

}