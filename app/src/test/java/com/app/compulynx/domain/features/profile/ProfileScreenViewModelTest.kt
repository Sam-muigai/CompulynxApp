package com.app.compulynx.domain.features.profile

import com.app.compulynx.core.testing.repositories.FakeAccountRepository
import com.app.compulynx.features.profile.ProfileScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModelTest {
    private lateinit var viewModel: ProfileScreenViewModel
    private lateinit var fakeAccountRepository: FakeAccountRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeAccountRepository = FakeAccountRepository()
        viewModel = ProfileScreenViewModel(fakeAccountRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when repository emits values, state is updated with combined data`() =
        runTest {
            val expectedName = "John Doe"
            val expectedEmail = "john@example.com"
            val expectedId = "CUST123"
            val expectedAcc = "ACC987"

            fakeAccountRepository.emitUsername(expectedName)
            fakeAccountRepository.emitEmail(expectedEmail)
            fakeAccountRepository.emitCustomerId(expectedId)
            fakeAccountRepository.emitAccountNumber(expectedAcc)

            advanceUntilIdle()

            val state = viewModel.profileScreenState.value
            assertEquals(expectedName, state.name)
            assertEquals(expectedEmail, state.email)
            assertEquals(expectedId, state.customerId)
            assertEquals(expectedAcc, state.accountNumber)
        }

    @Test
    fun `when only username changes, other state values persist`() =
        runTest {
            fakeAccountRepository.emitUsername("Old Name")
            fakeAccountRepository.emitEmail("test@test.com")
            fakeAccountRepository.emitCustomerId("1")
            fakeAccountRepository.emitAccountNumber("1")
            advanceUntilIdle()

            val newName = "New Name"
            fakeAccountRepository.emitUsername(newName)
            advanceUntilIdle()

            val state = viewModel.profileScreenState.value
            assertEquals(newName, state.name)
            assertEquals("test@test.com", state.email) // Remains the same
        }
}
