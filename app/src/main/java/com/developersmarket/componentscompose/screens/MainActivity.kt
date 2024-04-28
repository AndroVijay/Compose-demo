package com.developersmarket.componentscompose.screens

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.bottomnav.DashBoardScreen
import com.developersmarket.componentscompose.bottomnav.MainScreenView
import com.developersmarket.componentscompose.bottomnav.MyBottomNavigation
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.retrofit.ui.MainViewModel
import com.developersmarket.componentscompose.util.PlayStoreVersionChecker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = Color.Blue,
                        elevation = 5.dp
                    ) {
                        Text(modifier = Modifier.padding(16.dp), text = "Products", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.white)
                        )
                        )
                    }
                },

                bottomBar = {  MyBottomNavigation(navController) }
            ) {
                DashBoardScreen(mainViewModel = mainViewModel, dataStoreManager =dataStoreManager, navController )
               // MainScreenView(mainViewModel,dataStoreManager)


            }




            //Dashboard()
           // ComposeNavigationApp(mainViewModel)
            //MainScreen(mainViewModel,dataStoreManager)


           /* val pickMultipleMedia =
                registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
                    // Callback is invoked after the user selects media items or closes the
                    // photo picker.
                    if (uris.isNotEmpty()) {
                        Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                    } else {
                        Log.d("PhotoPicker", "No media selected")
                    }
                }

            // to select a single media item.
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))*/

        }
    }


}

