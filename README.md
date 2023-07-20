<p align="center">
  <img src="https://github.com/erictoader/Cartographer/assets/99261319/e929edd0-d69b-434b-b434-79e26c2bab88" width="800">
</p><br>

[![](https://jitpack.io/v/erictoader/Cartographer.svg)](https://jitpack.io/#erictoader/Cartographer)

# 🗺️ Compose Cartographer

Compose Cartographer is a robust Jetpack Compose library, specifically designed to compliment the official Compose navigation by taking the complexity out of navigation within your Android applications.<br> 
It brings simplicity to the forefront, allowing developers to navigate through screens while passing any type of objects with an unparalleled level of ease.<br>
<br>
Cartographer utilizes Serialization and Reflection to pass objects and simplify developer experience to the maximum. 

## 🌟 Features
- 🌐 Seamless Navigation: Navigate between screens using Destinations, not routes.
- 📦 Type-Safe Argument Passing: Use arguments without the hassle. Pass objects between screens with ease.
- 🎨 Enhanced NavGraph: Declare your simple/nested NavGraph using Destination classes.
- 🔌 Customization: Provide your own JsonAdapters for complex objects.

## 🔧 Installation
Integrating Compose Cartographer into your project is a breeze. Just add the following line to your module `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.erictoader:Cartographer:1.0.0'
}
```

Note: You will need to add Jitpack to your project repositories. To do so, add the following line to your top-level `build.gradle` file:

```gradle
repositories {
        // other repositories
        maven("https://jitpack.io")
    }
```

## 🚀 How to Use

#### 1. Define Your Destinations 📍
Declare your destinations as classes that inherit from the `Destination` class:
In this example, we are creating a nested navigation of the Authentication feaure.

```kotlin
object Auth : Destination() {
    object Splash : Destination()
    object Login : Destination()
}
```

For destinations that require arguments, use data classes.

```kotlin
data class Player(
    val asset: Asset
) : Destination()
```

For destinations which require more than one instance of an argument type, use `@NamedArgument` annotation.<br>
This makes it easy to refer to them separately in code, and ensures everything works, even when renaming properties.
 
```kotlin
data class Details(
    @NamedArgument("primary") val primaryAsset: Asset,
    @NamedArgument("secondary") val secondaryAsset: Asset
) : Destination()
```

#### 2. Define your NavGraph 🌏
This is a nested NavGraph. Your NavGraph can be as simple or as complicated as you like.

```kotlin
NavHost(
    navController = navController,
    startDestination = Main::class
) {
    navigation(
        routeClass = Auth::class,
        startDestinationClass = Auth.Splash::class
    ) {
        composable(Auth.Splash::class) {
            // some composable
        }
        composable(Auth.Login::class) {
            // some other composable
        }
    }

    navigation(
        routeClass = Main::class,
        startDestinationClass = Main.Home::class
    ) {
        composable(Main.Home::class) {
            // some composable
        }
        composable(Main.Player::class) { backStackEntry ->
            // some other composable
        }
    }
}
```

#### 3. Navigate with Ease 🚀
Navigate to new screens and pass arguments with ease:

```kotlin
navController.navigate(
    Main.Details(
        primaryAsset = Asset(.. some complex data),
        secondaryAsset = Asset(.. some more complex data)
    )
)
```

#### 4. Retrieve Arguments 🎣
Retrieve arguments with type-safety, null-safety and name-safety:

```kotlin
composable(Main.Player::class) { backStackEntry ->
    val asset = backStackEntry.getArg<Asset>() // non-null asserted, perfect for Fail-Fast approach

    val maybeAsset = backStackEntry.getArgNullable<Asset>() // null-safe

    PlayerScreen(
        asset = asset
    )
}
```

Or, when using multiple arguments of the same type, refer them by their defined name:

```kotlin
val asset1 = backStackEntry.getArg<Asset>(named = "primary")
val asset2 = backStackEntry.getArg<Asset>(named = "secondary")
```

## 🛠️ Configuration
To handle complex object serialization, provide custom JsonAdapters using `CartographerConfig`.<br>
This should be done before navigating to any destination.

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CartographerConfig.apply {
            addJsonAdapterFactory(.. some Adapter factory)
            addJsonAdapter(.. some Json adapter)
        )

        setContent {
            // composable
        }
    }
}
```

## 📖 More information
The rest of the features, public API as well as the inner workings of Cartographer are detailed in the [Wiki pages](https://github.com/erictoader/Cartographer/wiki).

## 📝 License
This project is licensed under the MIT License.

## 🤔 Queries?
Got questions or suggestions? Don't hesitate to open an issue on this repository!
