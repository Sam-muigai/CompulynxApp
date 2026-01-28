package com.app.compulynx.features.transaction.localTransactions

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.compulynx.domain.models.LocalTransaction
import com.app.compulynx.features.transaction.localTransactions.components.LocalTransactionCard

@Composable
fun LocalTransactionScreen(
    localTransactionViewModel: LocalTransactionsScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val localTransactions =
        localTransactionViewModel.localTransactions.collectAsStateWithLifecycle().value
    LocalTransactionScreenContent(
        onBackClick = onBackClick,
        localTransactions = localTransactions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalTransactionScreenContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    localTransactions: List<LocalTransaction>
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        "Local Transactions",
                        style = MaterialTheme.typography.titleLarge.copy(
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
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(localTransactions, key = { it.clientTransactionId }) { transaction ->
                LocalTransactionCard(localTransaction = transaction)
            }
        }
    }
}
