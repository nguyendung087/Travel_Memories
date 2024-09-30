package com.example.travelmemories.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.travelmemories.R

val Caprasimo = FontFamily(
    Font(R.font.caprasimo_regular)
)

val Cabin = FontFamily(
    Font(R.font.cabin_condensed_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Caprasimo,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Caprasimo,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Caprasimo,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Caprasimo,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.W700,
        fontSize = 30.sp
    )
)