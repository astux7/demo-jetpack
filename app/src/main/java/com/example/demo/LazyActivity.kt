package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


class LazyActivity : AppCompatActivity() {
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
    @Composable
    fun StoryList() {
        val modifier = Modifier
        val size = 100
        Box(modifier) {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(size) { item ->
                    Tile("U2 and $item items",
                            "Everybody shake your body \n" +
                                "Everybody shake your body \n" +
                                "Everybody shake your body \n" +
                                "Ufufuf, do it again ",
                            "https://media.tenor.com/images/18afef4ee43bea42b408d8cb6a69a300/tenor.gif"
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun previewStoryList() {
        StoryList()
    }
}