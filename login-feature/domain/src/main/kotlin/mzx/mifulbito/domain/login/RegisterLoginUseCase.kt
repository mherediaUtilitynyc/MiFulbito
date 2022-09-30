package mzx.mifulbito.domain.login

import arrow.core.Either
import javax.inject.Inject

class RegisterLoginUseCase @Inject constructor() :
    UseCase<Unit,
            RegisterLoginUseCase.RegisterLoginResult,
            RegisterLoginUseCase.RegisterLoginError> {

    object RegisterLoginResult
    object RegisterLoginError

    override suspend fun action(param: Unit): Either<RegisterLoginResult, RegisterLoginError> {
        TODO("Not yet implemented")
    }

}

interface UseCase<P, R, E> {
    suspend fun action(param: P): Either<R, E>
}