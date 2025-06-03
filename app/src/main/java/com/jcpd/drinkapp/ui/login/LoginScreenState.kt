package com.jcpd.drinkapp.ui.login

data class LoginScreenState(
    val loading : Boolean = false,
    val access : Boolean? = null,
    val idle : Boolean? = null
)

