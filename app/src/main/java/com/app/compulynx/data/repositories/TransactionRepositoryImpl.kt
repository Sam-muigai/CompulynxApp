package com.app.compulynx.data.repositories

import com.app.compulynx.core.network.CompulynxApiService
import com.app.compulynx.core.network.dtos.TransactionRequestDto
import com.app.compulynx.data.helpers.mapResult
import com.app.compulynx.data.mappers.toDomain
import com.app.compulynx.data.mappers.toDto
import com.app.compulynx.domain.models.SendMoneyRequest
import com.app.compulynx.domain.models.SendMoneyResponse
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.domain.repositories.TransactionRepository

class TransactionRepositoryImpl(
    private val apiService: CompulynxApiService
) : TransactionRepository {
    override suspend fun getLast100Transactions(customerId: String): Result<List<Transaction>> {
        return apiService.getLast100Transactions(TransactionRequestDto(customerId))
            .mapResult { transactionDtos ->
                transactionDtos?.map { it.toDomain() } ?: emptyList()
            }
    }

    override suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest): Result<SendMoneyResponse> {
        return apiService.sendMoney(sendMoneyRequest.toDto())
            .mapResult {
                it?.toDomain() ?: SendMoneyResponse()
            }
    }


}