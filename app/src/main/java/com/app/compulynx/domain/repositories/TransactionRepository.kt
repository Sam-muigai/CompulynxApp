package com.app.compulynx.domain.repositories

import com.app.compulynx.domain.models.LocalTransaction
import com.app.compulynx.domain.models.SendMoneyRequest
import com.app.compulynx.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getLast100Transactions(): Result<List<Transaction>>
    suspend fun getMiniStatement(): Result<List<Transaction>>
    suspend fun saveLocalTransaction(sendMoneyRequest: SendMoneyRequest)
    suspend fun syncLocalTransactions()

    suspend fun getSyncingTransactions(): Flow<List<LocalTransaction>>
}