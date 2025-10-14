package com.jcpd.loginscreen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.jcpd.data.Repository
import com.jcpd.drinkapp.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val cryptoManager: CryptoManager,
    @Named("secretFile") private val secretFile: File
) : ViewModel() {
    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState())
    val state = _state.asStateFlow()

    init {
        getDecodedCocktail()
    }

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

    fun getDecodedCocktail() {
        // --- Defensive Check ---
        // First, check if the file exists and has content before trying to read it.
        if (!secretFile.exists() || secretFile.length() == 0L) {
            Log.w("Crypto", "Attempted to decode, but the file is missing or empty. This is expected on first run.")
            // Optionally update the UI to show that no data is available yet.
            _state.update { state ->
                state.copy(
                    decodedCocktail = "No saved cocktail found."
                )
            }
            return // IMPORTANT: Stop the function here to prevent the crash.
        }

        // --- Proceed with Decryption ---
        // Wrap in a try-catch block for safety against file corruption or other errors.
        try {
            val bytes = cryptoManager.decrypt(
                inputStream = FileInputStream(secretFile)
            )
            val decodedString = bytes.decodeToString()

            _state.update { state ->
                state.copy(
                    decodedCocktail = decodedString
                )
            }
        } catch (e: Exception) {
            Log.e("Crypto", "Failed to decrypt file: ${e.message}", e)
            _state.update { state ->
                state.copy(
                    decodedCocktail = "Error: Could not decode saved cocktail."
                )
            }
        }
    }
}






