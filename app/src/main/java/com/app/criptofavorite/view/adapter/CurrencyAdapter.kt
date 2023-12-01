package com.app.criptofavorite.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.criptofavorite.R
import com.app.criptofavorite.model.Currency

class CurrencyAdapter() : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currency = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_finance, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currency[position]
        holder.name.text = currency.name
        //holder.buy.text = currency.buy.toString()

        // Forma ta o buy para duas casas decimais e com o símbolo de $ e usa vírgula para separar os milhares
        val buy = String.format("%.2f", currency.buy).replace(".", ",")
        holder.buy.text = "$ $buy"

    }

    override fun getItemCount(): Int {
        return currency.size
    }

    fun addCurrency(currency: Currency) {
        this.currency.add(currency)
        notifyDataSetChanged()
    }
}
