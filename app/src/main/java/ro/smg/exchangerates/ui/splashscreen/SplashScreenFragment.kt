package ro.smg.exchangerates.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import ro.smg.exchangerates.R
import ro.smg.exchangerates.model.app.ApplicationState
import ro.smg.exchangerates.ui.BaseFragment
import ro.smg.exchangerates.ui.MainActivity
import ro.smg.exchangerates.ui.iViews.ISplashScreen
import ro.smg.exchangerates.util.SPLASH_SCREEN_DURATION_MS

/**
 * Splash Screen
 */
class SplashScreenFragment : BaseFragment(), ISplashScreen {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash_screen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        observeViewModel()

        // Views
        (activity as MainActivity).displayToolbar(false)
        Handler().postDelayed({ viewModel.launchApp() }, SPLASH_SCREEN_DURATION_MS)
    }

    private fun observeViewModel() {
        viewModel.appState.observe(viewLifecycleOwner, Observer { state ->
            navigate(state)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })
    }

    override fun navigate(state: ApplicationState) {
        view?.let {
            Navigation.findNavController(it).navigate(
                when (state) {
                    ApplicationState.HOME -> SplashScreenFragmentDirections.navToHome()
                    else -> SplashScreenFragmentDirections.navToLogin()
                }
            )
        }
    }
}
