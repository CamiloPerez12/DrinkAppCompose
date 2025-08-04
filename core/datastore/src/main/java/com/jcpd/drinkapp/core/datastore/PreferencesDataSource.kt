package com.jcpd.drinkapp.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    fun getCounterValue() = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.COUNTER]
    }

    suspend fun updateCounterValue(value: Int) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.COUNTER] = value
        }
    }
}