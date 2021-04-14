package com.example.demo

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class TileTest {
    //requires emulator to run tests
    // createComposeRule() if you don't need access to the activityTestRule
    //  val composeTestRule = createComposeRule()
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @ExperimentalAnimationApi
    @Test
    fun showTile() {
        var tileVisability = mutableStateOf<Boolean>(true)
        composeTestRule.setContent {
            Surface {
                Tile("Title",
                    "Body",
                    "https://test.com/image.jpg",
                    display = tileVisability.value,
                    context = null,
                    {}
                )

            }
        }

    composeTestRule.onAllNodes(hasText("Title")).assertCountEquals(1)

    }
}