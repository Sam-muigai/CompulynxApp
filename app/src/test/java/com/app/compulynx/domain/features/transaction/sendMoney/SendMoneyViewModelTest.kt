package com.app.compulynx.domain.features.transaction.sendMoney

import com.app.compulynx.core.testing.repositories.FakeTransactionRepository
import com.app.compulynx.core.testing.sync.FakeSyncManager
import com.app.compulynx.features.transaction.sendMoney.SendMoneyScreenEffect
import com.app.compulynx.features.transaction.sendMoney.SendMoneyScreenEvent
import com.app.compulynx.features.transaction.sendMoney.SendMoneyScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SendMoneyViewModelTest {

    private lateinit var viewModel: SendMoneyScreenViewModel
    private lateinit var fakeRepository: FakeTransactionRepository
    private lateinit var fakeSyncManager: FakeSyncManager
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeTransactionRepository()
        fakeSyncManager = FakeSyncManager()
        viewModel = SendMoneyScreenViewModel(fakeRepository, fakeSyncManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when form is valid and send money is clicked, transaction is saved and sync requested`() = runTest {

        viewModel.handleEvent(SendMoneyScreenEvent.OnAmountChanged("1000"))
        viewModel.handleEvent(SendMoneyScreenEvent.OnAccountToChanged("ACT1002"))


        val effects = mutableListOf<SendMoneyScreenEffect>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.effect.collect { effects.add(it) }
        }

        viewModel.handleEvent(SendMoneyScreenEvent.OnSendMoneyClicked)
        advanceUntilIdle()


        // Verify Repository saved the data
        val savedTransactions = fakeRepository.getAllLocalTransactions().first()

        assertEquals(1, savedTransactions.size)
        assertEquals("1000.0", savedTransactions[0].amount.toString())

        // Verify SyncManager was triggered
        assertTrue("Sync should be requested", fakeSyncManager.syncRequested)

        // Verify Navigation Effect was sent
        assertTrue(effects.any { it is SendMoneyScreenEffect.NavigateBack })
    }

    @Test
    fun `when amount is changed, form validation updates correctly`() {
        // Start false
        assertFalse(viewModel.state.value.isFormValid)

        // Act
        viewModel.handleEvent(SendMoneyScreenEvent.OnAmountChanged("500"))
        viewModel.handleEvent(SendMoneyScreenEvent.OnAccountToChanged("ACT1002"))

        // Assert
        val state = viewModel.state.value
        assertEquals("500", state.amount)
        assertTrue(state.isFormValid)
    }
}