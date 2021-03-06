package com.example.demo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage

/**
 * Network image using CoilImage lib
 */
@Composable
fun NetworkImageView(url: String) {
    val modifier = Modifier
    val placeholderColor: Color = Color.Blue

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(5)),
        backgroundColor = placeholderColor
    ) {
        CoilImage(
            data = url,
            modifier = modifier,
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            loading = {
                if (placeholderColor != null) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(placeholderColor)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun ImagePreview() {
    NetworkImageView(
        url = "https://blazingminds.co.uk/wp-content/uploads/2013/09/minions.jpg"
    )
}