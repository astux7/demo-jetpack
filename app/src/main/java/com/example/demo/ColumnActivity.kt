package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import jp.wasabeef.composable.coil.CoilImage

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

            ImageView(url = "https://blazingminds.co.uk/wp-content/uploads/2013/09/minions.jpg")

            Spacer(Modifier.preferredHeight(16.dp))

            TextLink("Simple example", ActivityType.SIMPLE)

            Spacer(Modifier.preferredHeight(16.dp))

            TextLink("Lazy column example", ActivityType.LAZY_COLUMN)

        }
    }

    @Preview
    @Composable
    fun previewStoryList(){
        StoryList()
    }

    /**
     * Clickable text with color
     */
    @Composable
    fun TextLink(text: String, type: ActivityType) {
        ClickableText(
                text = AnnotatedString(text, spanStyle = SpanStyle(color = Color.Blue)),
                modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth(),
                onClick = {
                    val type = when(type) {
                        ActivityType.SIMPLE -> SimpleActivity::class.java
                        ActivityType.LAZY_COLUMN -> LazyActivity::class.java
                    }
                    val intent = Intent(this,type )
                    startActivity(intent)
                }
        )
    }

    /**
     * Network image using CoilImage lib
     */
    @Composable
    fun ImageView(url: String) {
        Surface(
                modifier = Modifier
                        .preferredSize(300.dp, 150.dp)
                        .padding(8.dp)
        ) {
            CoilImage(model = url)
        }
    }
}