package com.jcpd.drinkapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.jcpd.registerscreen.register.RegisterScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<RegisterScreenState>(RegisterScreenState())
    val state = _state.asStateFlow()

    private val auth: FirebaseAuth = Firebase.auth

    fun createUserWithEmailAndPassword(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _state.value = _state.value.copy(
                loading = false,
                error = "Email and password for sign up cannot be empty."
            )
            return
        }
        _state.value =
            _state.value.copy(loading = true, error = null, access = null)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "createUserWithEmail:success")
                    val user = auth.currentUser
                    _state.value =
                        _state.value.copy(loading = false, access = true, error = null, user = user)
                } else {
                    Log.w("Auth", "createUserWithEmail:failure", task.exception)
                    _state.value = _state.value.copy(
                        loading = false,
                        access = false,
                        error = task.exception?.message ?: "Sign up failed."
                    )
                }
            }
    }
}






