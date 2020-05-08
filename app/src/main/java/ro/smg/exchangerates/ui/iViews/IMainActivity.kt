package ro.smg.exchangerates.ui.iViews

interface IMainActivity {

    /**
     * Display or hide app toolbar view
     */
    fun displayToolbar(display: Boolean)

    /**
     * Hide user information if it is not available
     */
    fun hideAccountInfoInMenu()

    /**
     * Display user information (name and email)
     */
    fun displayAccountInfoInMenu(displayName: String?, email: String?)

}