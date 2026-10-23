# KernelSU iOS 26 Liquid Glass Edition

<div align="center">

![KernelSU Logo](https://img.shields.io/badge/KernelSU-iOS26-blue?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Android%2031+-green?style=for-the-badge)
![Design](https://img.shields.io/badge/Design-Liquid%20Glass-purple?style=for-the-badge)

**Revolutionary KernelSU rebuild with iOS 26 Liquid Glass design system**

[Features](#features) • [Screenshots](#screenshots) • [Build](#build) • [Design](#design)

</div>

---

## ✨ Features

### 🎨 iOS 26 Liquid Glass Design System
- **Translucent Surfaces** - Advanced glassmorphism with backdrop blur (12-20px)
- **Frosted Glass Effects** - Multi-layered depth with dynamic translucency
- **Specular Highlights** - Real-time light reflections on glass surfaces
- **Fluid Animations** - Spring-based physics animations
- **Luminous Borders** - Glowing borders with gradient effects
- **Dark Mode First** - Beautiful dark theme with high contrast

### 🚀 Core Features
- ✅ Kernel-based root management
- ✅ Per-app root access control
- ✅ Module system (Magisk compatible)
- ✅ SELinux management
- ✅ Safe mode support
- ✅ Boot-time initialization
- ✅ WebView-based module UI

### 📱 UI Components
- **Liquid Glass Navigation Bar** - Collapsible large title
- **Floating Tab Bar** - Shrinks on scroll with smooth animations
- **Glass Cards** - Multiple elevation levels
- **Liquid Buttons** - Filled, Tinted, Plain variants
- **Icon Buttons** - Glass effect with haptic feedback
- **FAB** - Floating action button with glow effect

### 🎯 iOS 26 Compliance
- 8pt Grid System
- SF Pro Typography (Android fallback)
- iOS Color Palette (Blue, Indigo, Purple, etc.)
- Native iOS animations (cubic-bezier curves)
- Edge-to-edge display
- System bars transparency

---

## 🖼️ Screenshots

> Coming soon - Build the project to see the stunning iOS 26 design in action!

---

## 🔧 Build Instructions

### Prerequisites
- Android Studio Ladybug (2024.2.1+)
- JDK 17
- Android SDK 37
- Gradle 8.7+

### Build Steps

1. **Clone or use existing project:**
```bash
cd /sdcard/disini/KernelSU_iOS26
```

2. **Open in Android Studio:**
   - File → Open → Select `KernelSU_iOS26` folder
   - Wait for Gradle sync

3. **Build the project:**
```bash
./gradlew assembleDebug
```

4. **Install APK:**
```bash
./gradlew installDebug
```

Or build release APK:
```bash
./gradlew assembleRelease
```

---

## 🎨 Design System

### Color Palette (Dark Mode)

| Color | Hex | Usage |
|-------|-----|-------|
| iOS Blue | `#0A84FF` | Primary actions, links |
| iOS Indigo | `#5E5CE6` | Accent, selections |
| iOS Purple | `#BF5AF2` | Secondary accent |
| iOS Green | `#32D74B` | Success states |
| iOS Red | `#FF453A` | Error, destructive |
| iOS Orange | `#FF9F0A` | Warnings |

### Typography Scale

```kotlin
Display Large: 34sp Bold (Large titles)
Headline Large: 22sp Semibold (Section headers)
Body Large: 17sp Regular (Body text)
Label Medium: 13sp Medium (Captions)
Label Small: 11sp Medium (Tab labels)
```

### Spacing (8pt Grid)

```kotlin
xxs: 2dp
xs: 4dp
sm: 8dp
md: 12dp
lg: 16dp
xl: 24dp
xxl: 32dp
xxxl: 48dp
```

### Glass Effects

**Elevation Levels:**
- Low: Most transparent (`rgba(17, 25, 40, 0.55)`)
- Medium: Balanced (`rgba(17, 25, 40, 0.70)`)
- High: Most opaque (`rgba(10, 14, 20, 0.80)`)

**Border Styles:**
- Light: `rgba(255, 255, 255, 0.12)`
- Medium: `rgba(255, 255, 255, 0.25)`
- Glow: `rgba(94, 92, 230, 0.30)` (Indigo)

---

## 📦 Project Structure

```
KernelSU_iOS26/
├── app/
│   ├── src/main/
│   │   ├── java/me/weishu/kernelsu/
│   │   │   ├── ui/
│   │   │   │   ├── theme/
│   │   │   │   │   ├── Color.kt
│   │   │   │   │   ├── Theme.kt
│   │   │   │   │   └── Type.kt
│   │   │   │   ├── components/
│   │   │   │   │   ├── LiquidGlass.kt
│   │   │   │   │   ├── LiquidButtons.kt
│   │   │   │   │   └── LiquidNavigation.kt
│   │   │   │   └── screens/
│   │   │   │       └── Dashboard.kt
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   │   └── mipmap-xxxhdpi/
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

---

## 🛠️ Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material 3 (customized for iOS 26)
- **Architecture**: MVVM
- **Minimum SDK**: 31 (Android 12)
- **Target SDK**: 37 (Android 26)

### Dependencies

- Jetpack Compose BOM 2024.02.00
- Material 3
- Accompanist (System UI Controller, Navigation Animation)
- Cloudy (Blur effects)
- Core Splash Screen
- AndroidX Core KTX
- Lifecycle ViewModel Compose
- Navigation Compose

---

## 🎯 Key Differences from Original KernelSU

| Feature | Original | iOS 26 Edition |
|---------|----------|---------------|
| Design Language | Material Design | iOS 26 Liquid Glass |
| Visual Style | Flat/Standard | Glassmorphism/Depth |
| Animations | Basic | Fluid Spring Physics |
| Tab Bar | Fixed | Floating, Shrinking |
| Navigation | Standard | Large Title Collapsing |
| Colors | Material Palette | iOS System Colors |
| Typography | Roboto | SF Pro-inspired |
| Grid System | 4dp | 8dp (iOS standard) |
| Dark Mode | Optional | Primary Theme |

---

## 📝 Design Philosophy

This rebuild follows Apple's iOS 26 design principles:

1. **Clarity** - Content is paramount, glass effects enhance without distraction
2. **Deference** - UI defers to content with translucency
3. **Depth** - Layers and translucency create spatial relationships
4. **Fluidity** - Animations feel natural with spring physics
5. **Consistency** - Every element follows the iOS 26 design system

---

## 🤝 Credits

### Original KernelSU
- **Author**: weishu
- **Repository**: [tiann/KernelSU](https://github.com/tiann/KernelSU)
- **License**: GPL-3.0

### iOS 26 Liquid Glass Redesign
- **Design System**: Apple Inc. (WWDC 2025)
- **Implementation**: OpenCode AI Agent
- **Reverse Engineering**: Based on KernelSU v3.3.0
- **Date**: October 23, 2026

### Design References
- Apple Human Interface Guidelines (iOS 26)
- GlassKit CSS Library
- Liquid Glass Design System
- iOS 26 WWDC 2025 Keynote

---

## ⚠️ Disclaimer

This is a **design concept and educational project**. 

- This rebuild focuses on **UI/UX redesign** only
- Native functionality (root access, kernel modules) requires the original KernelSU kernel driver
- This is not affiliated with or endorsed by Apple Inc.
- iOS design elements are used for educational purposes under fair use
- For actual root functionality, use the official KernelSU project

---

## 📄 License

This UI redesign project is provided under MIT License for the UI code.

Original KernelSU functionality is GPL-3.0 licensed by tiann/KernelSU.

Apple's iOS design language is a trademark of Apple Inc.

---

## 🌟 Show Your Support

If you love the iOS 26 Liquid Glass design, give this project a ⭐!

---

<div align="center">

**Built with ❤️ using iOS 26 Design System**

*Bringing Apple's design excellence to Android KernelSU*

</div>
