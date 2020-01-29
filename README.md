# What is this?
<img align="right" src="https://github.com/fluxtah/memset/blob/master/gfx/screenshot-01.png" alt="Card Designer" />Memset (An abreviation of Memory Set) is a small WIP application to create memory cards (those cards with two sides where one side presents a problem and the other side the solution).  Memset is a working usage example of [Jetpack Compose](https://developer.android.com/jetpack/compose). It aims to follow the development of Jetpack Compose as well as ultimately provide a useful application for remembering stuff and a reference to those that are on the development journey that could do with a little help.

## A Modular Application
Memset is a modular application, it is divided into gradle modules along the most useful boundaries such as by Feature or API.

By splitting your app into modules it promotes reuse and eases parallel work streams. If you work in a large corporation with many developers who work on your product it is beneficial to ensure that parallel work can be carried out and having a modular approach to building applications can contribute toward this goal.

The following summary provides a brief explanation of what each module is responsible for and the role it plays in the application.

### Main Module
* [:app](../master/app) Contains a single activity `MainActivity` which is the main entry point into the Memset app

### Feature Modules
* **:carddesigner** Provides the card designer feature for creating and modifying cards
* **:home** A list of cards previously created

### API & Support Modules
* **:core** Provides common shared code everywhere including navigation constants, the app data model and DI configuration
* **:data** Provides a Room database implementation for storing cards locally
* **:ui-shared** To share common UI components (Composables) across other modules such as navigation ui
* **:router** A URI based routing API that allows URI mapping to `@Composable` functions effectively defining the applications navigation

<img align="center" src="https://github.com/fluxtah/memset/blob/master/gfx/screenshot-02.png" alt="Card Designer" />

## App Navigation in Memset
Memset uses a custom URI driven solution to navigation and encapsulates that solution into the `:router` module.

`Router` is an API that allows us produce composables by mapping URI's to a `@Composable` function block.

Looking at the following code example, in `MainActivity`we use Compose's `setContent` to create a `Router`. With the `Router` we can add a block of mappings specifying which URI paths map to which `@Composable` bblocks using the `composeTo` infix operator along with schemes and hosts to qualify URIs that can map to those paths.

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Router("https://memset.com/") {
                schemes("https", "http")
                hosts("memset.com", "www.memset.com")

                "/" composeTo { HomeScreen() }
                "/designer" composeTo { CardDesignerScreen() }
                ".*" composeTo { Text("404 Not Found") }

            }.startComposing(intent)
        }
    }
}
```

Givem the example above `Router("https://memset.com")` is defined as our starting point if no other URI is available (such as one parsed from the incoming optional `Intent` argument passed to `startComposing()` which kicks off the router. 

For inter-module navigation we add some convenience up in the `:core` module (which every module that wishes to navigate should include).

```
sealed class Destination(val uri: String) {
    object HomeScreen : Destination("/")
    object QuxScreen : Destination("/qux")
    object CardDesigner : Destination("/designer")
}

fun goto(destination: Destination) = goto(destination.uri)
```

With the sealed class `Destination` and its implementations we can strongly type our navigation destinations, along with a helper functions `goto` that we can use to perform the navigation.

```
FloatingActionButton(modifier = Spacing(16.dp), elevation = 6.dp) {
    IconButton(
        vectorResourceId = R.drawable.ic_add_inverted,
        onClick = { goto(Destination.CardDesigner) })
}
```

Check out [`MainActivity`](https://github.com/fluxtah/memset/blob/master/app/src/main/java/com/citizenwarwick/memset/MainActivity.kt) in the `:app` module for a real working example of this routing pattern.

TBC

