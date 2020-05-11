package ro.smg.exchangerates.ui.history.date_formatter

import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.math.floor

class XAxisFormatter(val map: Map<Float, String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String = map.getValue(
        if (value - floor(value) > 0.5f) floor(value) + 1 else floor(value)
    )
}