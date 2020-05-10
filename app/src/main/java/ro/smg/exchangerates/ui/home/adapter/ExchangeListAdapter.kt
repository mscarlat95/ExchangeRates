package ro.smg.exchangerates.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ro.smg.exchangerates.R
import ro.smg.exchangerates.databinding.CurrencyLayoutBinding
import ro.smg.exchangerates.model.data.Currency

class ExchangeListAdapter(
    private val exchangesList: ArrayList<Currency>
) : RecyclerView.Adapter<ExchangeListAdapter.CurrencyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CurrencyLayoutBinding>(
            inflater,
            R.layout.currency_layout,
            parent,
            false
        )

        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = exchangesList.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        // Bind data
        holder.view.currency = exchangesList[position]
    }

    fun updateList(list: List<Currency>) {
        exchangesList.clear()
        exchangesList.addAll(list)
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(var view: CurrencyLayoutBinding): RecyclerView.ViewHolder(view.root)
}