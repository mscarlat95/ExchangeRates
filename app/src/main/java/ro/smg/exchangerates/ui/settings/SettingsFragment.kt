package ro.smg.exchangerates.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.ISettingsScreen

/**
 * Settings Screen
 *
 * - Adjust REFRESH time (e.g. 3sec, 5sec, 15sec)
 * - Change Currency (e.g. EURO, RON etc.)
 */
class SettingsFragment : BaseFragment(), ISettingsScreen {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        observeViewModel()

        // Views
        (activity as MainActivity).displayToolbar(true)
    }

    private fun observeViewModel() {

    }
}
