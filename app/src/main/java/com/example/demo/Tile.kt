package com.example.demo

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.wasabeef.composable.coil.CoilImage

class Tile {
     val bgColor = Color.LightGray

     @Composable
     fun Render(title: String, body: String, imageUrl: String? = null) {
            Surface(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                    start = 24.dp,
                                    top = 16.dp,
                                    end = 24.dp,
                                    bottom = 16.dp
                            ),
                    color = bgColor,
                    shape = RoundedCornerShape(10)
            ) {
                Column {
                    Row {
                        Column {
                            Text(title, color = Color.Black, modifier = Modifier.padding(8.dp))
                            Spacer(Modifier.preferredHeight(16.dp))
                            Text(body, color = Color.Black, modifier = Modifier.padding(8.dp))
                        }
                        imageUrl?.let {url ->
                            ImageView(url)
                        }
                    }
                }
            }
        }

    /**
     * Network image using CoilImage lib
     */
    @Composable
    fun ImageView(url: String) {
        Surface(
                modifier = Modifier
                        .preferredSize(300.dp, 150.dp)
                        .padding(8.dp),
                color = bgColor,
        ) {
            CoilImage(model = url)
        }
    }
}