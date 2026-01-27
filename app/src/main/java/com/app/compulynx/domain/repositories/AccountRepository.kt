package com.app.compulynx.domain.repositories

import com.app.compulynx.domain.models.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccountDetails(): Result<Account>

    suspend fun getUsername(): Flow<String>
}