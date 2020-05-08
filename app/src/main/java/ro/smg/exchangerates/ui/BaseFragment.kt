package ro.smg.exchangerates.ui

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ro.smg.exchangerates.R
import ro.smg.exchangerates.model.GenericError


/**
 *  Base fragment features
 */
abstract class BaseFragment : Fragment() {

    fun displayError(error: GenericError) {
        displayDialog(
            error.title,
            error.message
        )
    }

    fun displayDialog(title: String, message: String, onPositiveChoose: () -> Unit = {}) =
        AlertDialog.Builder(activity as MainActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(getString(R.string.ok)) { _, _ -> onPositiveChoose() }
        }.show()

}