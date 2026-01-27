package com.app.compulynx.domain.models

import kotlinx.serialization.SerialName

data class SendMoneyResponse(
    val message: String = "",
    val status: Boolean = false,
)