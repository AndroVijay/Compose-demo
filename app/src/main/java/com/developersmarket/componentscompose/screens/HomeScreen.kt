package com.developersmarket.componentscompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.retrofit.ui.MainViewModel
import com.developersmarket.componentscompose.retrofit.ui.Product
import com.developersmarket.componentscompose.util.ApiState
import com.developersmarket.componentscompose.util.ComposeProgressbar
import com.skydoves.landscapist.glide.GlideImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(mainViewModel: MainViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {

        apiData(mainViewModel = mainViewModel,navController)
    }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                backgroundColor = Color.Blue,
//                elevation = 5.dp
//            ) {
//                Text(modifier = Modifier.padding(16.dp), text = "Products", style = TextStyle(
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = colorResource(id = R.color.white)
//                )
//                )
//            }
//        },
//    ) {
//
//        apiData(mainViewModel = mainViewModel,navController)
//    }
}

// REST API CALL
@Composable
fun apiData(mainViewModel: MainViewModel, navController: NavHostController) {

    when (val result = mainViewModel.response.value) {

        is ApiState.Loading -> {

            ComposeProgressbar().simpleProgressbar()
        }

        is ApiState.Success -> {

            Recyclerview(result.data.products,navController)
        }

        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }

    }


}


// CREATING VERTICAL COLUMN
@Composable
fun Recyclerview(productlist: List<Product>, navController: NavHostController) {
    LazyColumn {
        items(productlist) { product ->
            EachRow(pro = product,navController)
        }
    }
}



// LIST SINGLE ROW
@Composable
fun EachRow(pro: Product, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .fillMaxWidth()
            .clickable {
                val encodedUrl = URLEncoder.encode(pro.thumbnail, StandardCharsets.UTF_8.toString())
                navController.navigate(Routes.ProductDetails.productdetais(pro.id.toString(),pro.title,encodedUrl,pro.description))
            },

        shape = RoundedCornerShape(CornerSize(10.dp)),
        elevation = 2.dp,
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            pro.thumbnail.let {
                GlideImage(
                    imageModel = it,
                    modifier = Modifier
                        .padding(8.dp)

                        .size(80.dp)
                        .clip(CircleShape)
                        .border(0.5.dp, Color.Gray, CircleShape),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                pro.title.let {
                    Text(text = it, modifier = Modifier.padding(8.dp), style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ))
                }
                pro.description.let {
                    Text(text = it, modifier = Modifier.padding(8.dp), style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                    )
                }
            }

        }
    }
}