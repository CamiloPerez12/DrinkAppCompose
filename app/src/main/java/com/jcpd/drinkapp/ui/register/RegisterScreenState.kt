package com.jcpd.drinkapp.ui.register

import com.google.firebase.auth.FirebaseUser

data class RegisterScreenState (
    val loading : Boolean = false,
    val access : Boolean? = null,
    val idle : Boolean? = null,
    val valid : Boolean? = null,
    val userCreated : Boolean? = null,
    val user : FirebaseUser? = null,
    val error : String? = null
)