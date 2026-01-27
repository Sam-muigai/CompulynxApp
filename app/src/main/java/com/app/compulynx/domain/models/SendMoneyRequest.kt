package com.app.compulynx.domain.models

import kotlinx.serialization.SerialName

data class SendMoneyRequest(
    val accountFrom: String,
    val accountTo: String,
    val amount: Int,
    val customerId: String
)