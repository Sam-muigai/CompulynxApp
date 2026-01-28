package com.app.compulynx.core.datastore.di

import android.content.Context
import com.app.compulynx.core.datastore.CompulynxPreferences
import com.app.compulynx.core.datastore.CompulynxPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideCompulynxDataStore(
        @ApplicationContext context: Context,
    ): CompulynxPreferences {
        return CompulynxPreferencesImpl(context)
    }
}
