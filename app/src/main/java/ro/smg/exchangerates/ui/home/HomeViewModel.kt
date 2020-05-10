package ro.smg.exchangerates.ui.home

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ro.smg.exchangerates.model.data.Currency
import ro.smg.exchangerates.model.data.Rate
import ro.smg.exchangerates.networking.ExchangeRatesApi
import ro.smg.exchangerates.ui.BaseViewModel
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.SharedPreferencesHelper
import ro.smg.exchangerates.util.TAG_HOME
import ro.smg.exchangerates.util.getCurrentDate
import java.lang.Exception

class HomeViewModel(app: Application) : BaseViewModel(app) {

    // Disposable
    private val disposable = CompositeDisposable()

    // Service
    private val api by lazy { ExchangeRatesApi.getApi() }

    // Exchange List Live Data
    val exchangesList: LiveData<List<Currency>> get() = _exchangesList
    protected val _exchangesList = MutableLiveData<List<Currency>>()

    // Exchange List Live Data
    val lastUpdate: LiveData<String> get() = _lastUpdate
    protected val _lastUpdate = MutableLiveData<String>()

    fun refreshList() {
        Log.d(TAG_HOME, "Get exchange rates")
        val currency = SharedPreferencesHelper.invoke(app).getBaseCurrency()

        disposable.add(
            api.getAllExchanges(currency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Rate>() {

                    override fun onSuccess(t: Rate) {
                        Log.d(TAG_HOME, "Get Exchange Rates Successful: $t")

                        // Update UI
                        _exchangesList.postValue(retrieveList(t.rates))
                        _lastUpdate.postValue(getCurrentDate())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG_HOME, "Get Exchange Rates Failed", e)
                        _error.postValue(Exception(e.message))
                    }
                })
        )
    }

    private fun retrieveList(rates: Map<String, Double>): List<Currency> {
        val list = mutableListOf<Currency>()
        rates.forEach { (key, value) ->
            Currency.getCurrencyByName(key).also {
                it.value = value
                list.add(it)
            }
        }

        return list
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

