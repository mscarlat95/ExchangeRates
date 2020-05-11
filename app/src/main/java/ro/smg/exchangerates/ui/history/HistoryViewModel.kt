package ro.smg.exchangerates.ui.history

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ro.smg.exchangerates.model.data.RangedRates
import ro.smg.exchangerates.networking.ExchangeRatesApi
import ro.smg.exchangerates.ui.BaseViewModel
import ro.smg.exchangerates.util.*

class HistoryViewModel(app: Application) : BaseViewModel(app) {

    // Disposable
    private val disposable = CompositeDisposable()

    // Service
    private val api by lazy { ExchangeRatesApi.getApi() }

    // Exchange Rates Live Data
    val historyData: LiveData<Map<String, LineDataSet>> get() = _historyData
    protected val _historyData = MutableLiveData<Map<String, LineDataSet>>()

    // Date map Live Data
    val daysMap: LiveData<Map<Float, String>> get() = _daysMap
    protected val _daysMap = MutableLiveData<Map<Float, String>>()


    fun getExchangesHistory() {
        Log.d(TAG_CHART, "Get exchanges history")

        disposable.add(
            api.getExchangesByDate(
                getDateDaysAgo(CHART_MAX_DAYS),
                getCurrentDate(),
                CHART_AVAILABLE_SYMBOLS
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RangedRates>() {

                    override fun onSuccess(t: RangedRates) {
                        Log.d(TAG_CHART, "Get History Rates Successful: $t")

                        createHistoryList(t.rates!!.toSortedMap())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG_CHART, "Get History Rates Failed", e)
                        _error.postValue(Exception(e.message))
                    }
                })
        )
    }


    private fun createHistoryList(rates: Map<String, Map<String, Double>>) {
        // Save each currency into a data set
        val bgnSet = LineDataSet(arrayListOf<Entry>(), "BGN").apply { color = Color.RED }
        val ronSet = LineDataSet(arrayListOf<Entry>(), "RON").apply { color = Color.GREEN }
        val usdSet = LineDataSet(arrayListOf<Entry>(), "USD").apply { color = Color.GRAY }

        // Map each day to a Date
        val dateMap: MutableMap<Float, String> = HashMap()
        var currentDay: Float = 0f

        rates.forEach { (date, exchange) ->
            currentDay += 1f

            dateMap[currentDay] = date
            val bgn = exchange.getValue("BGN").toFloat()
            val ron = exchange.getValue("RON").toFloat()
            val usd = exchange.getValue("USD").toFloat()

            bgnSet.addEntry(Entry(currentDay, bgn))
            ronSet.addEntry(Entry(currentDay, ron))
            usdSet.addEntry(Entry(currentDay, usd))
        }

        // Update UI
        _historyData.postValue(HashMap<String, LineDataSet>().apply {
            put("BGN", bgnSet)
            put("RON", ronSet)
            put("USD", usdSet)
        })
        _daysMap.postValue(dateMap)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}