package com.jcpd.home.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.data.Repository
import com.jcpd.data.models.ListCocktailModel
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
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val preferencesDataSource: ProtoDataStoreDataSource
) : ViewModel() {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState())
    val state : StateFlow<HomeScreenState> = _state.asStateFlow()

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
}