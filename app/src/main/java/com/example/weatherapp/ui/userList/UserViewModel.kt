package com.example.weatherapp.ui.userList

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.UserDatabase
import com.example.weatherapp.data.local.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(context : Context) : ViewModel() {
    private var _userList = MutableLiveData<ArrayList<UserEntity>>(arrayListOf())
    val userList: LiveData<ArrayList<UserEntity>> get() = _userList
    private val database = UserDatabase.getDatabase(context).userDao()

    init {
        getUserList()
    }
    private fun getUserList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val users = database.getAllUsers()
                withContext(Dispatchers.Main){
                    _userList.value = ArrayList(users)
                }
            }
        }
    }

    fun removeUserList(item: UserEntity) {
        _userList.value?.remove(item)
        getUserList()
    }
}