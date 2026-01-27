package com.app.compulynx.features.transaction.listTransactions

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.features.components.TransactionCard

@Composable
fun TransactionListScreen(
    transactionListScreenViewModel: TransactionListScreenViewModel = hiltViewModel()
) {
    val transactionListScreenState by transactionListScreenViewModel.state.collectAsStateWithLifecycle()
    TransactionListScreenContent(
        transactionListScreenState = transactionListScreenState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreenContent(
    modifier: Modifier = Modifier,
    transactionListScreenState: TransactionListScreenState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Transactions",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            AnimatedContent(transactionListScreenState) { listScreenState ->
                when (listScreenState) {
                    is TransactionListScreenState.Error -> {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                listScreenState.message,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    TransactionListScreenState.Loading -> {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    is TransactionListScreenState.Success -> {
                        TransactionsScreen(
                            modifier = Modifier.fillMaxSize(),
                            transactions = listScreenState.transactions
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(transactions) { transaction ->
            TransactionCard(transaction = transaction)
        }
    }
}