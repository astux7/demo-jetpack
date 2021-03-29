package com.example.demo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import kotlin.math.round

/**
 * Network image using CoilImage lib
 */
@Composable
fun NetworkImageView(url: String) {
    val modifier = Modifier
    val placeholderColor: Color = lightGrey

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(10)
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