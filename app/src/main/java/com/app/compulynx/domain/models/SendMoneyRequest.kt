package com.app.compulynx.domain.models

data class SendMoneyRequest(
    val accountTo: String,
    val amount: Int,
)
