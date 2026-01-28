package com.app.compulynx.features.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.compulynx.R
import com.app.compulynx.core.base.CollectOneTimeEvent
import com.app.compulynx.core.ui.components.LynxButton
import com.app.compulynx.core.ui.components.LynxOutlineButton
import com.app.compulynx.features.components.TransactionCard
import com.app.compulynx.features.home.components.AccountBalanceCard

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    onSendMoneyClick: () -> Unit,
    onViewAllTransactionsClick: () -> Unit,
    navigateToLogin: () -> Unit,
    onProfileClick: () -> Unit = {},
    onViewLocalTransactionsClick: () -> Unit = {},
) {
    val homeScreenState = homeScreenViewModel.state.collectAsStateWithLifecycle().value

    CollectOneTimeEvent(
        homeScreenViewModel.effect,
    ) { effect ->
        when (effect) {
            HomeScreenEffect.NavigateToLogin -> navigateToLogin()
        }
    }

    LaunchedEffect(Unit) {
        homeScreenViewModel.getMiniStatement()
    }

    HomeScreenContent(
        homeScreenState = homeScreenState,
        onEvent = homeScreenViewModel::handleEvent,
        onSendMoneyClick = onSendMoneyClick,
        onViewAllTransactionsClick = onViewAllTransactionsClick,
        onProfileClick = onProfileClick,
        onViewLocalTransactionsClick = onViewLocalTransactionsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeScreenState: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    onSendMoneyClick: () -> Unit,
    onViewAllTransactionsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onViewLocalTransactionsClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome ${homeScreenState.username} 👋",
                        style =
                            MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(HomeScreenEvent.OnLogoutClick)
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_logout),
                            contentDescription = null,
                        )
                    }
                    IconButton(
                        onClick = onProfileClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_account),
                            contentDescription = null,
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AnimatedContent(homeScreenState.isSyncing) { syncing ->
                        when (syncing) {
                            true -> {
                                Text(
                                    "Syncing ${homeScreenState.syncingTransactionCount} transactions",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }

                            false -> {
                                Text(
                                    "No pending transactions",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                    LynxOutlineButton(
                        shape = MaterialTheme.shapes.medium,
                        onClick = onViewLocalTransactionsClick,
                        content = {
                            Text("View Local Transactions")
                        },
                    )
                }
            }
            spacer()
            item {
                AccountBalanceCard(
                    isBalanceVisible = homeScreenState.isBalanceVisible,
                    balance = homeScreenState.balance,
                    isBalanceLoading = homeScreenState.isBalanceLoading,
                    onCheckBalanceClick = {
                        onEvent(HomeScreenEvent.OnViewBalanceClick)
                    },
                )
            }
            spacer()
            item {
                LynxButton(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("Send Money")
                            Spacer(Modifier.width(8.dp))
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(R.drawable.ic_send),
                                contentDescription = null,
                            )
                        }
                    },
                    onClick = onSendMoneyClick,
                )
            }
            spacer()
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Recent Transactions",
                        style =
                            MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                    )
                    TextButton(
                        onClick = onViewAllTransactionsClick,
                    ) {
                        Text(
                            "View All",
                            color = MaterialTheme.colorScheme.surfaceContainer,
                        )
                    }
                }
            }
            spacer()
            item {
                AnimatedVisibility(homeScreenState.isTransactionLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(32.dp))
                    }
                }
            }
            spacer()
            item {
                AnimatedVisibility(!homeScreenState.isTransactionLoading && homeScreenState.transactions.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            "No transactions yet. Click on send money to start transacting.",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            if (homeScreenState.transactions.isEmpty()) {
                spacer()
            }
            items(
                homeScreenState.transactions,
                key = { it.id },
            ) { transaction ->
                TransactionCard(transaction = transaction)
            }
        }
    }
}

private fun LazyListScope.spacer(height: Dp = 16.dp) {
    item {
        Spacer(Modifier.height(height))
    }
}
