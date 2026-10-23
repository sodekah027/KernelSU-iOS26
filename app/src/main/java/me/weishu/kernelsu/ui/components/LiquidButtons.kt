package me.weishu.kernelsu.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.weishu.kernelsu.ui.theme.*

/**
 * iOS 26 Liquid Glass Buttons
 * Multiple styles: Filled, Tinted, Plain with glass effects
 */

/**
 * Primary Liquid Glass Button
 * Filled with vibrant color and glass overlay
 */
@Composable
fun LiquidGlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    buttonStyle: LiquidButtonStyle = LiquidButtonStyle.Filled,
    size: LiquidButtonSize = LiquidButtonSize.Large
) {
    val haptic = LocalHapticFeedback.current
    var pressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    
    val interactionSource = remember { MutableInteractionSource() }
    
    val (backgroundColor, contentColor) = when (buttonStyle) {
        LiquidButtonStyle.Filled -> Pair(iOS26Blue, Color.White)
        LiquidButtonStyle.Tinted -> Pair(iOS26Blue.copy(alpha = 0.15f), iOS26Blue)
        LiquidButtonStyle.Plain -> Pair(Color.Transparent, iOS26Blue)
        LiquidButtonStyle.Destructive -> Pair(iOS26Red, Color.White)
        LiquidButtonStyle.Success -> Pair(iOS26Green, Color.White)
    }
    
    val cornerRadius = when (size) {
        LiquidButtonSize.Small -> 10.dp
        LiquidButtonSize.Medium -> 12.dp
        LiquidButtonSize.Large -> 14.dp
    }
    
    val verticalPadding = when (size) {
        LiquidButtonSize.Small -> 8.dp
        LiquidButtonSize.Medium -> 12.dp
        LiquidButtonSize.Large -> 16.dp
    }
    
    val horizontalPadding = when (size) {
        LiquidButtonSize.Small -> 16.dp
        LiquidButtonSize.Medium -> 20.dp
        LiquidButtonSize.Large -> 24.dp
    }
    
    Box(
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = if (buttonStyle == LiquidButtonStyle.Filled || buttonStyle == LiquidButtonStyle.Destructive || buttonStyle == LiquidButtonStyle.Success) {
                    Brush.verticalGradient(
                        colors = listOf(
                            backgroundColor.copy(alpha = 1.0f),
                            backgroundColor.copy(alpha = 0.9f)
                        )
                    )
                } else {
                    Brush.linearGradient(colors = listOf(backgroundColor, backgroundColor))
                }
            )
            .then(
                if (buttonStyle == LiquidButtonStyle.Tinted) {
                    Modifier.liquidGlass(cornerRadius = cornerRadius, elevation = LiquidGlassElevation.Low)
                } else Modifier
            )
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(color = contentColor),
                enabled = enabled,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    pressed = true
                    onClick()
                }
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = contentColor,
                fontSize = when (size) {
                    LiquidButtonSize.Small -> 15.sp
                    LiquidButtonSize.Medium -> 16.sp
                    LiquidButtonSize.Large -> 17.sp
                },
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    
    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(150)
            pressed = false
        }
    }
}

/**
 * Floating Action Button with Liquid Glass
 */
@Composable
fun LiquidGlassFAB(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = iOS26Blue,
    contentColor: Color = Color.White
) {
    val haptic = LocalHapticFeedback.current
    var pressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.92f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )
    
    Box(
        modifier = modifier
            .size(56.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        backgroundColor.copy(alpha = 1.0f),
                        backgroundColor.copy(alpha = 0.85f)
                    )
                )
            )
            .liquidGlassGlow(cornerRadius = 28.dp, glowColor = backgroundColor, glowIntensity = 0.4f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = contentColor),
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    pressed = true
                    onClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
    }
    
    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(150)
            pressed = false
        }
    }
}

/**
 * Icon Button with Glass Effect
 */
@Composable
fun LiquidGlassIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    iconSize: Dp = 20.dp,
    tint: Color = iOS26TextPrimary
) {
    val haptic = LocalHapticFeedback.current
    var pressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.88f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
            .liquidGlass(cornerRadius = size / 2, elevation = LiquidGlassElevation.Low)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, radius = size / 2),
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    pressed = true
                    onClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }
    
    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(100)
            pressed = false
        }
    }
}

/**
 * Button Style Variants
 */
enum class LiquidButtonStyle {
    Filled,      // Solid color with glass overlay
    Tinted,      // Translucent color with glass
    Plain,       // Text-only, no background
    Destructive, // Red/warning action
    Success      // Green/positive action
}

/**
 * Button Size Variants
 */
enum class LiquidButtonSize {
    Small,
    Medium,
    Large
}
