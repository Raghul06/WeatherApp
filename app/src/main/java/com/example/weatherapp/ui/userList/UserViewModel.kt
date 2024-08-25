package com.example.weatherapp.ui.userList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.UserDatabase
import com.example.weatherapp.data.local.UserEntity
import com.example.weatherapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(context : Context) : ViewModel() {
    private var _userList = MutableLiveData<List<UserEntity>>(listOf())
    val userList: LiveData<List<UserEntity>> get() = _userList
    val database = UserDatabase.getDatabase(context).userDao()

    init {
        getUserList(context)
    }
    private fun getUserList(context: Context){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val users = database.getAllUsers()
                withContext(Dispatchers.Main){
                    _userList.value = users
                }
            }

        }

    }
}