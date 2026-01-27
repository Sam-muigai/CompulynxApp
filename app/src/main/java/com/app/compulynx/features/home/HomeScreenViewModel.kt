package com.app.compulynx.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compulynx.core.base.BaseViewModel
import com.app.compulynx.core.base.SnackbarController
import com.app.compulynx.core.base.SnackbarEvent
import com.app.compulynx.core.base.UiEffect
import com.app.compulynx.core.base.UiEvent
import com.app.compulynx.core.base.UiState
import com.app.compulynx.domain.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


typealias ScreenModel = BaseViewModel<HomeScreenState, HomeScreenEvent, HomeScreenEffect>

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ScreenModel(HomeScreenState()) {

    init {
        viewModelScope.launch {
            val username = accountRepository.getUsername().first()
            setState { copy(username = username) }
        }
    }


    override fun handleEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.OnViewBalanceClick -> {
                fetchAccountDetails()
            }
        }
    }

    private fun fetchAccountDetails() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            accountRepository.getAccountDetails()
                .onSuccess { accountDetails ->
                    setState {
                        copy(
                            isLoading = false,
                            balance = accountDetails.balance.toString(),
                            isBalanceVisible = true
                        )
                    }
                    // Hide the balance after 5 seconds
                    delay(5_000L)
                    setState {
                        copy(
                            isBalanceVisible = false
                        )
                    }
                }
                .onFailure {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            it.message ?: "Error fetching balance"
                        )
                    )
                }
        }
    }
}

data class HomeScreenState(
    val username: String = "",
    val isLoading: Boolean = false,
    val isBalanceVisible: Boolean = false,
    val balance: String = ""
) : UiState


sealed class HomeScreenEvent : UiEvent {
    data object OnViewBalanceClick : HomeScreenEvent()
}

sealed class HomeScreenEffect : UiEffect {

}