package com.jcpd.home.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jcpd.data.Repository
import com.jcpd.data.models.ListCocktailModel
import com.jcpd.drinkapp.CryptoManager
import com.jcpd.drinkapp.core.protodatastore.ProtoDataStoreDataSource
import com.jcpd.drinkapp.core.protodatastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val preferencesDataSource: ProtoDataStoreDataSource,
    private val cryptoManager: CryptoManager,
    @Named("secretFile") private val secretFile: File
) : ViewModel() {
    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    val preferencesStateFlow: StateFlow<UserPreferences?> = preferencesState().stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        randomCocktail()
        saveName()
    }

    private fun randomCocktail() {
        viewModelScope.launch {
            try {
                val response: Response<ListCocktailModel> = repository.getRandomCocktail()
                _state.update { state ->
                    state.copy(
                        loading = false,
                        error = response.errorBody()?.string(),
                        data = response.body(),
                    )
                }
                if (response.isSuccessful && response.body() != null) {
                    saveJson(response.body()!!)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                _state.update { state ->
                    state.copy(loading = false)
                }
            }
        }
    }

    fun updateCounterValue(value: Int) {
        viewModelScope.launch {
            preferencesDataSource.updateCounter(value)
        }
    }

    fun toggleCounter() {
        viewModelScope.launch {
            preferencesDataSource.toggleCounter()
        }
    }

    private fun saveName() {
        viewModelScope.launch {
            preferencesDataSource.saveName("Jonathan")
        }
    }

    private fun preferencesState(): Flow<UserPreferences> {
        return preferencesDataSource.userPreferencesFlow
    }

    private fun saveJson(json: ListCocktailModel) {
        val jsonCocktail = Json.encodeToString(ListCocktailModel.serializer(), json)
        val bytes = jsonCocktail.encodeToByteArray()

        // Use the 'use' block to automatically manage the FileOutputStream
        FileOutputStream(secretFile).use { fos ->
            // The cryptoManager will write to the stream 'fos'
            cryptoManager.encrypt(
                bytes = bytes,
                outputStream = fos
            )
        } // <-- fos.close() is automatically called here, flushing and closing the file.
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