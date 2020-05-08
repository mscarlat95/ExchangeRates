package ro.smg.exchangerates.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.IHomeScreen

/**
 * Home Screen
 *
 * - Display all exchanges
 */
class HomeFragment : BaseFragment(), IHomeScreen {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        observeViewModel()

        // Views
        (activity as MainActivity).displayToolbar(true)
        (activity as MainActivity).hideAccountInfoInMenu()
    }

    private fun observeViewModel() {
        viewModel.account.observe(viewLifecycleOwner, Observer { account ->
            account?.let {
                (activity as MainActivity).displayAccountInfoInMenu(
                    account.displayName,
                    account.email
                )
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })
    }
}
