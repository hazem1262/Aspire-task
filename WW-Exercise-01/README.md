# Exercise 1

load some meals as a json response from api, display data in recycler view and show meal tags.

## Design Pattern
MVVV design pattern as recomended in docs [guide to app archticture](https://developer.android.com/jetpack/docs/guide) ,
with some modification to the network layer adding Kotlin couritines,
 also using koin for dependency injection

## Features

* Show a list of available meals.
* When click on an item, see a snackbar showing the filter associated with the item.
* Maintain the state while rotating the device.
* unit, integration and ui test.
* Support dark mode.
* Display a placeholder image if the URL is invalid.
* Display a loader while image is loading.
* Display an empty state view if the response is empty.
* Display an error message if the request fails or if there is no network.
* Poll to refresh functionality.

## Technology used

* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous programming
* [koin](https://insert-koin.io/) for dependency injection
* [databinding](https://developer.android.com/topic/libraries/data-binding) to bind data to ui
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [navigation](https://developer.android.com/guide/navigation/) to handle navigation between screens
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to bind data to ui, and survive among configuration changes
* [Mockk](https://mockk.io/) for unit and integration testing
* [Kaspresso](https://github.com/KasperskyLab/Kaspresso/releases) for ui testing
* [Retrofit] for networking

## Demo

<img src="https://github.com/WW-Digital/android-exercises-hazem1262/blob/exercise_one/WW-Exercise-01/results/normal_mode.gif" width="300" height="700">&emsp;&emsp;&emsp;<img src="https://github.com/WW-Digital/android-exercises-hazem1262/blob/exercise_one/WW-Exercise-01/results/dark_mode.gif" width="300"  height="700">
<br />
