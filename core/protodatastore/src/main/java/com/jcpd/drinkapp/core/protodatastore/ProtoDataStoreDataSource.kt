package com.jcpd.drinkapp.core.protodatastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProtoDataStoreDataSource @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>,
) {

    val userPreferencesFlow: Flow<UserPreferences> = userPreferencesDataStore.data

    suspend fun saveName(name: String) {
        userPreferencesDataStore.updateData {
            it.toBuilder()
                .setName(name)
                .build()
        }
    }

    suspend fun updateCounter(counter: Int) {
        userPreferencesDataStore.updateData {
            it.toBuilder()
                .setCounter(counter)
                .build()
        }
    }

    suspend fun toggleCounter() {
        userPreferencesDataStore.updateData {
            it.toBuilder()
                .setCounterFlag(!it.counterFlag)
                .build()
        }
    }
}