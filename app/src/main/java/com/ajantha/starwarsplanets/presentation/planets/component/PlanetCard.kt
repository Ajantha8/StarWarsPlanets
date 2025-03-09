package com.ajantha.starwarsplanets.presentation.planets.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajantha.starwarsplanets.R
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import com.ajantha.starwarsplanets.presentation.util.RemoteImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlanetCard(
    planet: PlanetModel,
    onPlanetClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.clickable {
            onPlanetClick()
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5 / 2f)
        ) {
            RemoteImage(
                imageUrl = planet.imageUrl,
                contentDescription = planet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(5 / 2f),
                width = 300,
                height = 150,
                placeholderResId = R.drawable.planet_placeholder
            )
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 0.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
                    .align(Alignment.TopStart),
                text = planet.name.uppercase(),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 0.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
                    .align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.climate)} : ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = planet.climate,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
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
fun PreviewPlanetCard() {
    PlanetCard(
        planet = planetSample,
        onPlanetClick = {

        })
}

val planetSample = PlanetModel(
    name = "Tatooine",
    climate = "arid",
    orbitalPeriod = "304",
    gravity = "1 standard",
    imageUrl = "https://picsum.photos/id/14/300/300"
)