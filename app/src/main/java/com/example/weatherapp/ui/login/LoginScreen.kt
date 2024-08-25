package com.example.weatherapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.repository.PreferencesManager
import com.example.weatherapp.ui.utils.CommonComposable

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val viewModel = LoginViewModel(PreferencesManager(context))
    val keyboardManager = LocalSoftwareKeyboardController.current
    Box(contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._20sdp))
                .background(color = Color.White)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    keyboardManager?.hide()
                }
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._35sdp)))
            Text(
                "Login", style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nunito_extra_bold)),
                    fontSize = (dimensionResource(
                        id = com.intuit.ssp.R.dimen._25ssp
                    ).value.sp),
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)))
            Text(
                "Welcome to the App ", style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    fontSize = (dimensionResource(
                        id = com.intuit.ssp.R.dimen._15ssp
                    ).value.sp),
                ),
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)))
            CommonComposable.LoginTextField(
                label = "Username",
                placeholder = "Enter your Username",
                keyboardType = KeyboardType.Email,
                isEmailText = true,
                errorMessage = "Please enter a valid username",
                onValueChange = { viewModel.username.value = it },
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)))
            CommonComposable.LoginTextField(
                label = "Password",
                placeholder = "Enter your password",
                keyboardType = KeyboardType.Password,
                isEmailText = false,
                errorMessage = "Password must be at least 8 characters long",
                onValueChange = { viewModel.password.value = it },
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._25sdp)))
            LoginButton {
                viewModel.validateUser()
                keyboardManager?.hide()
                if(viewModel.loginError.value.isEmpty()){
                    onLoginSuccess.invoke()
                }else{
                    Toast.makeText(context,viewModel.loginError.value,Toast.LENGTH_SHORT).show()
                }
            }


        }
    }


}


@Composable
fun LoginButton(
    onClick : ()-> Unit
){
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonColors(
            colorResource(id = R.color.light_blue), // Example color
            colorResource(id = R.color.light_blue),
            colorResource(id = R.color.light_blue),
            colorResource(id = R.color.light_blue)
        )
    ) {
        Text(
            text = "LOGIN",
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = (dimensionResource(id = com.intuit.ssp.R.dimen._20ssp).value.sp), fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.white)
        )
    }
}
