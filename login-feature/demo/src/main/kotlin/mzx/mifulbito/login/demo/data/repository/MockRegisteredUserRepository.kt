package mzx.mifulbito.login.demo.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import mzx.mifulbito.data.login.RegisteredUserRepository

class MockRegisteredUserRepository @Inject constructor(private val accountSettings: AccountSettings) :
    RegisteredUserRepository {

    override suspend fun hasValidCredentials(): Boolean {

        val userName = accountSettings.userName()
        val token = accountSettings.token()

        return userName != null && token != null && accountSettings.hasExpired()

    }

    override suspend fun hasExpiredCredentials(): Boolean = accountSettings.hasExpired()

    override suspend fun getRegisteredUserName(): String = accountSettings.userName()?:""

    override suspend fun tokenCredentials(): Pair<String, String>  =
        (accountSettings.userName()?:"") to (accountSettings.token()?:"")

    class AccountSettingsImpl @Inject constructor(@ApplicationContext private val context: Context) : AccountSettings {
        private val userName = stringPreferencesKey("user_name")
        private val token = stringPreferencesKey("token")
        private val expirationDate = stringPreferencesKey("expiration_date")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "accountSettings")

        private val dateFormatter =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ", Locale.getDefault())

        override suspend fun userName(): String? =
            context.dataStore.data.firstOrNull()?.get(userName)

        override suspend fun token(): String? = context.dataStore.data.firstOrNull()?.get(token)

        private suspend fun expirationDate(): Date? =
            context.dataStore.data.firstOrNull()?.get(expirationDate)
                ?.let { dateFormatter.parse(it) }

        override suspend fun hasExpired(): Boolean =
            expirationDate()?.let { it.time < System.currentTimeMillis() } ?: false
    }
}

interface AccountSettings {
    suspend fun userName(): String?

    suspend fun token(): String?

    suspend fun hasExpired(): Boolean
}