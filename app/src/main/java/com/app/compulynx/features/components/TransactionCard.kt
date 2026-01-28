package com.app.compulynx.features.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.app.compulynx.domain.models.Transaction
import com.app.compulynx.utils.format

@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    val (iconRes, textColor) = when (transaction.debitOrCredit.lowercase()) {
        "credit" -> Pair(R.drawable.ic_arrow_downward, Color.Green)
        else -> Pair(R.drawable.ic_arrow_upward, Color.Red)
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
                        painter = painterResource(iconRes),
                        contentDescription = "transaction type",
                    )
                }
            }
            Column {
                Text(
                    text = transaction.transactionId,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = transaction.transactionType,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Ksh ${transaction.amount.format()}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor,
                )
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}