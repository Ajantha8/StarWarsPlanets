package com.ajantha.starwarsplanets.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ajantha.starwarsplanets.presentation.planetdetail.PlanetDetailScreen
import com.ajantha.starwarsplanets.presentation.planets.PlanetsScreen
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Any
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<Planets> {
                PlanetsScreen(
                    animatedVisibilityScope = this@composable
                ) { planet ->
                    navController.navigate(
                        PlanetDetail(
                            planet = planet
                        )
                    )
                }
            }
            composable<PlanetDetail>(typeMap = mapOf(typeOf<PlanetModel>() to parcelableType<PlanetModel>())) {
                val planetDetail = it.toRoute<PlanetDetail>()
                PlanetDetailScreen(
                    planet = planetDetail.planet,
                    animatedVisibilityScope = this@composable,
                    onBackPressed = {
                        navController.popBackStack()
                    })
            }
        }
    }
}