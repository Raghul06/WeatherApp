package com.example.weatherapp.ui.userList

import android.widget.ListView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun UserListScreen(onAddUserClick : ()->Unit) {
    val viewModel = UserViewModel(LocalContext.current)
    val userList by viewModel.userList.observeAsState(emptyList())
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
                    contentDescription = "Back Icon"
                )

                Text(
                    text = "User List", style = TextStyle(
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._20ssp).value.sp,
                        fontFamily = FontFamily(
                            Font(R.font.nunito_semibold)
                        )
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.add_icon_white),
                    contentDescription = "Back Icon",
                    modifier = Modifier.clickable {
                        onAddUserClick.invoke()
                    }
                )

            }
        }

        LazyColumn(Modifier.align(Alignment.CenterHorizontally)) {
            items(userList.size) { index ->
                Box(
                    Modifier
                        .fillMaxWidth(0.9f)
                        .height(dimensionResource(id = com.intuit.sdp.R.dimen._55sdp))
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp))
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(
                                dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)
                            )
                        )
                        .border(
                            border = BorderStroke(
                                1.dp,
                                Color.Cyan
                            ), // Adjust the width and color as needed
                            shape = RoundedCornerShape(
                                dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)
                            )
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)),
                        text = "${userList[index].firstName} ${userList[index].lastName}", style = TextStyle(
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._15ssp).value.sp,
                            fontFamily = FontFamily(
                                Font(R.font.nunito_semibold)
                            )
                        )
                    )
                }
            }
        }
    }
}

