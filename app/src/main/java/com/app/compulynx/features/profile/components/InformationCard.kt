package com.app.compulynx.features.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InformationCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, fontWeight = FontWeight.Medium)
            Text(
                value,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                    ),
            )
        }
    }
}
