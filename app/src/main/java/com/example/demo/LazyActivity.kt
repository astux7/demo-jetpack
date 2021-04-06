package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class LazyActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoryList()
        }
    }

    /**
     * To add style settings to the column use Modifier
     * Image my not be visible at first - need to restart AS & Emulator
     */
    @OptIn(ExperimentalAnimationApi::class)
    @ExperimentalAnimationApi
    @Composable
    fun StoryList() {
        val modifier = Modifier
        val size = 100

        Box(modifier) {
            val listState = rememberLazyListState()
            // renders only the visible items on screen,
            // allowing performance gains and doesn't need to scroll modifier
            LazyColumn(state = listState, modifier = Modifier.fillMaxWidth()) {
                items(size) { item ->
                    Tile("U2 and $item items",
                            "Everybody shake your body \n" +
                                "Everybody shake your body \n" +
                                "Everybody shake your body \n" +
                                "Ufufuf, do it again ",
                            "https://media.tenor.com/images/18afef4ee43bea42b408d8cb6a69a300/tenor.gif",
                            postAction = {}
                    )
                }
            }
            // https://www.youtube.com/watch?v=BhqPpUYJYeQ
            // Scroll to top https://developer.android.com/jetpack/compose/lists
//            ScrollToTopButton(
//                onClick = {
//                    coroutineScope.launch {
//                        // Animate scroll to the first item
//                        listState.animateScrollToItem(index = 0)
//                    }
//                }
//            )

        }
    }

    @ExperimentalAnimationApi
    @Preview
    @Composable
    fun previewStoryList() {
        StoryList()
    }
}