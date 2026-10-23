package me.weishu.kernelsu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.weishu.kernelsu.ui.theme.*

/**
 * iOS 26 Liquid Glass Modifiers
 * Advanced glassmorphism effects with depth and translucency
 */

/**
 * Primary Liquid Glass Effect
 * Creates the signature iOS 26 frosted glass appearance
 * 
 * @param cornerRadius Rounded corners (default: 16.dp for iOS feel)
 * @param borderWidth Border thickness (default: 1.dp)
 * @param blurRadius Backdrop blur amount (12-20dp recommended)
 */
@Composable
fun Modifier.liquidGlass(
    cornerRadius: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
    blurRadius: Dp = 16.dp,
    elevation: LiquidGlassElevation = LiquidGlassElevation.Medium
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    val backgroundColor = when (elevation) {
        LiquidGlassElevation.Low -> iOS26GlassLight
        LiquidGlassElevation.Medium -> iOS26GlassMedium
        LiquidGlassElevation.High -> iOS26GlassDark
    }
    
    return this
        .clip(shape)
        .background(backgroundColor, shape)
        .border(
            width = borderWidth,
            brush = Brush.verticalGradient(
                colors = listOf(
                    iOS26BorderMedium,
                    iOS26BorderLight
                )
            ),
            shape = shape
        )
        .blur(radius = 0.5.dp) // Subtle internal blur for depth
}

/**
 * Glowing Liquid Glass Effect
 * Adds luminous border glow for active/focused states
 */
@Composable
fun Modifier.liquidGlassGlow(
    cornerRadius: Dp = 16.dp,
    glowColor: Color = iOS26BorderGlow,
    glowIntensity: Float = 0.3f
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    
    return this
        .clip(shape)
        .background(iOS26GlassMedium, shape)
        .border(
            width = 1.5.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    glowColor.copy(alpha = glowIntensity),
                    iOS26BorderLight,
                    glowColor.copy(alpha = glowIntensity * 0.5f)
                )
            ),
            shape = shape
        )
        .drawBehind {
            // Outer glow effect
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        glowColor.copy(alpha = 0.2f),
                        Color.Transparent
                    ),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.maxDimension * 0.8f
                ),
                radius = size.maxDimension * 0.6f
            )
        }
}

/**
 * Gradient Liquid Glass
 * Glass effect with colorful gradient overlay
 */
@Composable
fun Modifier.liquidGlassGradient(
    cornerRadius: Dp = 16.dp,
    gradientColors: List<Color> = listOf(
        iOS26GradientStart,
        iOS26GradientMiddle,
        iOS26GradientEnd
    ),
    gradientAlpha: Float = 0.15f
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    
    return this
        .clip(shape)
        .background(
            brush = Brush.linearGradient(
                colors = gradientColors.map { it.copy(alpha = gradientAlpha) }
            ),
            shape = shape
        )
        .background(iOS26GlassMedium.copy(alpha = 0.7f), shape)
        .border(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(
                    iOS26BorderMedium,
                    Color.Transparent,
                    iOS26BorderLight
                )
            ),
            shape = shape
        )
}

/**
 * Shimmer Effect for Liquid Glass
 * Adds subtle light reflection animation effect
 */
@Composable
fun Modifier.liquidGlassShimmer(
    cornerRadius: Dp = 16.dp
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    
    return this
        .clip(shape)
        .background(iOS26GlassMedium, shape)
        .drawBehind {
            // Top shimmer highlight
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        iOS26Shimmer,
                        Color.Transparent
                    ),
                    startY = 0f,
                    endY = size.height * 0.3f
                )
            )
        }
        .border(
            width = 1.dp,
            color = iOS26BorderLight,
            shape = shape
        )
}

/**
 * Frosted Glass for Sheets and Modals
 * Heavy blur with high transparency
 */
@Composable
fun Modifier.frostGlass(
    cornerRadius: Dp = 24.dp
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    
    return this
        .clip(shape)
        .background(iOS26GlassDark.copy(alpha = 0.85f), shape)
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    iOS26BorderMedium,
                    iOS26BorderLight,
                    Color.Transparent
                )
            ),
            shape = shape
        )
}

/**
 * Liquid Glass Card Container
 * Complete card component with glass effect
 */
@Composable
fun LiquidGlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    elevation: LiquidGlassElevation = LiquidGlassElevation.Medium,
    padding: PaddingValues = PaddingValues(16.dp),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .liquidGlass(
                cornerRadius = cornerRadius,
                elevation = elevation
            )
            .padding(padding)
    ) {
        content()
    }
}

/**
 * Glass Elevation Levels
 */
enum class LiquidGlassElevation {
    Low,    // Most transparent, subtle
    Medium, // Balanced glass effect
    High    // Most opaque, strong presence
}

/**
 * Extension: Standard iOS 26 8pt Grid Spacing
 */
object iOS26Spacing {
    val xxs = 2.dp
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
    val xxxl = 48.dp
}
