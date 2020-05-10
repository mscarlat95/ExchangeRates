package ro.smg.exchangerates.model.data

import ro.smg.exchangerates.R

/**
 * https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html
 *
 * Available currency
 */
enum class Currency(
    val imageRes: Int?,
    val description: String?,
    var value: Double? = null
) {
    EUR(R.drawable.euro_icon, "Euro"),
    USD(R.drawable.usd, "Us Dollar"),
    JPY(R.drawable.jpy, "Japanese yen"),
    BGN(R.drawable.bgn, "Bulgarian lev"),
    CZK(R.drawable.czk, "Czech koruna"),
    DKK(R.drawable.dkk, "Danish krone"),
    GBP(R.drawable.gbp, "Pound sterling"),
    HUF(R.drawable.huf, "Hungarian forint"),
    PLN(R.drawable.pln, "Polish zloty"),
    RON(R.drawable.ron, "Romanian leu"),
    SEK(R.drawable.sek, "Swedish krona"),
    CHF(R.drawable.chf, "Swiss franc"),
    ISK(R.drawable.isk, "Icelandic krona"),
    NOK(R.drawable.nok, "Norwegian krone"),
    HRK(R.drawable.hrk, "Croatian kuna"),
    RUB(R.drawable.rub, "Russian rouble"),
    TRY(R.drawable.try0, "Turkish lira"),
    AUD(R.drawable.aud, "Australian dollar"),
    BRL(R.drawable.brl, "Brazilian real"),
    CAD(R.drawable.cad, "Canadian dollar"),
    CNY(R.drawable.cny, "Chinese yuan renminbi"),
    HKD(R.drawable.hdk, "Hong Kong dollar"),
    IDR(R.drawable.idr, "Indonesian rupiah"),
    ILS(R.drawable.ils, "Israeli shekel"),
    INR(R.drawable.inr, "Indian rupee"),
    KRW(R.drawable.krw, "South Korean won"),
    MXN(R.drawable.mxn, "Mexican peso"),
    MYR(R.drawable.myr, "Malaysian ringgit"),
    NZD(R.drawable.nzd, "New Zealand dollar"),
    PHP(R.drawable.php, "Philippine peso"),
    SGD(R.drawable.sgd, "Singapore dollar"),
    THB(R.drawable.thb, "Thai baht"),
    ZAR(R.drawable.zar, "South African rand");

    companion object {
        fun getCurrencyByName(name: String): Currency = valueOf(name)
    }
}
