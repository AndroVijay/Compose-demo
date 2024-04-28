package com.developersmarket.componentscompose.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.util.PlayStoreVersionChecker
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
 fun ProductDetails(
    id: String,
    title: String,
    image: String,
    des: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {

        Column(

            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            GlideImage(
                imageModel = image,
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        Color.LightGray
                    ),
                contentScale = ContentScale.FillWidth,

                )
            Text(
                text = id.toString(), style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = title.toString(), style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = des.toString(), style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            )
            val context = LocalContext.current
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

                    Toast.makeText(context, uri.toString(),Toast.LENGTH_LONG).show()


                }

            Button(onClick = {

                launcher.launch(PickVisualMediaRequest())

                CoroutineScope(Dispatchers.IO).launch {

                    val version_code = PlayStoreVersionChecker.getPlayStoreVersion()
                    Log.d("Update_version", version_code.toString())
                }



            }) {
                Text(text = "Select Photo")

            }


        }
    }

}


//   Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        modifier = Modifier.padding(16.dp),
//                        text = "Product Details",
//                        style = TextStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp,
//                            color = colorResource(id = R.color.white)
//                        )
//                    )
//                },
//
//                backgroundColor = Color.Blue,
//                elevation = 5.dp,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back",
//                            tint = Color.White
//                            )
//                    }
//                }
//            )
//        },
//    ) {
//    Column(
//
//        modifier = Modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//
//
//    ) {
//        GlideImage(
//            imageModel = image,
//            Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//                .padding(4.dp)
//                .clip(RoundedCornerShape(4.dp))
//                .background(
//                    Color.LightGray
//                ),
//            contentScale = ContentScale.FillWidth,
//
//            )
//        Text(
//            text = id.toString(), style = TextStyle(
//                fontSize = 18.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold
//            )
//        )
//        Text(
//            text = title.toString(), style = TextStyle(
//                fontSize = 18.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold
//            )
//        )
//
//        Text(
//            text = des.toString(), style = TextStyle(
//                fontSize = 15.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Normal
//            )
//        )
//        val launcher =
//            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri -> }
//        val context = LocalContext.current
//        Button(onClick = {
//
//            launcher.launch(PickVisualMediaRequest())
//        }) {
//            Text(text = "Select Photo")
//
//        }
//
//
//    }
//
//}
//
//}