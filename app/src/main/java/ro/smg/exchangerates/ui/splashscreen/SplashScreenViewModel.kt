package ro.smg.exchangerates.ui.splashscreen

import android.app.Application
import android.os.Handler
import ro.smg.exchangerates.model.ApplicationState
import ro.smg.exchangerates.ui.BaseViewModel

class SplashScreenViewModel(app: Application) : BaseViewModel(app) {
    companion object {
        private const val SPLASH_SCREEN_DURATION_MS = 2000L
    }

    init {
       launchApp()
    }

    private fun launchApp() {
        Handler().postDelayed({
            val userAccount = _account.value

            if (userAccount == null) {
                // User is NOT logged in
                _appState.postValue(ApplicationState.AUTHENTICATION)

            } else {
                // User is logged in, so we can proceed to the Home Screen
                _appState.postValue(ApplicationState.HOME)
            }

        }, SPLASH_SCREEN_DURATION_MS)
    }

}
