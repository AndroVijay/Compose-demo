package com.developersmarket.componentscompose.composableText

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developersmarket.componentscompose.R
import com.developersmarket.componentscompose.database.DataStoreManager
import com.developersmarket.componentscompose.database.User
import com.developersmarket.componentscompose.screens.Routes
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


@Composable
fun NormalTextSize(normalText: String) {

    Text(
        text = normalText,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        style = TextStyle(
            fontSize = 30.sp, fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Cursive
        ), color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center

    )
}


@Composable
fun HeadingTextSize(normalText: String) {

    Text(
        text = normalText,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 40.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Cursive
        ), color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center

    )

}


@Composable
fun myTextField(value: String, iconId: Painter): String {

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small),
        label = { Text(text = value, style = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp
        )
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            backgroundColor = colorResource(id = R.color.white),
            textColor = colorResource(id = R.color.black)

        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp
        ),
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(painter = iconId, contentDescription = "")
        }
    )
    return textValue.value
}

@Composable
fun passwordTextField(value: String, iconId: Painter): String {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small),
        label = { Text(text = value, style = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp
        )
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            backgroundColor = colorResource(id = R.color.white),
            textColor = colorResource(id = R.color.black)

        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp
        ),
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(painter = iconId, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_pass)
            } else {
                stringResource(id = R.string.show_pass)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {

                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation()


    )

    return password.value.toString()
}


@Composable
fun checkBoxField(value: String, onTextSelected: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val checkedstate = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedstate.value, onCheckedChange = {
            checkedstate.value = it
        })

        clickableText(value, onTextSelected)
    }

}

@Composable
fun clickableText(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing accept our "
    val policyText = "Privacy Policy"
    val andText = " and "
    val termsText = " Term of use"
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.teal_700), fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif
        )) {
            pushStringAnnotation(tag = policyText, annotation = policyText)
            append(policyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.teal_700), fontSize = 18.sp, fontFamily = FontFamily.SansSerif)) {
            pushStringAnnotation(tag = termsText, annotation = termsText)
            append(termsText)
        }

    }
    ClickableText(text = annotatedString, style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,

    ), onClick = { offset ->


        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {


                if ((it.item == termsText) || (it.item == policyText)) {
                    Toast.makeText(context, "clicked ${it.item}", Toast.LENGTH_LONG).show()
                    onTextSelected(it.item)
                }
            }

    })

}

@Composable
fun SignUpButton(
    navController: NavHostController,
    fname: String,
    lname: String,
    email: String,
    pass: String,
    dataStoreManager: DataStoreManager
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                if (fname.equals("") && lname.equals("") && email.equals("")&& pass.equals("")){
                    Toast.makeText(context, "all field required", Toast.LENGTH_LONG).show()
                }else{
                    coroutineScope.launch {
                        dataStoreManager.saveUserSession(
                            User(isLogin = true,fname,lname,email,pass)
                        )
                    }
                    Toast.makeText(context, "Sign up successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(Routes.SignInScreen.route)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = true,
            border = BorderStroke(1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Sign Up", color = Color.White, style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }

}

@Composable
fun LoginButton(
    navController: NavHostController,
    email: String,
    pass: String,
    dataStoreManager: DataStoreManager
) {
    val context = LocalContext.current
    val data: State<User> =   dataStoreManager.getFromDataStore().collectAsState(initial = User())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {

                Toast.makeText(context, "${data.value.emailAddress}+${data.value.password}", Toast.LENGTH_LONG).show()

                if (email== data.value.emailAddress && pass == data.value.password){
                    navController.navigate(Routes.HomeScreen.route)
                }else{
                    Toast.makeText(context, "wrong email or password", Toast.LENGTH_LONG).show()
                }



            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = true,
            border = BorderStroke(1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Sign In", color = Color.White, style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}


@Composable
fun NoAccountField(value: String, onTextSelected: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        NoAccountText(value, onTextSelected)
    }

}


@Composable
fun NoAccountText(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "You don't have an accounts? "
    val sigUpText = " Sign up"
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.blue), fontSize = 18.sp, fontFamily = FontFamily.SansSerif)) {
            pushStringAnnotation(tag = sigUpText, annotation = sigUpText)
            append(sigUpText)
        }

    }
    ClickableText(text = annotatedString, style = TextStyle(

        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    ), onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {

                if ((it.item == sigUpText) || (it.item == sigUpText)) {
                    Toast.makeText(context, "clicked ${it.item}", Toast.LENGTH_LONG).show()
                    onTextSelected(it.item)
                }
            }

    })

}


@Composable
fun YesAccountField(value: String, onTextSelected: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AlreadyAccountText(value, onTextSelected)
    }

}


@Composable
fun AlreadyAccountText(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "You have an accounts? "
    val sigUpText = " Sign in"
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.blue), fontSize = 18.sp, fontFamily = FontFamily.SansSerif)) {
            pushStringAnnotation(tag = sigUpText, annotation = sigUpText)
            append(sigUpText)
        }

    }
    ClickableText(text = annotatedString,style = TextStyle(

        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    ), onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {

                if ((it.item == sigUpText) || (it.item == sigUpText)) {
                    Toast.makeText(context, "clicked ${it.item}", Toast.LENGTH_LONG).show()
                    onTextSelected(it.item)
                }
            }

    })

}

