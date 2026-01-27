package com.app.compulynx.data.repositories

import com.app.compulynx.core.datastore.CompulynxPreferences
import com.app.compulynx.core.network.CompulynxApiService
import com.app.compulynx.core.network.dtos.AccountRequestDto
import com.app.compulynx.data.helpers.mapResult
import com.app.compulynx.data.mappers.toDomain
import com.app.compulynx.domain.models.Account
import com.app.compulynx.domain.repositories.AccountRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: CompulynxApiService,
    private val compulynxPreferences: CompulynxPreferences
) : AccountRepository {
    override suspend fun getAccountDetails(): Result<Account> {
        val customerId =
            compulynxPreferences.getCustomerId().first()
        return apiService.getAccountBalance(AccountRequestDto(customerId))
            .mapResult { responseDto ->
                responseDto?.toDomain() ?: Account()
            }
    }
}