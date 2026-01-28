package com.app.compulynx.domain.models

import kotlinx.serialization.SerialName

data class SendMoneyRequest(
    val accountTo: String,
    val amount: Int,
)