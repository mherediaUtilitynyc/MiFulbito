package mzx.mifulbito.login.presentation

import io.mockk.mockk
import mzx.mifulbito.domain.login.RegisterLoginUseCase
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class LoginViewModelTest : Spek({
    describe("a view Model") {
        val useCase: RegisterLoginUseCase by memoized { mockk { RegisterLoginUseCase::class.java } }
//        val viewModel by memoized { LoginViewModel(useCase) }
        it("it is initialized ") {
//            assertEquals(viewModel.state.value, LoginViewModel.LoginViewState.Init)
        }
        describe("launch login") {
            val name by memoized { "name" }
            val password by memoized { "password" }
            beforeEachTest {
//                viewModel.onEvent(LoginViewModel.LoginViewEvent.SignIn(name, password))
            }
            it("login is launched") {
//                assertEquals(viewModel.state.value, LoginViewModel.LoginViewState.Init.toLoading())
            }
            describe("server success") {
                beforeEachTest {

                }

            }
            describe("server error") {

            }

        }
    }
})