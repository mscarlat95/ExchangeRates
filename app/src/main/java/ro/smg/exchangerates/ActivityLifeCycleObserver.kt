package ro.smg.exchangerates

import android.app.Activity
import android.app.Application
import android.os.Bundle
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.TAG_APP_STATE

class ActivityLifeCycleObserver : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {
        Log.i(TAG_APP_STATE, "${activity.localClassName} started")
    }

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityStopped(activity: Activity) {
        Log.i(TAG_APP_STATE, "${activity.localClassName} stopped")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityResumed(activity: Activity) {}
}