package com.example.tgt

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun TgtAnimation(
    bitmap: Bitmap,
    buttonOffset: Offset,
    switchToDark: Boolean
) {

    val imageBrush = ShaderBrush(ImageShader(bitmap.asImageBitmap()))
    var darkRadius by remember { mutableFloatStateOf(5000f) }
    var lightRadius by remember { mutableFloatStateOf(0f) }

    val darkAnimatedRadius by animateFloatAsState(
        targetValue = darkRadius,
        animationSpec = tween(durationMillis = 500), label = "",
    )

    val lightAnimatedRadius by animateFloatAsState(
        targetValue = lightRadius,
        animationSpec = tween(durationMillis = 500), label = "",
    )

    LaunchedEffect(switchToDark) {
        if (!switchToDark) {
            darkRadius = 0f
            lightRadius = 0f
        } else {
            darkRadius = 5000f
            lightRadius = 5000f
        }
    }

    Canvas(
        Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = 0.99f
            }) {

        if (!switchToDark) {
            drawCircle(
                brush = imageBrush,
                radius = darkAnimatedRadius,
                center = Offset(buttonOffset.x, buttonOffset.y),
            )
        } else {

            drawCircle(
                brush = imageBrush,
                radius = 5000f,
                center = Offset(buttonOffset.x, buttonOffset.y),
            )

            drawCircle(
                color = Color.Transparent,
                radius = lightAnimatedRadius,
                center = Offset(buttonOffset.x, buttonOffset.y),
                blendMode = BlendMode.Clear
            )
        }
    }

}