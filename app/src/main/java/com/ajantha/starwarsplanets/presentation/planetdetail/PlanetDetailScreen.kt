package com.ajantha.starwarsplanets.presentation.planetdetail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajantha.starwarsplanets.R
import com.ajantha.starwarsplanets.presentation.core.component.AppTitleBar
import com.ajantha.starwarsplanets.presentation.planetdetail.component.DetailCard
import com.ajantha.starwarsplanets.presentation.planets.component.planetSample
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import com.ajantha.starwarsplanets.presentation.util.RemoteImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlanetDetailScreen(
    planet: PlanetModel,
    onBackPressed: () -> Unit
) {
    Box {
        Column {
            RemoteImage(
                imageUrl = planet.imageUrl,
                contentDescription = planet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f),
                width = 300,
                height = 300,
                placeholderResId = R.drawable.planet_placeholder
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = planet.name.uppercase(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                DetailCard(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    title = stringResource(R.string.orbital_period),
                    value = planet.orbitalPeriod
                )
                DetailCard(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    title = stringResource(R.string.gravity),
                    value = planet.gravity
                )
            }
        }
        AppTitleBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onBackPressed = onBackPressed
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
fun PreviewPlanetCard() {
    PlanetDetailScreen(
        planet = planetSample,
        onBackPressed = {

        })
}