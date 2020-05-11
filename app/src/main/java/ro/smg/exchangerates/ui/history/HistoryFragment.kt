package ro.smg.exchangerates.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.LineData
import kotlinx.android.synthetic.main.fragment_history.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.history.date_formatter.XAxisFormatter
import ro.smg.exchangerates.ui.iViews.IHistoryScreen
import ro.smg.exchangerates.util.CHART_ANIMATION_DURATION_MS

/**
 * History Screen
 *
 * - Display last 10 days currency chart
 */
class HistoryFragment : BaseFragment(), IHistoryScreen {

    private lateinit var viewModel: HistoryViewModel

    @SuppressLint("SourceLockedOrientationActivity")
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
        exchanges_chart.apply {

            // Set chart description
            description.isEnabled = true
            description.textSize = 14f
            description.yOffset = -12f
            description.text = context.getString(R.string.chart_description)

            // Set chart legend
            legend.isEnabled = true
            legend.setDrawInside(false)
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        }

        // Update graph with the latest exchanges
        getLastExchangeRates()
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })
        viewModel.historyData.observe(viewLifecycleOwner, Observer { map ->
            // Update chart
            exchanges_chart.data = LineData(
                map.getValue("BGN"),
                map.getValue("RON"),
                map.getValue("USD")
            )
            exchanges_chart.animateX(CHART_ANIMATION_DURATION_MS)

            exchanges_chart.notifyDataSetChanged()
            exchanges_chart.invalidate()
        })
        viewModel.daysMap.observe(viewLifecycleOwner, Observer { map ->
            // Set date for x Axis
            exchanges_chart.xAxis.valueFormatter = XAxisFormatter(map)
        })
    }

    override fun getLastExchangeRates() = viewModel.getExchangesHistory()
}
