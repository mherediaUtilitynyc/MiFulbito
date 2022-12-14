package mzx.mifulbito.login.presentation

import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
                stateMachine.onEvent(LoginStateMachine.Event.OnInit)
            }
            it("read init") {
                assertEquals(stateMachine.state.value, LoginStateMachine.State.CheckingCredentials)
                verify {
                    listener.invoke(
                        LoginStateMachine.SideEffect.LoadLogin, testScope, stateMachine::onEvent
                    )
                }
            }
            describe("Token expired") {
                beforeEachTest {
                    stateMachine.onEvent(LoginStateMachine.Event.OnPasswordExpired("Some userName"))
                }
                it("Display Password") {
                    assertEquals(
                        stateMachine.state.value,
                        LoginStateMachine.State.CheckPassword("Some userName")
                    )
                }
            }

            describe("First time Login") {
                beforeEachTest {
                    stateMachine.onEvent(LoginStateMachine.Event.OnNotCredentials)
                }
                it("Check Credential") {
                    assertEquals(
                        stateMachine.state.value,
                        LoginStateMachine.State.CheckCredential
                    )
                }
                val checkCredentialEvent = LoginStateMachine.Event.OnCheckCredentials(
                    "user",
                    "password"
                )
                describe("enter valid credentials") {
                    beforeEachTest {
                        stateMachine.onEvent(
                            checkCredentialEvent
                        )
                    }
                    it("will display Checking credentials") {
                        assertEquals(
                            stateMachine.state.value,
                            LoginStateMachine.State.CheckingCredential
                        )
                        verify {
                            listener.invoke(
                                LoginStateMachine.SideEffect.CheckingCredentials(
                                    "user",
                                    "password"
                                ), testScope, stateMachine::onEvent
                            )
                        }
                    }
                    describe("login success") {
                        beforeEachTest {
                            stateMachine.onEvent(LoginStateMachine.Event.OnLoginSuccess)
                        }
                        it("Great Logged") {
                            assertEquals(
                                stateMachine.state.value,
                                LoginStateMachine.State.Logged
                            )
                        }
                    }
                    describe("Login error") {
                        beforeEachTest {
                            stateMachine.onEvent(LoginStateMachine.Event.OnLoginError("user"))
                        }
                        it("Password error") {
                            assertEquals(
                                stateMachine.state.value,
                                LoginStateMachine.State.CheckPassword("user")
                            )
                        }
                    }
                }
            }

            describe("Login Valid") {
                beforeEachTest {
                    stateMachine.onEvent(LoginStateMachine.Event.OnLoginSuccess)
                }
                it("Display Password") {
                    assertEquals(
                        stateMachine.state.value,
                        LoginStateMachine.State.Logged
                    )
                }
            }

            describe("Error Login") {
                beforeEachTest {
                    stateMachine.onEvent(LoginStateMachine.Event.OnError)
                }
                it("Display Error Login") {
                    assertEquals(
                        stateMachine.state.value,
                        LoginStateMachine.State.LoggedError
                    )
                }
            }
        }
    }
})