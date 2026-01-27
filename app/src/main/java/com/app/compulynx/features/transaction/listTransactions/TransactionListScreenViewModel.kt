package com.app.compulynx.features.transaction.listTransactions

import androidx.lifecycle.viewModelScope
import com.app.compulynx.core.base.BaseViewModel
import com.app.compulynx.core.base.UiEffect
import com.app.compulynx.core.base.UiEvent
import com.app.compulynx.core.base.UiState
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.domain.repositories.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


typealias ScreenModel = BaseViewModel<TransactionListScreenState, TransactionListScreenEvent, TransactionListScreenEffect>

@HiltViewModel
class TransactionListScreenViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ScreenModel(TransactionListScreenState.Loading) {


    override fun handleEvent(event: TransactionListScreenEvent) {}

    init {
        getTransactions()
    }

    private fun getTransactions() {
        setState { TransactionListScreenState.Loading }
        viewModelScope.launch {
            transactionRepository.getLast100Transactions()
                .onSuccess {
                    setState { TransactionListScreenState.Success(it) }
                }.onFailure {
                    setState { TransactionListScreenState.Error(it.message.toString()) }
                }
        }
    }

}


sealed class TransactionListScreenState : UiState {
    object Loading : TransactionListScreenState()
    data class Success(val transactions: List<Transaction>) : TransactionListScreenState()
    data class Error(val message: String) : TransactionListScreenState()
}

sealed class TransactionListScreenEvent : UiEvent

sealed class TransactionListScreenEffect : UiEffect