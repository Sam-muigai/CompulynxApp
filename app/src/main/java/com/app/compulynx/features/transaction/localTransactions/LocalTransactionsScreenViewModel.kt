package com.app.compulynx.features.transaction.localTransactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compulynx.domain.repositories.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LocalTransactionsScreenViewModel
    @Inject
    constructor(
        private val transactionRepository: TransactionRepository,
    ) : ViewModel() {
        val localTransactions =
            transactionRepository.getAllLocalTransactions()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList(),
                )
    }
