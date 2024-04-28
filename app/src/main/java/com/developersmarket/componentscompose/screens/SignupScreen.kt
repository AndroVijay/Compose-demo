package com.developersmarket.componentscompose.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.composableText.HeadingTextSize
import com.developersmarket.componentscompose.composableText.NormalTextSize
import com.developersmarket.componentscompose.composableText.SignUpButton
import com.developersmarket.componentscompose.composableText.YesAccountField
import com.developersmarket.componentscompose.composableText.checkBoxField
import com.developersmarket.componentscompose.composableText.myTextField
import com.developersmarket.componentscompose.composableText.passwordTextField
import com.developersmarket.componentscompose.database.DataStoreManager


@Composable
fun SignUpScreen(navController: NavHostController, dataStoreManager: DataStoreManager) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextSize(stringResource(id = R.string.hello))

            Spacer(modifier = Modifier.height(20.dp))

            HeadingTextSize(stringResource(id = R.string.create_accounts))

            Spacer(modifier = Modifier.height(25.dp))

            val  fname = myTextField(stringResource(id = R.string.firstname), painterResource(id = R.drawable.ic_person_pin_24))
            Spacer(modifier = Modifier.height(15.dp))
            val  lname =myTextField(stringResource(id = R.string.lastname), painterResource(id = R.drawable.ic_person_pin_24))
            Spacer(modifier = Modifier.height(15.dp))
            val  email = myTextField(stringResource(id = R.string.email), painterResource(id = R.drawable.ic_email_24))
            Spacer(modifier = Modifier.height(15.dp))
            val pass= passwordTextField(stringResource(id = R.string.password), painterResource(id = R.drawable.ic_lock_24))

            Spacer(modifier = Modifier.height(20.dp))


            checkBoxField(stringResource(id = R.string.terms), onTextSelected = {

            })

            Spacer(modifier = Modifier.height(15.dp))

            SignUpButton(navController = navController, fname =fname , lname =lname , email = email, pass = pass,dataStoreManager)


            Spacer(modifier = Modifier.height(15.dp))

            YesAccountField(stringResource(id = R.string.yes_account), onTextSelected = {

                navController.navigate(Routes.SignInScreen.route)
            })

        }

    }

}