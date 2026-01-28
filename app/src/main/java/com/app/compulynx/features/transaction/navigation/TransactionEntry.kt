package com.app.compulynx.features.transaction.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.compulynx.features.transaction.localTransactions.LocalTransactionScreen
import com.app.compulynx.features.transaction.transactionList.TransactionListScreen
import com.app.compulynx.features.transaction.sendMoney.SendMoneyScreen
import kotlinx.serialization.Serializable

@Serializable
data object SendMoney : NavKey

@Serializable
data object TransactionList : NavKey

@Serializable
data object LocalTransaction : NavKey

fun EntryProviderScope<NavKey>.transactionEntry(backStack: NavBackStack<NavKey>) {
    entry<TransactionList> {
        TransactionListScreen(
            onBackClick = {
                backStack.removeLastOrNull()
            }
        )
    }

    entry<SendMoney> {
        SendMoneyScreen(
            onNavigateBack = {
                backStack.removeLastOrNull()
            }
        )
    }

    entry<LocalTransaction> {
        LocalTransactionScreen(
            onBackClick = {
                backStack.removeLastOrNull()
            }
        )
    }
}
