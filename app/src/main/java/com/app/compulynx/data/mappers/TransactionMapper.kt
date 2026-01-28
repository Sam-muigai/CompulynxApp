package com.app.compulynx.data.mappers

import com.app.compulynx.core.database.entities.LocalTransactionEntity
import com.app.compulynx.core.network.dtos.SendMoneyResponseDto
import com.app.compulynx.core.network.dtos.TransactionDto
import com.app.compulynx.domain.models.LocalTransaction
import com.app.compulynx.domain.models.SendMoneyResponse
import com.app.compulynx.domain.models.Transaction

fun TransactionDto.toDomain(): Transaction {
    return Transaction(
        id = id ?: 0,
        accountNo = accountNo ?: "",
        amount = amount ?: 0.0,
        balance = balance ?: 0.0,
        customerId = customerId ?: "",
        debitOrCredit = debitOrCredit ?: "",
        transactionType = transactionType ?: "",
        transactionId = transactionId ?: "",
    )
}


fun SendMoneyResponseDto.toDomain(): SendMoneyResponse {
    return SendMoneyResponse(
        message = responseMessage ?: "",
        status = responseStatus ?: false,
    )
}

fun LocalTransactionEntity.toDomain(): LocalTransaction {
    return LocalTransaction(
        clientTransactionId = clientTransactionId,
        accountFrom = accountFrom,
        accountTo = accountTo,
        createdAt = createdAt,
        syncStatus = syncStatus,
        amount = amount
    )
}