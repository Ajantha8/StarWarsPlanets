package com.ajantha.starwarsplanets

import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ajantha.starwarsplanets.presentation.planets.PlanetsScreen
import com.ajantha.starwarsplanets.ui.theme.StarWarsPlanetsTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun screen_header_displayed() = runTest {
        composeTestRule.activity.setContent {
            StarWarsPlanetsTheme {
                SharedTransitionLayout {
                    AnimatedVisibility(visible = true) {
                        PlanetsScreen(
                            animatedVisibilityScope = this,
                            onPlanetClick = {

                            })
                    }
                }
            }
        }

        composeTestRule.onNodeWithText("Star Wars Planets")
            .isDisplayed()
    }

}