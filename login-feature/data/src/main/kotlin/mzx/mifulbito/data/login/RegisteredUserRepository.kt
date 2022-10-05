package mzx.mifulbito.data.login

interface RegisteredUserRepository {
    suspend fun hasValidCredentials(): Boolean
    suspend fun hasExpiredCredentials(): Boolean
    suspend fun getRegisteredUserName(): String
    suspend fun tokenCredentials(): Pair<String, String>
}