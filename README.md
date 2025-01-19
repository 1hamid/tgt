## Tgt Library

**Tgt** is a library inspired by Telegram's smooth theme transition animation.

## Installation
Step 1: Add the Repository
Add JitPack to the root build.gradle of your project:
```kotlin
maven(url = "https://jitpack.io")
```

Step 2: Add the Dependency
Add the following dependency to your module’s build.gradle file:
```kotlin
dependencies {
    implementation ("com.github.1hamid:tgt:Tag")
}
```

## Usage
**1. Capturing Screenshots**

To capture a screenshot, use the captureScreenshot function:

```kotlin
val view = LocalView.current
val bitmap = captureScreenshot(view)
```

This function returns a **Bitmap** object representing the captured view.

**2. Theme Transition Animation**

Use the TgtAnimation composable function to create animations inspired by Telegram's theme transitions:

```kotlin
@Composable
fun TgtAnimation(
    bitmap: Bitmap,
    buttonOffset: Offset,
    switchToDark: Boolean
)
```

Parameters:
- **bitmap:** The captured bitmap used as a brush for the animation.
- **buttonOffset:** The position of the button where the animation starts.
- **switchToDark:** A boolean to toggle the theme transition between light and dark modes.
