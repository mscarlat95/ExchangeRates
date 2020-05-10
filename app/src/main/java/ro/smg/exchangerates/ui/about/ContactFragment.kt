package ro.smg.exchangerates.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_contact.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.iViews.IContactScreen
import ro.smg.exchangerates.util.CONTACT_URL

/**
 * Contact Screen
 *
 * - Display API use-cases
 * https://exchangeratesapi.io/
 *
 */
class ContactFragment : BaseFragment(), IContactScreen {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_contact, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Views
        web_view.webViewClient = WebViewClient()
        web_view.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
        }
        web_view.loadUrl(CONTACT_URL)
    }

}
