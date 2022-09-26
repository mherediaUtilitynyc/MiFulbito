package mzx.mifulbito.domain.login

import arrow.core.Either
import javax.inject.Inject

class RegisterLoginUseCase @Inject constructor():
    UseCase<RegisterLoginUseCase.RegisterLoginParam,
            RegisterLoginUseCase.RegisterLoginResult,
            RegisterLoginUseCase.RegisterLoginError> {

    data class RegisterLoginParam(val name: String, val password: String) : UseCase.Param
    object RegisterLoginResult : UseCase.Result
    object RegisterLoginError : UseCase.Error

    override suspend fun action(param: UseCase.Param): Either<UseCase.Result, UseCase.Error> {
        TODO("Not yet implemented")
    }

}

interface UseCase<P : UseCase.Param, R : UseCase.Result, E : UseCase.Error> {
    suspend fun action(param: Param): Either<Result, Error>
    interface Param
    interface Result
    interface Error
}