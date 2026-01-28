package com.app.compulynx.data.repositories

import com.app.compulynx.core.database.daos.LocalTransactionDao
import com.app.compulynx.core.database.entities.LocalTransactionEntity
import com.app.compulynx.core.database.entities.SyncStatus
import com.app.compulynx.core.datastore.CompulynxPreferences
import com.app.compulynx.core.network.CompulynxApiService
import com.app.compulynx.core.network.dtos.MiniStatementRequestDto
import com.app.compulynx.core.network.dtos.TransactionRequestDto
import com.app.compulynx.data.helpers.mapResult
import com.app.compulynx.data.mappers.toDomain
import com.app.compulynx.domain.models.SendMoneyRequest
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiService: CompulynxApiService,
    private val compulynxPreferences: CompulynxPreferences,
    private val localTransactionDao: LocalTransactionDao
) : TransactionRepository {
    override suspend fun getLast100Transactions(): Result<List<Transaction>> {
        val customerId = compulynxPreferences.getCustomerId().first()
        return apiService.getLast100Transactions(TransactionRequestDto(customerId))
            .mapResult { transactionDtos ->
                transactionDtos?.map { it.toDomain() } ?: emptyList()
            }
    }

    override suspend fun getMiniStatement(): Result<List<Transaction>> {
        val customerId = compulynxPreferences.getCustomerId().first()
        val accountNumber = compulynxPreferences.getAccountNumber().first()
        val miniStatementRequestDto =
            MiniStatementRequestDto(accountNo = accountNumber, customerId = customerId)
        return apiService.getMiniStatement(miniStatementRequestDto)
            .mapResult { transactionDtos ->
                transactionDtos?.map { it.toDomain() } ?: emptyList()
            }
    }

    override suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest) {
        val localTransaction = LocalTransactionEntity(
            clientTransactionId = UUID.randomUUID(),
            accountFrom = compulynxPreferences.getAccountNumber().first(),
            accountTo = sendMoneyRequest.accountTo,
            createdAt = LocalDateTime.now(),
            syncStatus = SyncStatus.QUEUED,
            amount = sendMoneyRequest.amount.toDouble()
        )
        localTransactionDao.saveTransaction(localTransaction)
    }

}