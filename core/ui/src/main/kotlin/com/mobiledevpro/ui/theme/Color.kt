package com.mobiledevpro.ui.theme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF006495)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFCBE6FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001E30)
val md_theme_light_secondary = Color(0xFF006781)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFBAEAFF)
val md_theme_light_onSecondaryContainer = Color(0xFF001F29)
val md_theme_light_tertiary = Color(0xFF006496)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFCCE5FF)
val md_theme_light_onTertiaryContainer = Color(0xFF001E31)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFF8FDFF)
val md_theme_light_onBackground = Color(0xFF001F25)
val md_theme_light_surface = Color(0xFFF8FDFF)
val md_theme_light_onSurface = Color(0xFF001F25)
val md_theme_light_surfaceVariant = Color(0xFFDEE3EA)
val md_theme_light_onSurfaceVariant = Color(0xFF41474D)
val md_theme_light_outline = Color(0xFF72787E)
val md_theme_light_inverseOnSurface = Color(0xFFD6F6FF)
val md_theme_light_inverseSurface = Color(0xFF00363F)
val md_theme_light_inversePrimary = Color(0xFF8FCDFF)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006495)
val md_theme_light_outlineVariant = Color(0xFFC1C7CE)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF8FCDFF)
val md_theme_dark_onPrimary = Color(0xFF003450)
val md_theme_dark_primaryContainer = Color(0xFF004B71)
val md_theme_dark_onPrimaryContainer = Color(0xFFCBE6FF)
val md_theme_dark_secondary = Color(0xFF5FD4FD)
val md_theme_dark_onSecondary = Color(0xFF003544)
val md_theme_dark_secondaryContainer = Color(0xFF004D62)
val md_theme_dark_onSecondaryContainer = Color(0xFFBAEAFF)
val md_theme_dark_tertiary = Color(0xFF91CDFF)
val md_theme_dark_onTertiary = Color(0xFF003350)
val md_theme_dark_tertiaryContainer = Color(0xFF004B72)
val md_theme_dark_onTertiaryContainer = Color(0xFFCCE5FF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF001F25)
val md_theme_dark_onBackground = Color(0xFFA6EEFF)
val md_theme_dark_surface = Color(0xFF001F25)
val md_theme_dark_onSurface = Color(0xFFA6EEFF)
val md_theme_dark_surfaceVariant = Color(0xFF41474D)
val md_theme_dark_onSurfaceVariant = Color(0xFFC1C7CE)
val md_theme_dark_outline = Color(0xFF8B9198)
val md_theme_dark_inverseOnSurface = Color(0xFF001F25)
val md_theme_dark_inverseSurface = Color(0xFFA6EEFF)
val md_theme_dark_inversePrimary = Color(0xFF006495)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF8FCDFF)
val md_theme_dark_outlineVariant = Color(0xFF41474D)
val md_theme_dark_scrim = Color(0xFF000000)

val seed = Color(0xFF06283D)


@get:Composable
val ColorScheme.topAppBarColor: Color
    get() = seed

@get:Composable
val ColorScheme.lightGreen: Color
    get() = Color(0x9932CD32)

@get:Composable
val ColorScheme.red: Color
    get() = Color(0x99F44336)