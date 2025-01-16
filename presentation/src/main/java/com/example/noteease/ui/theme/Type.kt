package com.example.noteease.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.noteease.R

val SourceSansFamily = FontFamily(
    Font(R.font.source_sans3_bold),
    Font(R.font.source_sans3_medium),
    Font(R.font.source_sans3_regular),
    Font(R.font.source_sans3_semi_bold),
    Font(R.font.source_sans3_italic),
)

val AppTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
        ),
    titleLarge = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SourceSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)
