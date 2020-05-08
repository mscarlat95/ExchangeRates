package ro.smg.exchangerates.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ro.smg.exchangerates.model.ApplicationState
import ro.smg.exchangerates.model.GenericError
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.TAG_AUTH


/**
 * Base ViewModel features
 *
 * It contains observable data:
 * - application state
 * - application errors
 * - account information
 */
abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    // App state Live Data
    val appState: LiveData<ApplicationState> get() = _appState
    protected val _appState = MutableLiveData<ApplicationState>()

    // Account Live Data
    val account: LiveData<GoogleSignInAccount> get() = _account
    protected val _account = MutableLiveData<GoogleSignInAccount>()

    // Error Live Data
    val error: LiveData<GenericError> get() = _error
    protected val _error = MutableLiveData<GenericError>()

    init {
        // Check if the user is logged in
        val userAccount = GoogleSignIn.getLastSignedInAccount(app)
        Log.d(TAG_AUTH, "Current account: $userAccount")

        userAccount?.let {
            _account.postValue(it)
        }
    }

}