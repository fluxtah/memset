# What is this?
<img align="right" src="https://github.com/fluxtah/memset/blob/master/gfx/screenshot-01.png" alt="Card Designer" />Memset (An abreviation of Memory Set) is a small WIP application to create memory cards (those cards with two sides where one side presents a problem and the other side the solution).  Memset is a working usage example of Jetpack Compose. It aims to follow the development of Jetpack Compose as well as ultimately provide a useful application for remembering stuff and a reference to those that are on the development journey that could do with a little help.

## A Modular Application
Memset is a modular application, it is divided into gradle modules along the most useful boundaries such as by Feature or API.

By splitting your app into modules it promotes reuse and eases parallel work streams. If you work in a large corporation with many developers who work on your product it is beneficial to ensure that parallel work can be carried out and having a modular approach to building applications can contribute toward this goal.

The following summary provides a brief explanation of what each module is responsible for and the role it plays in the application.

### Main Module
* [:app](../master/app/README.md) Contains a single activity `MainActivity` which is the main entry point into the Memset app

### Feature Modules
* *:carddesigner* Provides the card designer feature for creating and modifying cards
* *:home* Provides a landing experience (WIP will probably be a list of cards and a FAB to add cards)

### API & Support Modules
* *:core* Provides common shared code everywhere including navigation constants, the app data model and DI configuration
* *:data* Provides a Room database implementation for storing cards locally
* *:ui-shared* To share common UI components (Composables) across other modules such as navigation ui
* *:router* A URI based routing API that allows URI mapping to `@Composable` functions effectively defining the applications navigation

<img align="center" src="https://github.com/fluxtah/memset/blob/master/gfx/screenshot-02.png" alt="Card Designer" />
