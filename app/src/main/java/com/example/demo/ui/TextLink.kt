package com.example.demo.ui

import android.app.Activity
import android.content.Context
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
import androidx.core.content.ContextCompat.startActivity
import com.example.demo.ColumnActivity
import com.example.demo.LazyActivity
import com.example.demo.SimpleActivity

/**
 * Clickable text with color
 */
@Composable
fun Activity.TextLink(text: String, type: ColumnActivity.ActivityType) {
    ClickableText(
        text = AnnotatedString(text, spanStyle = SpanStyle(color = Color.Blue)),
        modifier = Modifier.padding(0.dp).fillMaxWidth(),
        onClick = {
            val intent = Intent(this, activityType(type))
            startActivity(intent)
        }
    )
}

private fun activityType(type: ColumnActivity.ActivityType) =
    when(type) {
        ColumnActivity.ActivityType.SIMPLE -> SimpleActivity::class.java
        ColumnActivity.ActivityType.LAZY_COLUMN -> LazyActivity::class.java
    }