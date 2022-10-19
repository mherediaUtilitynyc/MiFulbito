package utility.closet.data.server.json

import javax.inject.Inject
import mzx.mifulbito.data.login.LoginRepository

class MockLoginRepository @Inject constructor() : LoginRepository {
    override suspend fun signInToken(tokenCredentials: Pair<String, String>): Boolean {
        TODO("Not yet implemented")
    }
}