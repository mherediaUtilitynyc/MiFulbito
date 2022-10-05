package mzx.mifulbito.domain.login

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import javax.inject.Inject
import mzx.mifulbito.data.login.LoginRepository
import mzx.mifulbito.data.login.RegisteredUserRepository

class RegisterLoginUseCase @Inject constructor(
    private val registeredUserRepository: RegisteredUserRepository,
    private val loginRepository: LoginRepository
) :
    UseCase<Unit,
            RegisterLoginUseCase.RegisterLoginResult,
            RegisterLoginUseCase.RegisterLoginError> {

    sealed interface RegisterLoginResult {
        object LoginSuccess : RegisterLoginResult
        object NoCredentials : RegisterLoginResult
        data class PasswordExpired(val userName: String) : RegisterLoginResult
    }

    sealed interface RegisterLoginError {
        data class LoginError(val userName: String) : RegisterLoginError
        object CommonError : RegisterLoginError
    }

    override suspend fun action(param: Unit): Either<RegisterLoginError, RegisterLoginResult> {
        return when {
            registeredUserRepository.hasValidCredentials() -> validateLogin()
            registeredUserRepository.hasExpiredCredentials() ->
                RegisterLoginResult.PasswordExpired(registeredUserRepository.getRegisteredUserName())
                    .right()
            else -> RegisterLoginResult.NoCredentials.right()
        }
    }

    private suspend fun validateLogin() = if (loginRepository.signInToken(
            registeredUserRepository.tokenCredentials()
        )
    ) {
        RegisterLoginResult.LoginSuccess.right()
    } else {
        RegisterLoginError.LoginError(registeredUserRepository.getRegisteredUserName())
            .left()
    }

}

interface UseCase<P, R, E> {
    suspend fun action(param: P): Either<E, R>
}