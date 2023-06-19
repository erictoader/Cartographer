# 🗺️ Compose Cartographer

Compose Cartographer is a robust and highly optimized Jetpack Compose library, specifically designed to take the complexity out of navigation and screen transitions within your Android applications. 🚀 It brings simplicity to the forefront, allowing developers to glide through screens while passing objects with an unparalleled level of ease.

## 🌟 Features
- 🌐 Seamless Navigation: Navigate between screens with a single line of code.
- 📦 Bundle Passing: No more hassle when you need to pass objects between screens. Just bundle it up!
- 🎨 Enhanced Transitions: Screen transitions are smoother, enhancing user experience.

## 🔧 Installation
Integrating Compose Cartographer into your project is a breeze. Just add the following line to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.erictoader:compose-cartographer:0.1.0'
}
```

## 🚀 How to Use

### 1. Define Your Destinations 📍
Start by defining a set of destinations. Extend the `Destination` class as follows:

```kotlin
sealed class ScreenDestination(
    override val name: String,
    vararg argumentPaths: String
) : Destination(name, argumentPaths) {

    object Intro : ScreenDestination("intro") {
        object SplashScreen : ScreenDestination("splash")
        object AuthScreen : ScreenDestination("auth")
    }

    object Main : ScreenDestination("main") {
        object MoviesScreen : ScreenDestination("movies")
        object SeriesScreen : ScreenDestination("series")
        object SettingsScreen : ScreenDestination("settings")
        object DetailsScreen : ScreenDestination("details", argumentOf<AssetDetails>())
    }
}
```
### 2. Inherit from NavigationArgument 🏷️
For every object that you want to pass as an argument, ensure they inherit from `NavigationArgument`.

```kotlin
data class AssetDetails(val id: String, val title: String) : NavigationArgument
```
### 3. Navigate with Ease 🚀
Once you've got your destinations set and arguments ready, navigating to a new screen and passing an object is a breeze.

```kotlin
navController.navigate(ScreenDestination.Main.DetailsScreen, assetDetails)
```
Here, `assetDetails` is an `AssetDetails` object that inherits from `NavigationArgument`.

## 📝 License
This project uses the MIT license.

## 🤔 Queries?
Got questions or suggestions? Don't hesitate to open an issue on this repository!
