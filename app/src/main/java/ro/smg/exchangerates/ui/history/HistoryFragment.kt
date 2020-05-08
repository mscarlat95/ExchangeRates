package ro.smg.exchangerates.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.IHistoryScreen

/**
 * History Screen
 *
 * - Display last 10 days currency chart
 */
class HistoryFragment : BaseFragment(), IHistoryScreen {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        observeViewModel()

        // Views
        (activity as MainActivity).displayToolbar(true)
    }

    private fun observeViewModel() {

    }
}
