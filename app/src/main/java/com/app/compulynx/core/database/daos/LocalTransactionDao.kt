package com.app.compulynx.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.compulynx.core.database.entities.LocalTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalTransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transaction: LocalTransactionEntity)

    @Query("SELECT * FROM local_transactions")
    fun getAllTransactions(): Flow<List<LocalTransactionEntity>>
}