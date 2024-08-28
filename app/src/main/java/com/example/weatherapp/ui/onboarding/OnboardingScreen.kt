package com.example.weatherapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun OnboardingScreen(
    onLoginClick:()->Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .paint(painter = painterResource(id = R.drawable.onboarding_bg))) {
                Button(
                    onClick = {
                        onLoginClick.invoke()
                              },
                    Modifier
                        .fillMaxWidth(0.9f)
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._20sdp))
                        .align(Alignment.BottomCenter),
                    colors = ButtonColors(colorResource(id = R.color.light_blue),colorResource(id = R.color.light_blue),colorResource(id = R.color.light_blue),colorResource(id = R.color.light_blue))
                ) {
                    Text(text = "LOGIN", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold), color = colorResource(id = R.color.white))
                }
            }
}