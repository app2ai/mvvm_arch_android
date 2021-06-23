package com.vishalr.loginapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel(){

    private val _isValidate = MutableLiveData<Boolean>()
    val isValidate: LiveData<Boolean> = _isValidate

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _loginData = MutableLiveData<LoginData>()
    val loginData: LiveData<LoginData> = _loginData

    fun dataValidate(username: String, password: String){
        _isValidate.value = !(username.isEmpty() or password.isEmpty())
    }

    fun goForLogin(username: String, password: String){
        _progress.value = true
        GlobalScope.launch {
            delay(4000)
            withContext(Dispatchers.Main){
                _progress.value = false
                _loginData.value = Repository().getLoginData()
            }
        }
    }
}