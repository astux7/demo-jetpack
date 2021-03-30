package com.example.demo.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import com.example.demo.ActivityType
import com.example.demo.LazyActivity
import com.example.demo.SimpleActivity

/**
 * Clickable text with color
 */
@Composable
fun Activity.TextLink(text: String, type: ActivityType) {
    ClickableText(
        text = AnnotatedString(text, spanStyle = SpanStyle(color = Color.Blue)),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
        onClick = {
            val intent = Intent(this, activityType(type))
            startActivity(intent)
        }
    )
}

private fun activityType(type: ActivityType) =
    when(type) {
        ActivityType.SIMPLE -> SimpleActivity::class.java
        ActivityType.LAZY_COLUMN -> LazyActivity::class.java
    }