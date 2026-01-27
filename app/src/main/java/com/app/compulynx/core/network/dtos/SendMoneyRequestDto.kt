package com.app.compulynx.core.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendMoneyRequestDto(
    @SerialName("accountFrom")
    val accountFrom: String?,
    @SerialName("accountTo")
    val accountTo: String?,
    @SerialName("amount")
    val amount: Int?,
    @SerialName("customerId")
    val customerId: String?
)