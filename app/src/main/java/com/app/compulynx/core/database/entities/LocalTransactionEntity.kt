package com.app.compulynx.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "local_transactions")
data class LocalTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clientTransactionId: UUID,
    val accountFrom: String,
    val accountTo: String,
    val createdAt: LocalDateTime,
    val syncStatus: SyncStatus,
    val amount: Double,
)

enum class SyncStatus {
    QUEUED,
    SYNCING,
    SYNCED,
    FAILED,
}
