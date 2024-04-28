package com.developersmarket.componentscompose.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


const val MERCHANT_DATASTORE = "user_Session"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)


class DataStoreManager(val context: Context) {


    companion object {

        val ISLOGIN = booleanPreferencesKey("ISLOGIN")
        val FNAME = stringPreferencesKey("FNAME")
        val LNAME = stringPreferencesKey("LNAME")
        val EMAIL = stringPreferencesKey("EMAIL")
        val PASSWORD = stringPreferencesKey("PASSSWORD")

    }


    suspend fun saveUserSession(user: User)  {
        context.dataStore.edit {
            it[ISLOGIN]= user.isLogin
            it[FNAME] = user.firstName
            it[LNAME] = user.lastName
            it[EMAIL] = user.emailAddress
            it[PASSWORD] = user.password

        }
    }


    fun getFromDataStore():Flow<User> = context.dataStore.data.map {
        User(
            isLogin = it[ISLOGIN]?:false,
            emailAddress = it[EMAIL]?:"",
            password = it[PASSWORD]?:""
        )

    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}