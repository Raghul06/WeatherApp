package com.example.weatherapp.ui.weather

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.repository.PreferencesManager
import com.example.weatherapp.ui.login.LoginViewModel

@Composable
fun WeatherScreen(onBackClick : ()->Unit,onLogoutClick : ()->Unit,) {
    val viewModel = WeatherViewModel()
    val prefManager = PreferencesManager(LocalContext.current)
    val weatherState = viewModel.weatherState.observeAsState()
    LaunchedEffect(Unit) {
        Log.d("##DEV","Pref ->${prefManager.isLoggedIn()}")
        viewModel.fetchWeather(
            lat = 12.9082847623315,
            lon = 77.65197822993314,
            units = "imperial",
            appid = "b143bb707b2ee117e62649b358207d3e"
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._70sdp))
                .background(color = colorResource(id = R.color.light_blue))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        dimensionResource(id = com.intuit.sdp.R.dimen._13sdp)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon_white),
                    contentDescription = "Weather",
                    modifier = Modifier.clickable { onBackClick.invoke() }
                )

                Text(
                    text = "User List", style = TextStyle(
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._20ssp).value.sp,
                        fontFamily = FontFamily(
                            Font(R.font.nunito_semibold)
                        )
                    )
                )
                Text(
                    text = "Logout", style = TextStyle(
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp,
                        fontFamily = FontFamily(
                            Font(R.font.nunito_semibold)
                        ),
                        color = Color.White
                    ), modifier = Modifier.clickable {
                        prefManager.setLoggedIn(false)
                        onLogoutClick.invoke()
                    }
                )
            }
        }

        weatherState.let { weather ->
            Text(
                text = "Temperature: ${weather.value?.current?.temp?:"--"} °F",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Humidity: ${weather.value?.current?.humidity?:"--"}%",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Wind Speed: ${weather.value?.current?.windSpeed?:"--"} mph",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Condition: ${weather.value?.current?.weather?.get(0)?.main?:"--"}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}
