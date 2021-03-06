package com.example.demo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo.ui.NetworkImageView
import com.example.demo.ui.lightGrey

@ExperimentalAnimationApi
@Composable
fun Tile(title: String, body: String, imageUrl: String? = null) {
    val bgColor = lightGrey
    var visible = remember { mutableStateOf(true) }
    AnimatedVisibility(visible = visible.value) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    top = 16.dp,
                    end = 24.dp,
                    bottom = 16.dp
                ),
            backgroundColor = bgColor,
            shape = RoundedCornerShape(5)
        )
        {

            Column(
                Modifier.clickable {
                    visible.value = !visible.value
                }
            ) {
                Row {
                    Column {
                        Text(title, color = Color.Black, modifier = Modifier.padding(8.dp))

                        Spacer(Modifier.height(16.dp))

                        Text(body, color = Color.Black, modifier = Modifier.padding(8.dp))
                    }
                    imageUrl?.let { url ->
                        NetworkImageView(url)
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true, name = "Tile")
@Composable
fun PreviewTile() {
    Tile("Title",
        "Text",
        "https://media.tenor.com/images/18afef4ee43bea42b408d8cb6a69a300/tenor.gif"
    )
}
