package com.example.weatherapp.ui.userform


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.utils.CommonComposable
import com.intuit.ssp.R

@Composable
fun UserFormScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val viewModel = UserFormViewModel(LocalContext.current)
//    val keyboardManager = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(indication = null, interactionSource = remember {
                MutableInteractionSource()
            }) {
//                keyboardManager?.hide()
            }
    ) {
        Text(
            "Add User", style = TextStyle(
                fontFamily = FontFamily(Font(com.example.weatherapp.R.font.nunito_extra_bold)),
                fontSize = (dimensionResource(
                    id = R.dimen._25ssp
                ).value.sp),
            ),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)))
        Text(
            "Enter the User Details ", style = TextStyle(
                fontFamily = FontFamily(Font(com.example.weatherapp.R.font.nunito_medium)),
                fontSize = (dimensionResource(
                    id = com.intuit.ssp.R.dimen._15ssp
                ).value.sp),
            ),
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonComposable.UserTextField(
            label = "First Name",
            placeholder = "Enter your First Name",
            keyboardType = KeyboardType.Text,
            isEmailText = false,
            errorMessage = "First Name is Required",
            onValueChange = { viewModel.firstName.value = it },
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(8.dp))
        CommonComposable.UserTextField(
            label = "Last Name",
            placeholder = "Enter your Last Name",
            keyboardType = KeyboardType.Text,
            isEmailText = false,
            errorMessage = "Last Name is Required",
            onValueChange = { viewModel.lastName.value = it },
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(8.dp))
        CommonComposable.UserTextField(
            label = "Email ",
            placeholder = "Enter your Email",
            keyboardType = KeyboardType.Text,
            isEmailText = true,
            errorMessage = "Email is Required",
            onValueChange = { viewModel.email.value = it },
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                viewModel.onSaveClick()
                onSave.invoke()
            }) {
                Text("Save")

//                keyboardManager?.hide()
            }
            Button(onClick = {
                onCancel()
                onCancel.invoke()
            }) {
                Text("Cancel")

            }
        }
    }
}


