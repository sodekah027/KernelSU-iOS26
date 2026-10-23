package me.weishu.kernelsu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import me.weishu.kernelsu.ui.screens.KernelSUDashboard
import me.weishu.kernelsu.ui.theme.*

/**
 * KernelSU iOS 26 Liquid Glass Edition
 * Main Activity with Splash Screen and Fluid Animations
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display (iOS-like)
        enableEdgeToEdge()
        
        setContent {
            iOS26LiquidGlassTheme {
                var showSplash by remember { mutableStateOf(true) }
                
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000) // 2 second splash
                    showSplash = false
                }
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = iOS26BackgroundDark
                ) {
                    if (showSplash) {
                        SplashScreen()
                    } else {
                        KernelSUDashboard()
                    }
                }
            }
        }
    }
}

/**
 * Animated Splash Screen with iOS 26 Liquid Glass Effect
 */
@Composable
private fun SplashScreen() {
    // Animated scale for logo
    val infiniteTransition = rememberInfiniteTransition(label = "splash")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logo_scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logo_alpha"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        iOS26BackgroundDark,
                        iOS26SurfaceDark,
                        iOS26BackgroundDark
                    ),
                    radius = 1000f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated glow background
        Box(
            modifier = Modifier
                .size(300.dp)
                .scale(scale)
                .alpha(alpha * 0.3f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            iOS26Blue.copy(alpha = 0.4f),
                            iOS26Indigo.copy(alpha = 0.2f),
                            androidx.compose.ui.graphics.Color.Transparent
                        )
                    )
                )
        )
        
        // Logo placeholder (would be replaced with actual logo)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .alpha(alpha)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                iOS26Blue,
                                iOS26Indigo,
                                iOS26Purple
                            )
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp)
                    )
            )
            
            androidx.compose.material3.Text(
                text = "KernelSU",
                color = iOS26TextPrimary.copy(alpha = alpha),
                fontSize = 34.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            
            androidx.compose.material3.Text(
                text = "iOS 26 Liquid Glass Edition",
                color = iOS26TextSecondary.copy(alpha = alpha * 0.8f),
                fontSize = 15.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
        }
    }
}
