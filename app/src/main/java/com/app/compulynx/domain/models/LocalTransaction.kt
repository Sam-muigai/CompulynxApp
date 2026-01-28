package com.app.compulynx.domain.models

import com.app.compulynx.core.database.entities.SyncStatus
import java.time.LocalDateTime
import java.util.UUID

data class LocalTransaction(
    val clientTransactionId: UUID,
    val accountFrom: String,
    val accountTo: String,
    val createdAt: LocalDateTime,
    val syncStatus: SyncStatus,
    val amount: Double,
)
