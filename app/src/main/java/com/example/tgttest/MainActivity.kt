package com.example.tgttest

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tgt.TgtAnimation
import com.example.tgt.captureScreenshot
import com.example.tgttest.ui.theme.TgtTheme

class MainActivity : ComponentActivity() {

    var isDarkTheme by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TgtTheme(darkTheme = isDarkTheme) {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {

        var bitmap by remember { mutableStateOf<Bitmap?>(null) }
        val view = LocalView.current

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            var pointOffset by remember { mutableStateOf(Offset.Zero) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(0.1f)
                ) {
                    Box(modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            bitmap = captureScreenshot(view)
                            isDarkTheme = !isDarkTheme
                        }
                    ) {
                        Canvas(
                            Modifier
                                .width(35.dp)
                                .height(35.dp)
                                .onGloballyPositioned { layoutCoordinates ->
                                    val positionInRoot = layoutCoordinates.positionInRoot()
                                    val size = layoutCoordinates.size
                                    pointOffset = Offset(
                                        positionInRoot.x + size.width / 2f,
                                        positionInRoot.y + size.height / 2f
                                    )
                                }
                        ) {
                            drawCircle(
                                color = Color.Blue,
                                radius = 35f,
                                center = Offset(x = 50f, y = 50f)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Hello pavel", fontSize = 40.sp)
                    Text(text = "Hello Word", fontSize = 40.sp)
                }
            }

            bitmap?.let {
                TgtAnimation(it, pointOffset, switchToDark = isDarkTheme)
            }

        }
    }
}

