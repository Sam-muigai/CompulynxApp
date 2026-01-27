package com.app.compulynx.data.mappers

import com.app.compulynx.core.network.dtos.SendMoneyRequestDto
import com.app.compulynx.core.network.dtos.SendMoneyResponseDto
import com.app.compulynx.core.network.dtos.TransactionDto
import com.app.compulynx.domain.models.SendMoneyRequest
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

fun SendMoneyRequest.toDto(): SendMoneyRequestDto {
    return SendMoneyRequestDto(
        accountFrom = accountFrom,
        accountTo = accountTo,
        amount = amount,
        customerId = customerId,
    )
}

fun SendMoneyResponseDto.toDomain(): SendMoneyResponse {
    return SendMoneyResponse(
        message = responseMessage ?: "",
        status = responseStatus ?: false,
    )
}