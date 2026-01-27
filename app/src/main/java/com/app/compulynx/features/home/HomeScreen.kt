package com.app.compulynx.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.app.compulynx.features.home.components.AccountBalanceCard

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeScreenState = homeScreenViewModel.state.collectAsStateWithLifecycle().value
    HomeScreenContent(
        homeScreenState = homeScreenState,
        onEvent = homeScreenViewModel::handleEvent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeScreenState: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome ${homeScreenState.username} 👋",
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
            AccountBalanceCard(
                isBalanceVisible = homeScreenState.isBalanceVisible,
                balance = homeScreenState.balance,
                isBalanceLoading = homeScreenState.isLoading,
                onCheckBalanceClick = {
                    onEvent(HomeScreenEvent.OnViewBalanceClick)
                }
            )
        }
    }
}