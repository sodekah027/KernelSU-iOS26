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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.weishu.kernelsu.ui.theme.*

/**
 * iOS 26 Tab Bar
 * Floating glass bar that shrinks on scroll with fluid animations
 */

@Composable
fun LiquidGlassTabBar(
    items: List<TabBarItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = true
) {
    val animatedHeight by animateDpAsState(
        targetValue = if (isExpanded) 80.dp else 60.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    
    val animatedPadding by animateDpAsState(
        targetValue = if (isExpanded) 12.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .padding(horizontal = 16.dp, vertical = animatedPadding)
            .liquidGlass(
                cornerRadius = 24.dp,
                elevation = LiquidGlassElevation.High,
                blurRadius = 20.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                TabBarItemView(
                    item = item,
                    isSelected = selectedIndex == index,
                    isExpanded = isExpanded,
                    onClick = { onItemSelected(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TabBarItemView(
    item: TabBarItem,
    isSelected: Boolean,
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    var pressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.85f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) iOS26Blue else iOS26TextSecondary,
        animationSpec = tween(300)
    )
    
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 24.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Column(
        modifier = modifier
            .fillMaxHeight()
            .scale(scale)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, radius = 40.dp),
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    pressed = true
                    onClick()
                }
            )
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon with liquid glass droplet background for selected item
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(iconSize + 8.dp)
        ) {
            if (isSelected && isExpanded) {
                // Liquid glass droplet effect
                Box(
                    modifier = Modifier
                        .size(iconSize + 16.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    iOS26Blue.copy(alpha = 0.15f),
                                    iOS26Blue.copy(alpha = 0.05f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }
            
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = iconColor,
                modifier = Modifier.size(iconSize)
            )
        }
        
        if (isExpanded) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.label,
                color = iconColor,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                maxLines = 1
            )
        }
    }
    
    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(100)
            pressed = false
        }
    }
}

/**
 * iOS 26 Navigation Bar
 * Liquid glass top bar with large title that collapses on scroll
 */

@Composable
fun LiquidGlassNavigationBar(
    title: String,
    modifier: Modifier = Modifier,
    isLargeTitle: Boolean = true,
    leftAction: (@Composable () -> Unit)? = null,
    rightAction: (@Composable () -> Unit)? = null
) {
    val animatedTitleSize by animateFloatAsState(
        targetValue = if (isLargeTitle) 34f else 17f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    val animatedHeight by animateDpAsState(
        targetValue = if (isLargeTitle) 120.dp else 56.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        iOS26BackgroundDark.copy(alpha = 0.95f),
                        iOS26BackgroundDark.copy(alpha = 0.8f),
                        Color.Transparent
                    )
                )
            )
            .drawBehind {
                // Bottom separator line
                drawLine(
                    color = iOS26BorderLight,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1f
                )
            }
    ) {
        // Actions row (always visible)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left action
            Box(modifier = Modifier.width(40.dp)) {
                leftAction?.invoke()
            }
            
            // Small title (visible when collapsed)
            if (!isLargeTitle) {
                Text(
                    text = title,
                    color = iOS26TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Spacer(modifier = Modifier.width(40.dp))
            }
            
            // Right action
            Box(modifier = Modifier.width(40.dp)) {
                rightAction?.invoke()
            }
        }
        
        // Large title (bottom aligned when expanded)
        if (isLargeTitle) {
            Text(
                text = title,
                color = iOS26TextPrimary,
                fontSize = animatedTitleSize.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

/**
 * Search Bar with Liquid Glass
 */
@Composable
fun LiquidGlassSearchBar(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .liquidGlass(
                cornerRadius = 12.dp,
                elevation = LiquidGlassElevation.Low
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                color = iOS26TextTertiary,
                fontSize = 17.sp
            )
        }
        // Text field would go here (simplified for example)
    }
}

/**
 * Tab Bar Item Data Class
 */
data class TabBarItem(
    val icon: ImageVector,
    val label: String,
    val route: String? = null
)
