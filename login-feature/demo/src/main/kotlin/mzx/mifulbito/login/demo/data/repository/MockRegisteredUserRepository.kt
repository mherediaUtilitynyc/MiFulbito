package mzx.mifulbito.login.demo.data.repository

import javax.inject.Inject
import mzx.mifulbito.data.login.RegisteredUserRepository

class MockRegisteredUserRepository @Inject constructor(): RegisteredUserRepository {
    override suspend fun hasValidCredentials(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun hasExpiredCredentials(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getRegisteredUserName(): String {
        TODO("Not yet implemented")
    }

    override suspend fun tokenCredentials(): Pair<String, String> {
        TODO("Not yet implemented")
    }
}