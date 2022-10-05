package mzx.mifulbito.login.presentation

import arrow.core.right
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import mzx.mifulbito.domain.login.RegisterLoginUseCase
import mzx.mifulbito.domain.login.UseCase
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
internal class LoginEffectListenerTest : Spek({

    describe("Given a LoginEffect Listener") {
        val useCase: UseCase<Unit,
                RegisterLoginUseCase.RegisterLoginResult,
                RegisterLoginUseCase.RegisterLoginError> by memoized {
            mockk()
        }
        val listener by memoized { LoginEffectListener(useCase) }
        val testScope = TestScope()
        val onEvent: (LoginStateMachine.Event) -> Unit by memoized { mockk() }

        describe("Loading effect is launched") {

            describe("Use case returns success login") {


//                lateinit var useCaseResult:
//                        Either<RegisterLoginUseCase.RegisterLoginError, RegisterLoginUseCase.RegisterLoginResult>
//                useCaseResult =
//                    RegisterLoginUseCase.RegisterLoginResult.LoginSuccess.right()

                beforeEachTest {
//                        Dispatchers.setMain(newSingleThreadContext("Test thread"))
                    coEvery {
                        useCase.action(Unit)
                    } answers {
                        RegisterLoginUseCase.RegisterLoginResult.LoginSuccess.right()
                    }
                    runBlocking {
                        listener.invoke(
                            sideEffect = LoginStateMachine.SideEffect.LoadLogin,
                            viewModelScope = this@runBlocking,
                            onEvent = onEvent
                        )
                    }
                }
                it(
                    "Login success in notified"
                ) {

                    runBlocking {
                        coVerify {

                            onEvent(LoginStateMachine.Event.OnLoginSuccess)
                        }

                    }
                }





                afterEachTest {

                }


//                    testScope.
            }

        }

//            describe("Use case returns success expire") {
//                beforeEachTest {
//                    useCaseResult =
//                        RegisterLoginUseCase.RegisterLoginResult.LoginSuccess.right()


//                    listener.invoke(
//                        sideEffect = LoginStateMachine.SideEffect.LoadLogin,
//                        viewModelScope = testScope,
//                        onEvent = onEvent
//                    )
//                }
//                it("Login success in notified") {
//                    coVerify { onEvent(LoginStateMachine.Event.OnNotCredentials) }
//                }
//            }

    }


})