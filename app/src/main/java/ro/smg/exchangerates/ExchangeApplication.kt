package ro.smg.exchangerates

import androidx.multidex.MultiDexApplication
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.TAG_APP_STATE

class ExchangeApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // Register activities lifecycle
        registerActivityLifecycleCallbacks(ActivityLifeCycleObserver())
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.e(TAG_APP_STATE, "Low Memory")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.e(TAG_APP_STATE, "On Terminate")
    }
}