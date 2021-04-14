package com.example.demo

import androidx.activity.ComponentActivity
import androidx.activity.contextaware.ContextAwareHelper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
    fun showTileByState() {
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
        composeTestRule.onAllNodes(hasText("Title", substring = true), useUnmergedTree = true).assertCountEquals(1)
        composeTestRule.onRoot().onChild().assertHasClickAction()
    }

    @ExperimentalAnimationApi
    @Test
    fun hideTileByState() {
        var tileVisability = mutableStateOf<Boolean>(false)
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
        composeTestRule.onAllNodes(hasText("Title", substring = true), useUnmergedTree = true).assertCountEquals(0)
    }

    @ExperimentalAnimationApi
    @Test
    fun TileByClickShowsToastAndHides() {
        val mockedContext = ContextAwareHelper()
        mockedContext.clearAvailableContext()
        var tileVisability = mutableStateOf<Boolean>(true)
        mockedContext.addOnContextAvailableListener {

            composeTestRule.setContent {
                Surface {
                    Tile("Title",
                        "Body",
                        "https://test.com/image.jpg",
                        display = tileVisability.value,
                        context = it,
                        { }
                    )
                }
            }
            composeTestRule.onAllNodes(hasText("Title", substring = true), useUnmergedTree = true).assertCountEquals(1)
            composeTestRule.onNodeWithText("Title",substring = true, useUnmergedTree = true).performClick()

            composeTestRule.onAllNodes(hasText("Ohh", substring = true), useUnmergedTree = true).assertCountEquals(1)
            assert(tileVisability.value == false)
        }
    }


}