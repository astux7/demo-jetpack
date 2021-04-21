package com.example.demo

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.contextaware.ContextAwareHelper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.core.content.ContextCompat.startActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
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
        }

        composeTestRule.onAllNodes(hasText("Title", substring = true), useUnmergedTree = true).assertCountEquals(1)
        composeTestRule.onNodeWithText("Title",substring = true, useUnmergedTree = true).performClick()

        composeTestRule.onAllNodes(hasText("Ohh", substring = true), useUnmergedTree = true).assertCountEquals(1)
        assert(tileVisability.value == false)
    }

    @ExperimentalAnimationApi
    @Test
    fun TestWithAndroidView() {
        val mockedContext = ContextAwareHelper()
        mockedContext.clearAvailableContext()

        val context = composeTestRule.activity.applicationContext

        composeTestRule.setContent {
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Button(
                onClick = {
                    startActivity(context, intent, null)
                }
            ) {
                Text("AndroidView")
            }
        }

        composeTestRule.onNodeWithText(text = "AndroidView", substring = true, useUnmergedTree = true).performClick()
        Espresso.onView(withText("TEST")).check(matches(isDisplayed()))
    }
}