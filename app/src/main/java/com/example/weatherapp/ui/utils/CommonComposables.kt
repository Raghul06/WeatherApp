package com.example.weatherapp.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.login.LoginViewModel
import com.example.weatherapp.ui.userform.UserFormViewModel

class CommonComposable {
    companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun LoginTextField(
            label: String,
            placeholder: String,
            keyboardType: KeyboardType,
            isEmailText: Boolean,
            errorMessage: String,
            onValueChange: (String) -> Unit,
            viewModel: LoginViewModel,
        ) {
            var text by remember { mutableStateOf("") }
            var error by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        error = if(isEmailText) !viewModel.isValidEmail(it) else viewModel.isValidPassword(it).isNotEmpty()
                        onValueChange(it)
                    },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    isError = error,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    )
                )

                if (error) {
                    Text(
                        text = if(isEmailText) errorMessage else viewModel.isValidPassword(text).first(),
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun UserTextField(
            label: String,
            placeholder: String,
            keyboardType: KeyboardType,
            isEmailText: Boolean,
            errorMessage: String,
            onValueChange: (String) -> Unit,
            viewModel: UserFormViewModel,
        ) {
            var text by remember { mutableStateOf("") }
            var error by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        onValueChange(it)
                        error = if(isEmailText) !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() else it.isEmpty()
                    },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    isError = error,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    )
                )

                if (error) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}