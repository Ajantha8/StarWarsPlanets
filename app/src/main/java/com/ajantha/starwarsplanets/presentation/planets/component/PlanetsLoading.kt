package com.ajantha.starwarsplanets.presentation.planets.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajantha.starwarsplanets.presentation.util.shimmerEffect

fun LazyListScope.PlanetsLoading() {
    items(5) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5 / 2f)
                .shimmerEffect()
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    // showSystemUi = true,
    showBackground = true,
    device = "id:pixel_7",
    name = "pixel_7"
)
@Composable
fun PreviewPlanetsLoading() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        PlanetsLoading()
    }
}