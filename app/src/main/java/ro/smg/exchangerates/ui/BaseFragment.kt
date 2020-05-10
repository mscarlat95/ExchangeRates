package ro.smg.exchangerates.ui

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ro.smg.exchangerates.R
import java.lang.Exception


/**
 *  Base fragment features
 */
abstract class BaseFragment : Fragment() {

    fun displayError(error: Exception?) = displayDialog(
        getString(R.string.error_generic_title),
        error?.message ?: ""
    )

    fun displayDialog(title: String, message: String, onPositiveChoose: () -> Unit = {}) =
        AlertDialog.Builder(activity as MainActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(getString(R.string.ok)) { _, _ -> onPositiveChoose() }
        }.show()

}