package com.example.gramaangana.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GreenPrimary = Color(0xFF2E7D32)
private val GreenLight = Color(0xFFA5D6A7)
private val Cream = Color(0xFFF8F5F0)

private val LightColors = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenLight,
    background = Cream,
    surface = Color.White
)

@Composable
fun GramaAnganaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}