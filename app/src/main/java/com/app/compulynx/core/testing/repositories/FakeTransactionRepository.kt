package com.app.compulynx.core.testing.repositories

import com.app.compulynx.core.database.entities.SyncStatus
import com.app.compulynx.domain.models.LocalTransaction
import com.app.compulynx.domain.models.SendMoneyRequest
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import java.util.UUID

class FakeTransactionRepository : TransactionRepository {
    private val localTransactionsList = mutableListOf<LocalTransaction>()
    private val localTransactionsFlow = MutableStateFlow<List<LocalTransaction>>(emptyList())
    private val syncingTransactionsFlow = MutableStateFlow<List<LocalTransaction>>(emptyList())
    private var last100Result: Result<List<Transaction>> = Result.success(emptyList())
    private var miniStatementResult: Result<List<Transaction>> = Result.success(emptyList())

    var syncCalled = false
        private set

    override suspend fun getLast100Transactions(): Result<List<Transaction>> = last100Result

    override suspend fun getMiniStatement(): Result<List<Transaction>> = miniStatementResult

    override suspend fun saveLocalTransaction(sendMoneyRequest: SendMoneyRequest) {
        // Map the request to a LocalTransaction object (mocking the mapping logic)
        val newTransaction =
            LocalTransaction(
                clientTransactionId = UUID.randomUUID(),
                accountFrom = "mockAccount",
                accountTo = sendMoneyRequest.accountTo,
                createdAt = LocalDateTime.now(),
                syncStatus = SyncStatus.QUEUED,
                amount = sendMoneyRequest.amount.toDouble(),
            )
        localTransactionsList.add(newTransaction)
        localTransactionsFlow.value = localTransactionsList.toList()
    }

    override suspend fun syncLocalTransactions() {
        syncCalled = true
    }

    override fun getSyncingTransactions(): Flow<List<LocalTransaction>> = syncingTransactionsFlow

    override fun getAllLocalTransactions(): Flow<List<LocalTransaction>> = localTransactionsFlow

    fun setLast100Result(result: Result<List<Transaction>>) {
        last100Result = result
    }

    fun setMiniStatementResult(result: Result<List<Transaction>>) {
        miniStatementResult = result
    }

    fun emitSyncingTransactions(list: List<LocalTransaction>) {
        syncingTransactionsFlow.value = list
    }
}
