package mzx.mifulbito.data.login

interface RegisteredUserRepository {
    fun hasValidCredentials(): Boolean
    fun hasExpiredCredentials(): Boolean
    fun getRegisteredUserName(): String
}