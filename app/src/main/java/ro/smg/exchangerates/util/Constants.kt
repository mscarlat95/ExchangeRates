package ro.smg.exchangerates.util

// Tags
const val TAG_APP_STATE = "APP_STATE"
const val TAG_AUTH: String = "AUTHENTICATION"
const val TAG_HOME: String = "LIVE_EXCHANGE"
const val TAG_CHART: String = "AUTHENTICATION"
const val TAG_SETTINGS: String = "SETTINGS"

// Requests codes
const val REQ_CODE_GOOGLE_SIGN_IN: Int = 1231

// Networking
const val BASE_URL: String = "https://api.exchangeratesapi.io/"
const val CONNECTION_TIMEOUT_MS: Long = 60000L

// Shared Preferences
const val REFRESH_TIME_PREF: String = "refresh_time"
const val DEFAULT_REFRESH_TIME_S: String = "3"
const val CURRENCY_PREF: String = "base_currency"
const val DEFAULT_CURRENCY: String = "EUR"

// App Config
const val SPLASH_SCREEN_DURATION_MS: Long = 2000L
const val CONTACT_URL: String = "https://exchangeratesapi.io/"