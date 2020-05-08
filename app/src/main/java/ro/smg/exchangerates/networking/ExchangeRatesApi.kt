package ro.smg.exchangerates.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.smg.exchangerates.BuildConfig
import ro.smg.exchangerates.util.BASE_URL
import ro.smg.exchangerates.util.CONNECTION_TIMEOUT_MS
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

class ExchangeRatesApi {

    companion object {

        @Volatile
        private var instance: Retrofit? = null
        private val LOCK = Any()
        private var api: IExchangeRatesApi? = null

        fun getApi(): IExchangeRatesApi =
            api ?: getInstance().create(IExchangeRatesApi::class.java).also { api = it }

        private fun getInstance(): Retrofit =
            instance ?: synchronized(LOCK) {
                instance ?: createInstance().also {
                    instance = it
                }
            }

        private fun createInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().apply {
                        if (BuildConfig.DEBUG)
                            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        hostnameVerifier(HostnameVerifier { _, _ -> true })
                        readTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                        writeTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                        connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

}