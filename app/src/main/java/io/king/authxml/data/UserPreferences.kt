package io.king.authxml.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences (
    context: Context
        ){
    private val applicationContext = context.applicationContext
    private val  dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_base"
        )
    }

    val authToken: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[Key_Auth]
    }

    suspend fun saveAccessTokens(authToken: String) {
        dataStore.edit { preferences ->
            preferences[Key_Auth] = authToken
        }
    }

    companion object {
        private val Key_Auth = preferencesKey<String>("key_auth")
    }

}