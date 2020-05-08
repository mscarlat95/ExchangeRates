package ro.smg.exchangerates.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import ro.smg.exchangerates.R
import ro.smg.exchangerates.ui.auth.LoginFragment
import ro.smg.exchangerates.ui.iViews.IMainActivity
import ro.smg.exchangerates.util.REQ_CODE_GOOGLE_SIGN_IN

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views
        initToolbar()
        initDrawerLayout()
        initNavigationController()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_splash_screen,
                R.id.nav_login,
                R.id.nav_help,
                R.id.nav_home,
                R.id.nav_history,
                R.id.nav_settings,
                R.id.nav_account
            ),
            drawerLayout
        )
    }

    private fun initNavigationController() {
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }

    private fun getActiveFragment() =
        nav_host_fragment.childFragmentManager.primaryNavigationFragment!!

    private fun notifyLoginFragment(data: Intent) {
        getActiveFragment().takeIf { it is LoginFragment }.apply {
            (this as LoginFragment).onSignInResult(data)
        }
    }

    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(
        navController,
        appBarConfiguration
    ) || super.onSupportNavigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQ_CODE_GOOGLE_SIGN_IN -> notifyLoginFragment(data!!)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun displayToolbar(display: Boolean) {
        toolbar.visibility = if (display) View.VISIBLE else View.GONE
        drawerLayout.setDrawerLockMode(if (display) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun hideAccountInfoInMenu() {
        val headerView: View? = navView.getHeaderView(0)
        headerView?.display_name_text_view?.visibility = View.GONE
        headerView?.email_text_view?.visibility = View.GONE
    }

    override fun displayAccountInfoInMenu(displayName: String?, email: String?) {
        val headerView: View? = navView.getHeaderView(0)

        headerView?.display_name_text_view?.apply {
            visibility = View.VISIBLE
            text = displayName
        }

        headerView?.email_text_view?.apply {
            visibility = View.VISIBLE
            text = email
        }
    }
}
