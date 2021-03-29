package com.example.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo.ui.NetworkImageView
import com.example.demo.ui.TextLink

class ColumnActivity : AppCompatActivity() {
    enum class ActivityType {
        SIMPLE,
        LAZY_COLUMN,
    }

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
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Text("Jetpack Compose Column Example")

            NetworkImageView(url = "https://blazingminds.co.uk/wp-content/uploads/2013/09/minions.jpg")

            Spacer(Modifier.height(16.dp))

            TextLink("Simple example", ActivityType.SIMPLE)

            Spacer(Modifier.height(16.dp))

            TextLink("Lazy column example", ActivityType.LAZY_COLUMN)

        }
    }

    @Preview
    @Composable
    fun previewStoryList(){
        StoryList()
    }
}