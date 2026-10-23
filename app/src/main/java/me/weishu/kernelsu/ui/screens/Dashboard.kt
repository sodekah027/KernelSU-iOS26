package me.weishu.kernelsu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.weishu.kernelsu.ui.components.*
import me.weishu.kernelsu.ui.theme.*

/**
 * KernelSU Main Dashboard Screen
 * iOS 26 Liquid Glass Design
 */

@Composable
fun KernelSUDashboard() {
    var selectedTab by remember { mutableStateOf(0) }
    var isScrolled by remember { mutableStateOf(false) }
    
    val tabItems = listOf(
        TabBarItem(Icons.Default.Home, "Home"),
        TabBarItem(Icons.Default.Apps, "Modules"),
        TabBarItem(Icons.Default.Security, "Root"),
        TabBarItem(Icons.Default.Settings, "Settings")
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(iOS26BackgroundDark)
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Navigation Bar
            LiquidGlassNavigationBar(
                title = when (selectedTab) {
                    0 -> "KernelSU"
                    1 -> "Modules"
                    2 -> "Root Access"
                    else -> "Settings"
                },
                isLargeTitle = !isScrolled,
                leftAction = {
                    LiquidGlassIconButton(
                        icon = Icons.Default.Menu,
                        onClick = { /* Open menu */ }
                    )
                },
                rightAction = {
                    LiquidGlassIconButton(
                        icon = Icons.Default.Notifications,
                        onClick = { /* Show notifications */ }
                    )
                }
            )
            
            // Content based on selected tab
            when (selectedTab) {
                0 -> HomeContent(
                    onScroll = { isScrolled = it },
                    modifier = Modifier.weight(1f)
                )
                1 -> ModulesContent(modifier = Modifier.weight(1f))
                2 -> RootAccessContent(modifier = Modifier.weight(1f))
                3 -> SettingsContent(modifier = Modifier.weight(1f))
            }
        }
        
        // Bottom Tab Bar
        LiquidGlassTabBar(
            items = tabItems,
            selectedIndex = selectedTab,
            onItemSelected = { selectedTab = it },
            isExpanded = !isScrolled,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun HomeContent(
    onScroll: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(bottom = 100.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Status Card
        item {
            StatusCard()
        }
        
        // Quick Actions
        item {
            Text(
                text = "Quick Actions",
                color = iOS26TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionCard(
                    icon = Icons.Default.Shield,
                    title = "Safe Mode",
                    subtitle = "Disabled",
                    color = iOS26Green,
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    icon = Icons.Default.Apps,
                    title = "Modules",
                    subtitle = "12 Active",
                    color = iOS26Blue,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        // Recent Apps
        item {
            Text(
                text = "Root Apps",
                color = iOS26TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        items(5) { index ->
            RootAppItem(
                appName = "App ${index + 1}",
                packageName = "com.example.app${index + 1}",
                isGranted = index % 2 == 0
            )
        }
    }
}

@Composable
private fun StatusCard() {
    LiquidGlassCard(
        cornerRadius = 24.dp,
        elevation = LiquidGlassElevation.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "KernelSU Status",
                        color = iOS26TextSecondary,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Working",
                        color = iOS26Green,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    iOS26Green.copy(alpha = 0.3f),
                                    iOS26Green.copy(alpha = 0.1f)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = iOS26Green,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            
            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(label = "Version", value = "v3.3.0")
                StatItem(label = "Kernel", value = "32601")
                StatItem(label = "Root Apps", value = "8")
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column {
        Text(
            text = value,
            color = iOS26TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = label,
            color = iOS26TextTertiary,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun QuickActionCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    LiquidGlassCard(
        cornerRadius = 20.dp,
        elevation = LiquidGlassElevation.Medium,
        modifier = modifier.height(120.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                color.copy(alpha = 0.25f),
                                color.copy(alpha = 0.1f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Column {
                Text(
                    text = title,
                    color = iOS26TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    color = iOS26TextSecondary,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun RootAppItem(
    appName: String,
    packageName: String,
    isGranted: Boolean
) {
    LiquidGlassCard(
        cornerRadius = 16.dp,
        elevation = LiquidGlassElevation.Low,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iOS26SurfaceVariantDark),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Android,
                        contentDescription = null,
                        tint = iOS26Blue
                    )
                }
                
                Column {
                    Text(
                        text = appName,
                        color = iOS26TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = packageName,
                        color = iOS26TextTertiary,
                        fontSize = 13.sp
                    )
                }
            }
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (isGranted) iOS26Green.copy(alpha = 0.15f)
                        else iOS26Red.copy(alpha = 0.15f)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (isGranted) "Granted" else "Denied",
                    color = if (isGranted) iOS26Green else iOS26Red,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ModulesContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Modules Screen",
            color = iOS26TextPrimary,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun RootAccessContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Root Access Screen",
            color = iOS26TextPrimary,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun SettingsContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings Screen",
            color = iOS26TextPrimary,
            fontSize = 22.sp
        )
    }
}
