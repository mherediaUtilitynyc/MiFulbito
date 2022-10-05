package mzx.mifulbito.domain.login

import arrow.core.Either
import arrow.core.right
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import mzx.mifulbito.data.login.RegisteredUserRepository
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class RegisterLoginUseCaseTest : Spek({
    describe("Given RegisterLoginUseCase") {

        val userRepository: RegisteredUserRepository by memoized { mockk() }
        val useCase by memoized { RegisterLoginUseCase(userRepository) }
        lateinit var result: Either<RegisterLoginUseCase.RegisterLoginError, RegisterLoginUseCase.RegisterLoginResult>

        describe("There aren't credentials registered") {
            beforeEachTest {
                runBlocking { result = useCase.action(Unit) }
            }
            it("No credential is displayed") {
                assertEquals(result, RegisterLoginUseCase.RegisterLoginResult.NoCredentials.right())
            }
        }

        describe("Password was expired") {
            it("No credential is displayed") {
                assertEquals(
                    result,
                    RegisterLoginUseCase.RegisterLoginResult.PasswordExpired("some username")
                        .right()
                )
            }
        }
    }
})