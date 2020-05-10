package ro.smg.exchangerates.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.home.adapter.ExchangeListAdapter
import ro.smg.exchangerates.ui.iViews.IHomeScreen
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.SharedPreferencesHelper
import ro.smg.exchangerates.util.TAG_HOME

/**
 * Home Screen
 *
 * - Display all exchanges
 */
class HomeFragment : BaseFragment(), IHomeScreen {

    // ViewModel
    private lateinit var viewModel: HomeViewModel

    // Exchanges list adapter
    private val exchangesAdapter = ExchangeListAdapter(arrayListOf())

    // Periodic updates of exchange rates
    private val handler = Handler(Looper.getMainLooper())
    private val updateExchangesTask = object : Runnable {
        override fun run() {
            val period = SharedPreferencesHelper
                .invoke(activity as MainActivity).getRefreshPeriod() * 1000L

            progress_bar.visibility = View.VISIBLE
            refreshList()
            handler.postDelayed(this, period)
        }
    }

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
        rates_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = exchangesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        performPeriodicUpdates()
    }

    override fun onPause() {
        stopPeriodicUpdates()
        super.onPause()
    }

    override fun refreshList() = viewModel.refreshList()

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
            progress_bar.visibility = View.GONE
            displayError(error)
        })
        viewModel.exchangesList.observe(viewLifecycleOwner, Observer { list ->
            list?.let{
                progress_bar.visibility = View.GONE
                exchangesAdapter.updateList(it)
            }
        })
        viewModel.lastUpdate.observe(viewLifecycleOwner, Observer { date ->
            date?.let { last_update_edit_text.setText(it) }
        })
    }

    private fun performPeriodicUpdates() {
        Log.d(TAG_HOME, "Start periodic updates")
        handler.post(updateExchangesTask)
    }

    private fun stopPeriodicUpdates() {
        Log.d(TAG_HOME, "Stop periodic updates")
        handler.removeCallbacks(updateExchangesTask)
    }
}
