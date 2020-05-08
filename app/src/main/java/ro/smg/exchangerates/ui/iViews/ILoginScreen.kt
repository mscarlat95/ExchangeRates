package ro.smg.exchangerates.ui.iViews

import android.content.Intent
import ro.smg.exchangerates.model.ApplicationState

interface ILoginScreen {
    /**
     * Skip authentication
     */
    fun skipSignIn()

    /**
     * Perform sign in using Google account
     */
    fun performGoogleSignIn()

    /**
     * Google Sign In Response Data
     */
    fun onSignInResult(data: Intent)


    /**
     * Navigate to HomeScreen
     */
    fun navigate(state: ApplicationState)
}