package com.app.compulynx.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.compulynx.core.database.CompulynxDatabase
import com.app.compulynx.core.database.daos.LocalTransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
    ): CompulynxDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CompulynxDatabase::class.java,
            "compulynx_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalTransactionDao(database: CompulynxDatabase): LocalTransactionDao{
        return database.localTransactionDao()
    }

}