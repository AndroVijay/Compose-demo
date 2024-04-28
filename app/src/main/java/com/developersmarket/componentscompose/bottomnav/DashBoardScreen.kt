package com.developersmarket.componentscompose.bottomnav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.database.User
import com.developersmarket.componentscompose.retrofit.ui.MainViewModel
import com.developersmarket.componentscompose.screens.HomeScreen
import com.developersmarket.componentscompose.screens.MainScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DashBoardScreen(
    mainViewModel: MainViewModel,
    dataStoreManager: DataStoreManager,
    navController: NavHostController
){

    val data: State<User> =   dataStoreManager.getFromDataStore().collectAsState(initial = User())

    NavHost(navController = navController, startDestination = BottomNavItem.Home1.screen_route){

        composable(BottomNavItem.Home1.screen_route) {

            //HomeScreen(mainViewModel, navController = navController)
            MainScreen(mainViewModel, dataStoreManager)

        }
        composable(BottomNavItem.MyChat.screen_route) {

            ChatScreens()

        }

        composable(BottomNavItem.Settings.screen_route) {
           SettingScreen()
        }

        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen()
        }


    }
    
}