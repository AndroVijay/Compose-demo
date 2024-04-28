package com.developersmarket.componentscompose.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.composableText.HeadingTextSize
import com.developersmarket.componentscompose.composableText.LoginButton
import com.developersmarket.componentscompose.composableText.NoAccountField
import com.developersmarket.componentscompose.composableText.NormalTextSize
import com.developersmarket.componentscompose.composableText.checkBoxField
import com.developersmarket.componentscompose.composableText.myTextField
import com.developersmarket.componentscompose.composableText.passwordTextField
import com.developersmarket.componentscompose.database.DataStoreManager


@Composable
fun SignInScreen(navController: NavHostController, dataStoreManager: DataStoreManager) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextSize(stringResource(id = R.string.hello))

            Spacer(modifier = Modifier.height(20.dp))

            HeadingTextSize(stringResource(id = R.string.login_accounts))

            Spacer(modifier = Modifier.height(25.dp))

            val  email = myTextField(stringResource(id = R.string.email), painterResource(id = R.drawable.ic_email_24))

            Spacer(modifier = Modifier.height(15.dp))

            val pass= passwordTextField(stringResource(id = R.string.password), painterResource(id = R.drawable.ic_lock_24))

            val context = LocalContext.current

            Spacer(modifier = Modifier.height(20.dp))

            checkBoxField(stringResource(id = R.string.terms), onTextSelected = {

                Toast.makeText(context,it,Toast.LENGTH_LONG).show()

            })



            Spacer(modifier = Modifier.height(15.dp))

            LoginButton(navController,email,pass, dataStoreManager)

            Spacer(modifier = Modifier.height(15.dp))

            NoAccountField(stringResource(id = R.string.no_account_text), onTextSelected = {
                navController.navigate(Routes.SignupScreen.route)
            })



        }

    }
}

