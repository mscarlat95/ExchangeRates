package ro.smg.exchangerates.ui.settings

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.ISettingsScreen
import ro.smg.exchangerates.util.Log
import ro.smg.exchangerates.util.TAG_SETTINGS

/**
 * Settings Screen
 *
 * - Adjust REFRESH time (e.g. 3sec, 5sec, 15sec)
 * - Change Currency (e.g. EUR, RON etc.)
 */
class SettingsFragment : PreferenceFragmentCompat(), ISettingsScreen {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        Log.d(TAG_SETTINGS, "Display shared pref")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Views
        (activity as MainActivity).displayToolbar(true)
    }
}
