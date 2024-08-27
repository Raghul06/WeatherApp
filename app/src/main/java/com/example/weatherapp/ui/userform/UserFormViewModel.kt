package com.example.weatherapp.ui.userform

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.UserDao
import com.example.weatherapp.data.local.UserEntity
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.weatherapp.data.local.UserDatabase

class UserFormViewModel(context : Context) : ViewModel() {
    private val userDao = UserDatabase.getDatabase(context).userDao()
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")

    private fun validateInput(): Boolean {
        var isValid = true

        if (firstName.value.isEmpty()) {
            isValid = false
        }

        if (lastName.value.isEmpty()) {
            isValid = false
        }

        if (email.value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isValid = false
        }

        return isValid
    }

    fun onSaveClick() {
        if (validateInput()) {
            viewModelScope.launch {
                userDao.insertUser(
                    UserEntity(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        email = email.value
                    )
                )
                // Handle navigation or other actions after saving
            }

        }
    }
}
