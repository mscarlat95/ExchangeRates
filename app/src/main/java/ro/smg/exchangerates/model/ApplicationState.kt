package ro.smg.exchangerates.model

/**
 *  Possible state of the application
 *
 */
enum class ApplicationState {

    /**
     * Display Splash Screen
     */
    SPLASH_SCREEN,

    /**
     * Perform or Skip Authentication Screen
     */
    AUTHENTICATION,

    /**
     * Home Screen (view live updates)
     */
    HOME,

    /**
     * History Screen (view older exchanges via charts)
     */
    HISTORY,

    /**
     * Settings Screen
     */
    SETTINGS
}