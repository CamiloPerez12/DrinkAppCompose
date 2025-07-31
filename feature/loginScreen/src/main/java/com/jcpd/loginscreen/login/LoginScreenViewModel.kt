package com.jcpd.loginscreen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState())
    val state = _state.asStateFlow()

    private val auth: FirebaseAuth = Firebase.auth

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _state.update { state -> state.copy(loading = true) }
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("DrinkAppUser", "El user se a logeado")
                        _state.update { state -> state.copy(loading = false, access = true) }
                    } else {
                        _state.update { state -> state.copy(loading = false, access = false) }
                    }
                }
            } catch (ex: Exception) {
                Log.d("DrinkAppUser", "Algo pasó: ${ex.message}")
                _state.update { state -> state.copy(loading = false, access = false) }
            }
        }
    }
}






