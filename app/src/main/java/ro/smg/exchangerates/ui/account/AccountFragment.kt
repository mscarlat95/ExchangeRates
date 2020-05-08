package ro.smg.exchangerates.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.account_fragment.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.databinding.AccountFragmentBinding
import ro.smg.exchangerates.model.ApplicationState
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.IAccountScreen

/**
 * Account Screen
 *
 * - Display user information (name, email, picture)
 * - The user can perform Sign out
 */
class AccountFragment : BaseFragment(), IAccountScreen {

    private lateinit var viewModel: AccountViewModel
    private lateinit var dataBinding: AccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.account_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        observeViewModel()

        // Views
        sign_out_btn.setOnClickListener { signOut() }
    }

    private fun observeViewModel() {
        viewModel.account.observe(viewLifecycleOwner, Observer { account ->
            dataBinding.account = account
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })
        viewModel.appState.observe(viewLifecycleOwner, Observer { state ->
            navigate(state)
        })
    }

    override fun navigate(state: ApplicationState) {
        if (state == ApplicationState.AUTHENTICATION) {
            view?.let {
                Navigation.findNavController(it)
                    .navigate(AccountFragmentDirections.navToLogin())
            }
        }
    }

    override fun signOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(activity as MainActivity, gso)

        viewModel.signOut(client)
    }
}
