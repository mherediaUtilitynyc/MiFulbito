package mzx.mifulbito.data.login

interface LoginRepository {
    suspend fun signInToken(tokenCredentials: Pair<String, String>): Boolean
}