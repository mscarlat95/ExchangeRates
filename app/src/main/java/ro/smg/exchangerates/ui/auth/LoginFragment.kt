package ro.smg.exchangerates.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.model.app.ApplicationState
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.ILoginScreen
import ro.smg.exchangerates.util.REQ_CODE_GOOGLE_SIGN_IN

/**
 * Login Screen
 *
 * - The use can enroll using his Google Account
 * - Or he can skip this step and proceed to the Live Exchange Rates instead
 */
class LoginFragment : BaseFragment(), ILoginScreen {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        observeViewModel()

        // Views
        (activity as MainActivity).displayToolbar(false)
        google_sign_in_btn.setOnClickListener { performGoogleSignIn() }
        skip_auth_btn.setOnClickListener { skipSignIn() }
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })
        viewModel.googleClient.observe(viewLifecycleOwner, Observer { client ->
            client?.let {
                (activity as MainActivity).startActivityForResult(
                    it.signInIntent,
                    REQ_CODE_GOOGLE_SIGN_IN
                )
            }
        })
        viewModel.account.observe(viewLifecycleOwner, Observer { account ->
            account?.let {
                navigate(ApplicationState.HOME)
            }
        })
    }

    override fun skipSignIn() = navigate(ApplicationState.HOME)

    override fun performGoogleSignIn() = viewModel.performSignIn()

    override fun onSignInResult(data: Intent) = viewModel.onSignInResult(data)

    override fun navigate(state: ApplicationState) {
        if (state == ApplicationState.HOME) {
            view?.let {
                Navigation.findNavController(it).navigate(
                    LoginFragmentDirections.navToHome()
                )
            }
        }
    }
}
