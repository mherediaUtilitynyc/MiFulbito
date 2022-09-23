package mzx.mifulbito.login.presentation

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class LoginViewModelTest : Spek({
    describe("a view Model") {
        val viewModel by memoized { LoginViewModel() }
        it("it is initialized ") {
            assertEquals(viewModel.state.value, LoginViewModel.LoginViewState.Init)
        }
        describe("launch login") {
            val name by memoized { "name" }
            val password by memoized { "password" }
            beforeEachTest {
                viewModel.onEvent(LoginViewModel.LoginViewEvent.SignIn(name, password))
            }
            it("login is launched"){
                assertEquals(viewModel.state.value,LoginViewModel.LoginViewState.Init.toLoading())
            }

        }
    }
})