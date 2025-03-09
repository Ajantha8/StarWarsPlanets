package com.ajantha.starwarsplanets.presentation.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.shimmerEffect(): Modifier = composed {
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.5f),
    )
    val transition = rememberInfiniteTransition(label = "transition")
    val shimmerAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                1000,
                easing = LinearEasing
            )
        ),
        label = "animation"
    )
    background(
        Brush.linearGradient(
            colors = colors,
            start = Offset.Zero,
            end = Offset(
                x = shimmerAnimation.value,
                y = shimmerAnimation.value * 2
            )
        )
    )
}