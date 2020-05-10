package ro.smg.exchangerates.ui.auth

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import ro.smg.exchangerates.ui.BaseViewModel
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.TAG_AUTH

class LoginViewModel(app: Application) : BaseViewModel(app) {

    // Google Client Live Data
    val googleClient: LiveData<GoogleSignInClient> get() = _googleClient
    protected val _googleClient = MutableLiveData<GoogleSignInClient>()

    fun performSignIn() {
        try {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(app, gso)

            _googleClient.postValue(client)
        } catch (e: Exception) {
            Log.e(TAG_AUTH, "Perform Sign In Failed", e)
            _error.postValue(e)
        }
    }

    fun onSignInResult(data: Intent) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            Log.d(TAG_AUTH, "Sign In Succeeded for ${account?.displayName}")
            _account.postValue(account)
        } catch (e: ApiException) {
            Log.e(TAG_AUTH, "Sign In Failed", e)
            _error.postValue(e)
        }
    }
}
