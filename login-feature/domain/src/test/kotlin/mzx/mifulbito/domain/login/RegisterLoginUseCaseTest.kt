package mzx.mifulbito.domain.login

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import mzx.mifulbito.data.login.LoginRepository
import mzx.mifulbito.data.login.RegisteredUserRepository
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class RegisterLoginUseCaseTest : Spek({
    describe("Given RegisterLoginUseCase") {

        val userRepository: RegisteredUserRepository by memoized { mockk() }
        val loginRepository: LoginRepository by memoized { mockk() }
        val useCase by memoized { RegisterLoginUseCase(userRepository, loginRepository) }
        lateinit var result: Either<RegisterLoginUseCase.RegisterLoginError, RegisterLoginUseCase.RegisterLoginResult>

        describe("Login success") {
            beforeEachTest {
                coEvery { userRepository.hasValidCredentials() } returns true
                coEvery { userRepository.tokenCredentials() } returns ("user name" to "Token value")
                coEvery { loginRepository.signInToken("user name" to "Token value") } returns true

                runBlocking { result = useCase.action(Unit) }
            }
            it("user logged") {
                assertEquals(result, RegisterLoginUseCase.RegisterLoginResult.LoginSuccess.right())
            }
        }

        describe("There aren't credentials registered") {
            beforeEachTest {
                coEvery { userRepository.hasValidCredentials() } returns false
                coEvery { userRepository.hasExpiredCredentials() } returns false

                runBlocking { result = useCase.action(Unit) }
            }
            it("No credential is displayed") {
                assertEquals(result, RegisterLoginUseCase.RegisterLoginResult.NoCredentials.right())
            }
        }

        describe("Password was expired") {
            beforeEachTest {
                coEvery { userRepository.hasValidCredentials() } returns false
                coEvery { userRepository.hasExpiredCredentials() } returns true
                coEvery { userRepository.getRegisteredUserName() } returns "some username"

                runBlocking { result = useCase.action(Unit) }
            }
            it("No credential is displayed") {
                assertEquals(
                    result,
                    RegisterLoginUseCase.RegisterLoginResult.PasswordExpired("some username")
                        .right()
                )
            }
        }
        describe("Login Returns Error") {
            beforeEachTest {
                coEvery { userRepository.hasValidCredentials() } returns true
                coEvery { userRepository.tokenCredentials() } returns ("user name" to "Token value")
                coEvery { loginRepository.signInToken("user name" to "Token value") } returns false
                coEvery { userRepository.getRegisteredUserName() } returns "some username"

                runBlocking { result = useCase.action(Unit) }
            }
            it("Error on credentials in login") {
                assertEquals(
                    result,
                    RegisterLoginUseCase.RegisterLoginError.LoginError("some username")
                        .left()
                )
            }
        }
    }
})