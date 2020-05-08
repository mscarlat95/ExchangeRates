package ro.smg.exchangerates.networking

// TODO: SMG
//    https://api.exchangeratesapi.io/latest?base=EUR
//    https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-01-02&symbols=RON,USD,BGN

interface IExchangeRatesApi {

    /**
     * Obtain all exchange rates
     */
    fun getAllExchanges()

    /**
     * Obtain exchange rates for the latest 10 days
     */
    fun getExchangesByDate()

}