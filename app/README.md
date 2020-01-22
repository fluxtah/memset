The application has a single entry point in `MainActivity` where UI is served using Jetpack Compose `setContent { }` extension function and Memsets own solution to routing, the `Router`.

# Router Explained
The following example demonstrates the simplest thing we can do with Jetpack Compose, the classic Hello World.

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            hello()
        }
    }
    
    @Composable
    fun hello() {
        Text("Hello")
    }
}
```

`Router` allows us produce composables in a componentised manner by mapping URI's to an abstract type `Composer` with one function to imnplement `compose()`

In `MainActivity` we declare a `Router` and configure to with our mappings.

```
setContent {
    Router("https://memset.com/") {
        schemes("https", "http")
        hosts("memset.com", "www.memset.com")

        "/" composeWith { HomeScreenComposer() }
        "/designer" composeWith {
            CardDesignerScreenComposer()
        }
        ".*" composeTo { Text("404 Not Found") }

    }.startComposing(intent)
}
```

Givem the example above `Router("https://memset.com")` is defined as our starting point if no other URI is available (such as parsed from the incoming `Intent`, check the `:router` module for documentation on why URI'sa and incoming Intents).

The router allows us to specify which URI's are handled by your application and where those URI"s go (to which `Composer`).

Check out `MainActivity` for a real working example of this routing pattern.
