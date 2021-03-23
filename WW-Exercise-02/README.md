# Exercise 2

To complete this exercise successfully, you need to do the following:

1. Clone the [android-exercises](https://github.com/WW-Digital/android-exercises) repo to your computer.
1. Import `WW-Exercise-02` project into Android Studio.
1. Fix any compilation errors. 
1. Read and implement the [User Stories](#user-stories) below.
1. Run the application in the Emulator or on an Android device.
1. Ensure the app displays correctly when the orientation changes.
1. Submit your completed updates to the android-exercises repo using a pull request.

You may implement any architecture or use any libraries you feel are necessary. We are looking to see what production quality code looks like to you. Please avoid using code generators like [http://androidstarters.com](http://androidstarters.com) as the team will not be able to evaluate your work.

### User Stories
1. Build an app with 2 screens: 
	- Screen 1: RecyclerView of contacts with a 'add' FAB
	- Screen 2: New Contact / Edit Contact screen with save button
1. As a user, I’d like to be able to add a phone number to the list. The process should include validating that the number is a US phone number. If it is, add it to the list. If it is NOT, show a snackbar explaining the error.
1. As a user, tapping on any of the contacts in the list should send you to the Phone Dialer, with that number ready to dial.
1. As a user, long pressing any of the contacts in the list should send you to the Edit Contact screen.
1. Add a unit test to test the phone number validation

**Bonus Points**

* Support dark mode in your design
* Display an empty view when there are no contacts
* Add additional features you think would be great. Some examples could be search, favorites, etc.