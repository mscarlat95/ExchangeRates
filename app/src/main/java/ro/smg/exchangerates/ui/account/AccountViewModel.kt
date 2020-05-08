package ro.smg.exchangerates.ui.account

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import ro.smg.exchangerates.model.ApplicationState
import ro.smg.exchangerates.model.GenericError
import ro.smg.exchangerates.ui.BaseViewModel

class AccountViewModel(val app: Application) : BaseViewModel(app) {

    fun signOut(client: GoogleSignInClient) {
        // Check if there is any user logged in
        if (_account.value == null) {
            _error.postValue(GenericError())
            return
        }

        // Perform sign out and notify UI
        client.signOut().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _account.postValue(null)
                _appState.postValue(ApplicationState.AUTHENTICATION)
            } else
                _error.postValue(GenericError())
        }
    }

}
