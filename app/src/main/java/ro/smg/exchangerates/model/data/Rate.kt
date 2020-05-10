package ro.smg.exchangerates.model.data

data class Rate(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)

