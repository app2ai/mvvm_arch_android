package com.vishalr.loginapp

class Repository {
    fun getLoginData() : LoginData{
        return LoginData("TestUser", 30, "India")
    }
}