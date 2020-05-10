package ro.smg.exchangerates.networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ro.smg.exchangerates.model.data.Rate


interface IExchangeRatesApi {

    /**
     * Obtain all exchange rates
     */
    @GET("latest")
    fun getAllExchanges(@Query("base") base: String): Single<Rate>


    /**
     * Obtain exchange rates for the latest 10 days
     */
    @GET("history")
    fun getExchangesByDate(
        @Query("start_at") startDate: String,
        @Query("end_at") endDate: String,
        @Query("symbols") symbols: List<String> // RON,USD,BGN
    ): Single<Any>


}
