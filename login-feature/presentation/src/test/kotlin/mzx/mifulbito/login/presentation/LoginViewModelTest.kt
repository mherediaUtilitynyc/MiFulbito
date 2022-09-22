package mzx.mifulbito.login.presentation

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class LoginViewModelTest:Spek({
    describe("this is a test"){
        val alo by memoized { 2 }
        it("alo alo"){
            assertEquals("2", 3)
        }
    }
})