package com.ajantha.starwarsplanets.presentation.planets

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajantha.starwarsplanets.R
import com.ajantha.starwarsplanets.presentation.planets.component.PlanetCard
import com.ajantha.starwarsplanets.presentation.planets.component.PlanetsLoading
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import com.ajantha.starwarsplanets.presentation.planets.state.PlanetsUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PlanetsScreen(
    viewModel: PlanetsViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onPlanetClick: (planet: PlanetModel) -> Unit
) {
    val uiState: PlanetsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    // SnackBar for display error messages
    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            val result: SnackbarResult = snackBarHostState.showSnackbar(
                message = message,
                actionLabel = "OK",
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.Dismissed -> {
                    viewModel.resetErrorMessage()
                }
                SnackbarResult.ActionPerformed -> {
                    viewModel.resetErrorMessage()
                }
            }
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(
            hostState = snackBarHostState
        )
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    text = stringResource(id = R.string.app_name).uppercase(),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center
                    )
                )
            }

            if (uiState.loading || uiState.planets.isEmpty()) {
                PlanetsLoading() // Show loading until data received
            } else {
                items(
                    items = uiState.planets,
                    key = { planet -> planet.uuid }) { planet -> // Use key to prevent unnecessary recomposition
                    PlanetCard(
                        planet = planet,
                        animatedVisibilityScope = animatedVisibilityScope
                    ) {
                        onPlanetClick(planet)
                    }
                }
            }
        }
    }
}