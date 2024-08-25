package com.example.weatherapp.data.repository

class UserRepository(private val preferencesManager: PreferencesManager) {

    fun login(username: String, password: String): Boolean {
        // Perform login logic (e.g., validate credentials)
        if (username == "testapp@google.com" && password == "Test@123456") {
            preferencesManager.setLoggedIn(true)
            return true
        }
        return false
    }

    fun logout() {
        preferencesManager.clearLoginState()
    }

    fun isUserLoggedIn(): Boolean {
        return preferencesManager.isLoggedIn()
    }
}