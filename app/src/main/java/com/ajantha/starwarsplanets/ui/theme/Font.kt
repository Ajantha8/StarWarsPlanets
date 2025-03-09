package com.ajantha.starwarsplanets.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.ajantha.starwarsplanets.R

val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontRoboto = GoogleFont("Roboto")

val fontFamilyRoboto = FontFamily(
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Thin
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.ExtraLight
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Light
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Bold
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.ExtraBold
    ),
    Font(
        googleFont = fontRoboto,
        fontProvider = fontProvider,
        weight = FontWeight.Black
    )
)