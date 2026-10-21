package com.example.mpazpro1.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Solo tema claro y sin color dinámico, para que la app siempre use la paleta de MPAZ PRO
private val MpazColorScheme = lightColorScheme(
    primary = MpazPurple,
    onPrimary = Color.White,
    primaryContainer = MpazPurpleLight,
    onPrimaryContainer = MpazPurpleDark,
    secondary = SubjectMatematica,
    background = MpazBackground,
    surface = Color.White,
    surfaceVariant = MpazPurpleLight,
    outline = Color(0xFFB9A8E0)
)

@Composable
fun MpazPro1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MpazColorScheme,
        typography = Typography,
        content = content
    )
}