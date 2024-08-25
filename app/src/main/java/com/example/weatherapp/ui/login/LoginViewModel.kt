package com.example.weatherapp.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.PreferencesManager
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel(private val prefManager: PreferencesManager) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var loginError = mutableStateOf("")


    fun validateUser(){
        viewModelScope.launch {
            val passwordErrorList = isValidPassword(password.value)
            if(!isValidEmail(username.value)){
                loginError.value = "Enter a valid Username"
            } else if(passwordErrorList.isNotEmpty()){
                loginError.value = passwordErrorList.first()
            }else if(username.value != "testapp@google.com" || password.value != "Test@123456"){
                loginError.value = "Invalid credentials"
            }else{
                prefManager.setLoggedIn(true)
                loginError.value = ""
            }
        }
    }


    /**
     * Method to check for a valid Email
     * @return Boolean - whether it is valid email or not
     */
    fun isValidEmail(email: String): Boolean {
        // Use a regular expression to validate the email pattern
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Method to check for a valid Password
     * @return List<String> - If Valid Password means list will be empty
     * @return List<String> - If Invalid Password means list will contains error messages
     */
    fun isValidPassword(password: String): List<String> {
        val errors = mutableListOf<String>()

        // Check for lowercase
        if (!Pattern.matches(".*[a-z].*", password)) {
            errors.add("Password must contain at least one lowercase letter.")
        }

        // Check for uppercase
        if (!Pattern.matches(".*[A-Z].*", password)) {
            errors.add("Password must contain at least one uppercase letter.")
        }

        // Check for digits
        if (!Pattern.matches(".*\\d.*", password)) {
            errors.add("Password must contain at least one number.")
        }

        // Check for special characters (Corrected)
        if (!Pattern.matches(".*[!@#$%^&*()_+].*", password)) {
            errors.add("Password must contain at least one special character.")
        }

        // Check length
        if (password.length < 8) {
            errors.add("Password must be at least 8 characters long.")
        }

        return errors
    }
}