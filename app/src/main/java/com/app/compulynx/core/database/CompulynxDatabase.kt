package com.app.compulynx.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.compulynx.core.database.daos.LocalTransactionDao
import com.app.compulynx.core.database.entities.LocalTransactionEntity

@Database(entities = [LocalTransactionEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CompulynxDatabase : RoomDatabase() {
    abstract fun localTransactionDao(): LocalTransactionDao
}
