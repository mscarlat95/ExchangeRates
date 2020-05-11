package ro.smg.exchangerates.model.data

data class RangedRates(
    val rates: Map<String, Map<String, Double>>?,
    var start_at: String,
    var base: String?,
    var end_at: String?
)