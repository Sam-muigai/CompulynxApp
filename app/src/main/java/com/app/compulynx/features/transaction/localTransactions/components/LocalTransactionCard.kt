package com.app.compulynx.features.transaction.localTransactions.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.compulynx.R
import com.app.compulynx.core.database.entities.SyncStatus
import com.app.compulynx.domain.models.LocalTransaction
import com.app.compulynx.utils.format

@Composable
fun LocalTransactionCard(
    modifier: Modifier = Modifier,
    localTransaction: LocalTransaction
) {
    val (syncColor, syncStatus) = when (localTransaction.syncStatus) {
        SyncStatus.QUEUED -> Pair(Color.Blue, "Queued")
        SyncStatus.SYNCING -> Pair(Color.Yellow, "In Progress")
        SyncStatus.SYNCED -> Pair(Color.Green, "Success")
        SyncStatus.FAILED -> Pair(Color.Red, "Failed")
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    modifier = Modifier.padding(12.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_upward),
                        modifier = Modifier.size(20.dp),
                        contentDescription = "transaction type",
                    )
                }
            }
            Column {
                Text(
                    text = "Sent to: ${localTransaction.accountTo}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = syncStatus,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = syncColor
                    )
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Ksh ${localTransaction.amount.format()}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Red,
                )
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}