package ro.smg.exchangerates.util

import ro.smg.exchangerates.BuildConfig

class Log {
    companion object {
        private val LOG_ENABLE: Boolean = BuildConfig.DEBUG

        fun e(tag: String?, msg: String) {
            if (LOG_ENABLE) android.util.Log.e(tag, msg)
        }

        fun e(tag: String?, msg: String?, tr: Throwable?) {
            if (LOG_ENABLE) android.util.Log.e(tag, msg, tr)
        }

        fun d(tag: String?, msg: String) {
            if (LOG_ENABLE) android.util.Log.d(tag, msg)
        }

        fun d(tag: String?, msg: String?, tr: Throwable?) {
            if (LOG_ENABLE) android.util.Log.d(tag, msg, tr)
        }

        fun v(tag: String?, msg: String) {
            if (LOG_ENABLE) android.util.Log.v(tag, msg)
        }

        fun v(tag: String?, msg: String?, tr: Throwable?) {
            if (LOG_ENABLE) android.util.Log.v(tag, msg, tr)
        }

        fun i(tag: String?, msg: String) {
            if (LOG_ENABLE) android.util.Log.i(tag, msg)
        }

        fun i(tag: String?, msg: String?, tr: Throwable?) {
            if (LOG_ENABLE) android.util.Log.i(tag, msg, tr)
        }

        fun w(tag: String?, msg: String) {
            if (LOG_ENABLE) android.util.Log.w(tag, msg)
        }

        fun w(tag: String?, msg: String?, tr: Throwable?) {
            if (LOG_ENABLE) android.util.Log.w(tag, msg, tr)
        }
    }
}
