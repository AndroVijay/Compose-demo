package com.developersmarket.componentscompose.bottomnav

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.retrofit.ui.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(mainViewModel: MainViewModel, dataStoreManager: DataStoreManager){
    val navController = rememberNavController()

    Scaffold(
        bottomBar =    { MyBottomNavigation(navController) }
    ) {

        DashBoardScreen(mainViewModel = mainViewModel, dataStoreManager =dataStoreManager, navController )
    }
}