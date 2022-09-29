package mzx.mifulbito.login.presentation

import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.test.TestScope
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


@OptIn(ExperimentalCoroutinesApi::class)
internal class LoginStateMachineTest : Spek({
    describe("LoginStateMachine") {
        val testScope by memoized { TestScope() }

        val listener: (
            LoginStateMachine.SideEffect, CoroutineScope,
            (LoginStateMachine.Event) -> Unit
        ) -> Unit by memoized { mockk(relaxed = true) }

        val stateMachine by memoized { LoginStateMachine(listener) }

        describe("Test init") {
            beforeEachTest {
                stateMachine.viewModelScope = testScope
                stateMachine.onEvent(LoginStateMachine.Event.OnInit("some name", password = "pass"))
            }
            it("read init") {
                assertEquals(stateMachine.state.value, LoginStateMachine.State.Loading)
                verify {
                    listener.invoke(
                        LoginStateMachine.SideEffect.LoadLogin(
                            name = "some name",
                            password = "pass"
                        ), testScope, stateMachine::onEvent
                    )
                }
            }
        }
    }
})