package ro.smg.exchangerates.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedPreferencesHelper {
    companion object {
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedPreferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper =
            instance ?: synchronized(LOCK) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    // Note: we use String instead of Long type because the PreferenceScreen use only string arrays entries.

    // This method is called automatically from SettingsFragment using the SharedPreferences Jetpack lib
    fun saveRefreshPeriod(time: String) {
        prefs?.edit(commit = true) {
            putString(REFRESH_TIME_PREF, time)
        }
    }
    fun getRefreshPeriod(): Int = Integer.parseInt(prefs?.getString(REFRESH_TIME_PREF, DEFAULT_REFRESH_TIME_S)!!)

    // This method is called automatically from SettingsFragment using the SharedPreferences Jetpack lib
    fun saveBaseCurrency(currency: String) {
        prefs?.edit(commit = true) {
            putString(CURRENCY_PREF, currency)
        }
    }

    fun getBaseCurrency(): String = prefs?.getString(CURRENCY_PREF, DEFAULT_CURRENCY)!!
}