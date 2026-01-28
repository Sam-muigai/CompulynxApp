package com.app.compulynx.features.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compulynx.R
import com.app.compulynx.core.ui.components.LynxOutlineButton

@Composable
fun AccountBalanceCard(
    modifier: Modifier = Modifier,
    isBalanceVisible: Boolean = true,
    balance: String,
    isBalanceLoading: Boolean = false,
    onCheckBalanceClick: () -> Unit,
) {
    val gradientBrush =
        Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.surfaceContainer,
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
            ),
        )
    Surface(
        modifier =
            modifier
                .fillMaxWidth(),
        color = Color.Transparent,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(brush = gradientBrush)
                    .padding(horizontal = 16.dp, vertical = 20.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.wallet),
                    contentDescription = null,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Your Account Balance",
                    style =
                        MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "kes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(Modifier.width(8.dp))
                    AnimatedContent(
                        targetState = isBalanceVisible,
                    ) { isBalanceVisible ->
                        if (isBalanceVisible) {
                            Text(
                                balance,
                                style =
                                    MaterialTheme.typography.headlineLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                            )
                        } else {
                            Text(
                                "****",
                                style =
                                    MaterialTheme.typography.headlineLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                            )
                        }
                    }
                }
                AnimatedContent(isBalanceLoading) { isLoading ->
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(32.dp))
                    } else {
                        LynxOutlineButton(
                            shape = MaterialTheme.shapes.medium,
                            onClick = onCheckBalanceClick,
                            content = {
                                Text("Check Balance")
                            },
                            enabled = !isBalanceVisible && !isBalanceLoading,
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}
