package ro.smg.exchangerates.ui.splashscreen

import android.app.Application
import android.os.Handler
import ro.smg.exchangerates.model.app.ApplicationState
import ro.smg.exchangerates.ui.BaseViewModel

class SplashScreenViewModel(app: Application) : BaseViewModel(app) {

    fun launchApp() {
        val userAccount = _account.value

        if (userAccount == null) {
            // User is NOT logged in
            _appState.postValue(ApplicationState.AUTHENTICATION)
        } else {
            // User is logged in, so we can proceed to the Home Screen
            _appState.postValue(ApplicationState.HOME)
        }
    }

}
