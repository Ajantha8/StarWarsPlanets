package com.ajantha.starwarsplanets.presentation.planetdetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DetailCard(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    title: String,
    value: String
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = textAlign
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                textAlign = textAlign
            )
        )
    }
}