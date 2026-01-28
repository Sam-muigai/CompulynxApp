package com.app.compulynx.core.testing.repositories

import com.app.compulynx.domain.models.Account
import com.app.compulynx.domain.repositories.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAccountRepository : AccountRepository {
    private val username = MutableStateFlow("")
    private val email = MutableStateFlow("")
    private val customerId = MutableStateFlow("")
    private val accountNumber = MutableStateFlow("")
    private var accountResult: Result<Account> = Result.success(Account())

    override suspend fun getAccountDetails(): Result<Account> = accountResult

    override fun getUsername(): Flow<String> = username

    override fun getEmail(): Flow<String> = email

    override fun getCustomerId(): Flow<String> = customerId

    override fun getAccountNumber(): Flow<String> = accountNumber

    fun emitUsername(value: String) {
        username.value = value
    }

    fun emitEmail(value: String) {
        email.value = value
    }

    fun emitCustomerId(value: String) {
        customerId.value = value
    }

    fun emitAccountNumber(value: String) {
        accountNumber.value = value
    }

    fun setAccountResult(result: Result<Account>) {
        accountResult = result
    }
}
