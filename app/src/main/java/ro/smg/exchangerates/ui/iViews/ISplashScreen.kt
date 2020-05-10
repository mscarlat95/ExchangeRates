package ro.smg.exchangerates.ui.iViews

import ro.smg.exchangerates.model.app.ApplicationState

interface ISplashScreen {
    /**
     * Navigate to Login screen or to the Home screen
     */
    fun navigate(state: ApplicationState)
}