package com.example.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo.ui.NetworkImageView
import com.example.demo.ui.TextLink
import com.example.demo.ui.components.AnnotatedClickableText
import com.example.demo.ui.typography

class ColumnActivity : AppCompatActivity() {
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
        // The Column function lets you stack elements vertically
        // As Column doesn't handle scrolling by default,
        // some items aren't visible as they're outside of the screen.
        // Add the verticalScroll modifier to enable scrolling within the Column
        Column (
            //  modifier lets you configure the layout
            modifier = Modifier.padding(16.dp)
        ){
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("Jetpack")
                }
                append(" Compose ")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append(" Column ")
                }
                append(" Example")
            })

            Spacer(Modifier.height(16.dp))

            AnnotatedClickableText()

            Spacer(Modifier.height(16.dp))

            Text(
                "This is example of 3 lines \n " +
                   "of text but max lines is 2 \n " +
                   "you should not see this line",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

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

enum class ActivityType {
    SIMPLE,
    LAZY_COLUMN,
}