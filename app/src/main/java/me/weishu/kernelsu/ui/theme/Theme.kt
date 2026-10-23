package me.weishu.kernelsu.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * iOS 26 Liquid Glass Dark Theme
 * Inspired by Apple's revolutionary design system
 */

private val iOS26DarkColorScheme = darkColorScheme(
    // Primary Colors
    primary = iOS26Blue,
    onPrimary = Color.White,
    primaryContainer = iOS26Indigo,
    onPrimaryContainer = Color.White,
    
    // Secondary Colors
    secondary = iOS26Indigo,
    onSecondary = Color.White,
    secondaryContainer = iOS26Purple,
    onSecondaryContainer = Color.White,
    
    // Tertiary Colors
    tertiary = iOS26Teal,
    onTertiary = Color.White,
    tertiaryContainer = iOS26Cyan,
    onTertiaryContainer = Color.White,
    
    // Background & Surface
    background = iOS26BackgroundDark,
    onBackground = iOS26TextPrimary,
    surface = iOS26SurfaceDark,
    onSurface = iOS26TextPrimary,
    surfaceVariant = iOS26SurfaceVariantDark,
    onSurfaceVariant = iOS26TextSecondary,
    
    // Surface Containers (for glass effects)
    surfaceContainer = iOS26GlassLight,
    surfaceContainerHigh = iOS26GlassMedium,
    surfaceContainerHighest = iOS26GlassDark,
    
    // Error States
    error = iOS26Red,
    onError = Color.White,
    errorContainer = Color(0xFF4D1F1F),
    onErrorContainer = iOS26Red,
    
    // Outline & Border
    outline = iOS26BorderLight,
    outlineVariant = iOS26BorderMedium,
    
    // Scrim
    scrim = iOS26Scrim,
    
    // Inverse Colors (for high contrast elements)
    inverseSurface = Color.White,
    inverseOnSurface = iOS26BackgroundDark,
    inversePrimary = Color(0xFF006EE6),
    
    // Surface Tint (for elevation effects)
    surfaceTint = iOS26Shimmer
)

@Composable
fun iOS26LiquidGlassTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    
    // Configure system bars for iOS-like appearance
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = false,
            navigationBarContrastEnforced = false
        )
    }
    
    MaterialTheme(
        colorScheme = iOS26DarkColorScheme,
        typography = iOS26Typography,
        content = content
    )
}

/**
 * Extended color palette for specific iOS 26 use cases
 */
object iOS26ExtendedColors {
    val statusOnline = iOS26StatusOnline
    val statusWarning = iOS26StatusWarning
    val statusError = iOS26StatusError
    val statusInfo = iOS26StatusInfo
    
    val gradientStart = iOS26GradientStart
    val gradientMiddle = iOS26GradientMiddle
    val gradientEnd = iOS26GradientEnd
    
    val shimmer = iOS26Shimmer
    val highlight = iOS26Highlight
    val borderGlow = iOS26BorderGlow
    
    val shadow = iOS26Shadow
    val shadowSubtle = iOS26ShadowSubtle
}
