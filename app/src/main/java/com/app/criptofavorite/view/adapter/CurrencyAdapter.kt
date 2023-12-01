package com.app.criptofavorite.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.criptofavorite.R
import com.app.criptofavorite.model.Currency

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currencies = mutableListOf<Currency>() // Renomeie para evitar confusão

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_finance, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.name.text = currency.name
        holder.currencyBuyValue.text = "%.2f".format(currency.buy).replace('.', ',')
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun addCurrency(currency: Currency) {
        this.currencies.add(currency)
        notifyDataSetChanged()
    }

    fun setCurrencies(newCurrencies: List<Currency>) {
        this.currencies.clear()
        this.currencies.addAll(newCurrencies)
        notifyDataSetChanged()
    }
}
