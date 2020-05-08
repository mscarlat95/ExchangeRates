package ro.smg.exchangerates.ui.iViews

import ro.smg.exchangerates.model.ApplicationState

interface IAccountScreen {
    /**
     * Navigate to Login Screen if the user press the Sign out button
     */
    fun navigate(state: ApplicationState)

    /**
     * Perform sign out
     */
    fun signOut()
}