package com.app.compulynx.core.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendMoneyResponseDto(
    @SerialName("response_message")
    val responseMessage: String?,
    @SerialName("response_status")
    val responseStatus: Boolean?
)