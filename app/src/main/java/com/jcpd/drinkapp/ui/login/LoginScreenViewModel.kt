package com.jcpd.drinkapp.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginScreenViewModel : ViewModel (){
    var state : LoginScreenState = LoginScreenState()
    fun login(){}

}

